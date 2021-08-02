package com.prossafy101.ohnaco.controller;

import com.prossafy101.ohnaco.entity.user.SignInDto;
import com.prossafy101.ohnaco.entity.user.TempUserDto;
import com.prossafy101.ohnaco.entity.user.User;
import com.prossafy101.ohnaco.entity.user.UserDto;
import com.prossafy101.ohnaco.service.JwtUtil;
import com.prossafy101.ohnaco.service.RedisUtil;
import com.prossafy101.ohnaco.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @PostMapping("/test")
    public ResponseEntity<?> test() {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public ResponseEntity<Map<String, Object>> signIn(@RequestBody SignInDto dto, HttpServletResponse res) {
        HttpStatus status = null;
        Map<String, Object> result = new HashMap<>();
        try {
            User user = userService.signIn(dto);

            String accessToken = jwtUtil.generateAccessToken(user.getUserid(), dto.getEmail());
            String refreshToken = jwtUtil.generateRefreshToken(user.getUserid(), dto.getEmail());

            logger.info(refreshToken);
            res.setHeader("Authorization", "Bearer " + accessToken);
            redisUtil.setData(user.getEmail(), refreshToken,  JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);

            result.put("userId", user.getUserid());
            result.put("nickname", user.getNickname());
            result.put("githubId", user.getGithubid());
            result.put("image", user.getImage());
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.UNAUTHORIZED;
            result.put("message", e.getMessage());
        }

        return new ResponseEntity<>(result , status);
    }

    @GetMapping("/signOut")
    public Object signOut(@RequestParam String email) {
        redisUtil.deleteData(email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/join/idcheck")
    @ApiOperation(value = "아이디 중복 체크")
    public Object checkId(@RequestParam(required = true) String email) {
        Map<String, Object> result = new HashMap<>();
        try {
            userService.isSameEmail(email);
            result.put("status", true);
            result.put("message", "중복된 이메일이 없습니다.");
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", e.getMessage());
        }
        //임시로 레디스 저장된 이메일key 삭제!!!! 나중에 지워야함
//        userService.tempUserDelete(email);
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @GetMapping("/namecheck")
    @ApiOperation(value = "닉네임 중복 체크")
    public Object checkNickname(@RequestParam(required = true) String nickname) {
        Map<String, Object> result = new HashMap<>();

        try {
            userService.isSameNickname(nickname);
            result.put("status", true);
            result.put("message", "중복된 닉네임이 없습니다.");
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", e.getMessage());
        }

        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @PostMapping("/join")
    @ApiOperation(value = "임시정보 저장 => email, password값 전달")
    public Object tempJoin(@RequestBody TempUserDto tempUserDto) {
        Map<String, Object> result = new HashMap<>();
        String emailCode = userService.randomCode();
        try {
            userService.tempSaveAndSendEmail(TempUserDto.builder()
                    .email(tempUserDto.getEmail()).password(tempUserDto.getPassword()).token(emailCode).build());
            result.put("status", true);
            result.put("message", "임시정보 저장 성공");
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", e.getMessage());
        }

        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @PostMapping("/join/codecheck")
    @ApiOperation(value = "인증코드 검사 => email, token 전달")
    public Object checkEmailCode(@RequestBody TempUserDto tempUser) {
        Map<String, Object> result = new HashMap<>();
        Optional<TempUserDto> tempUserDto;
        try {
            tempUserDto = userService.tempUserByEmail(tempUser.getEmail());
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "임시 이메일 찾기 실패");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        if(tempUserDto.isPresent()) {
            if(!tempUserDto.get().getToken().equals(tempUser.getToken())) {
                result.put("status", false);
                result.put("message", "코드번호 불일치");
            } else {
                result.put("status", true);
                result.put("message", "코드번호 일치.");
            }
        } else {
            result.put("status", false);
            result.put("message", "존재하지 않는 이메일입니다.");
        }

        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @GetMapping("/join/resend")
    @ApiOperation(value = "이메일 재전송 => email값 전달")
    public Object tempJoin(@RequestParam(required = true) String email) {
        Map<String, Object> result = new HashMap<>();
        String emailCode = userService.randomCode();
//        System.out.println(userDto.getEmail());
        Optional<TempUserDto> tempUserDto;
        try {
            tempUserDto = userService.tempUserByEmail(email);
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "임시 이메일 찾기 실패");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        if(!tempUserDto.isPresent()) {
            result.put("status", false);
            result.put("message", "존재하지 않는 이메일입니다.");
        } else {
            tempUserDto.get().setToken(emailCode);

            try {
                userService.tempSaveAndSendEmail(TempUserDto.builder()
                        .email(tempUserDto.get().getEmail()).password(tempUserDto.get().getPassword()).token(emailCode).build());
                result.put("status", true);
                result.put("message", "이메일 보내기 성공.");
            } catch (Exception e) {
                result.put("status", false);
                result.put("message", e.getMessage());
            }
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @PostMapping("/join/profile")
    @ApiOperation(value = "회원정보 저장 => email, image(아직 구현x), nickname, githubid, position 전달 multipart?로 전송필요")
    public Object join(@RequestBody UserDto userDto) {
        Map<String, Object> result = new HashMap<>();
        Optional<TempUserDto> tempUserDto;
        try {
            tempUserDto = userService.tempUserByEmail(userDto.getEmail());
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "임시 이메일 찾기 실패");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        if(!tempUserDto.isPresent()) {
            result.put("status", false);
            result.put("message", "존재하지 않는 이메일입니다.");
        } else {
            try {
                userService.userSave(User.builder()
                        .userid(userService.createUserid())
                        .email(userDto.getEmail())
                        .password(tempUserDto.get().getPassword())
                        .nickname(userDto.getNickname())
                        .githubid(userDto.getGithubid())
                        .positions(userService.positionsName(userDto.getPosition()))
                        .build());

                userService.tempUserDelete(userDto.getEmail());
                result.put("status", true);
                result.put("message", "회원가입 성공.");
            } catch (Exception e) {
                result.put("status", false);
                result.put("message", "회원가입오류.");
            }
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
