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
@CrossOrigin
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

            String accessToken = jwtUtil.generateAccessToken(dto.getEmail());
            String refreshToken = jwtUtil.generateRefreshToken(dto.getEmail());

            logger.info(refreshToken);
            res.setHeader("accessToken", accessToken);
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

    @GetMapping("/join/idcheck")
    @ApiOperation(value = "아이디 중복 체크")
    public Object checkId(@RequestParam(required = true) String email) {
        HttpStatus status = null;
        Map<String, Object> result = new HashMap<>();
        try {
            userService.isSameEmail(email);
            result.put("message", "중복된 이메일이 없습니다.");
            status = HttpStatus.OK;
        } catch (Exception e) {
            result.put("message", e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        //임시로 레디스 저장된 이메일key 삭제!!!! 나중에 지워야함
//        userService.tempUserDelete(email);
        return new ResponseEntity<>(result , status);
    }

    @GetMapping("/namecheck")
    @ApiOperation(value = "닉네임 중복 체크")
    public Object checkNickname(@RequestParam(required = true) String nickname) {
        HttpStatus status = null;
        Map<String, Object> result = new HashMap<>();

        try {
            userService.isSameNickname(nickname);
            result.put("message", "중복된 닉네임이 없습니다.");
            status = HttpStatus.OK;
        } catch (Exception e) {
            result.put("message", e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(result , status);
    }

    @PostMapping("/join")
    @ApiOperation(value = "임시정보 저장 => email, password값 전달")
    public Object tempJoin(@RequestBody TempUserDto tempUser) {
        HttpStatus status = HttpStatus.OK;
        Map<String, Object> result = new HashMap<>();
        String emailCode = userService.randomCode();
        try {
            userService.tempUserSave(TempUserDto.builder()
                    .email(tempUser.getEmail())
                    .password(tempUser.getPassword())
                    .token(emailCode)
                    .build());
            //********************************유저 이메일로 변경해줘야함!!************************
            userService.sendMail(tempUser.getEmail(), "[OHNACO 이메일 인증 코드]", emailCode);
            result.put("message", "성공.");
            status = HttpStatus.OK;
        } catch(MessagingException e) {
            result.put("message", "메시지 전송 실패");
            status = HttpStatus.BAD_REQUEST;
        } catch(Exception e) {
            result.put("message", "임시회원 저장 오류");
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(result , status);
    }

    @PostMapping("/join/codecheck")
    @ApiOperation(value = "인증코드 검사 => email, token 전달")
    public Object checkEmailCode(@RequestBody TempUserDto tempUser) {
        HttpStatus status = HttpStatus.OK;
        Map<String, Object> result = new HashMap<>();
        Optional<TempUserDto> tempUserDto = userService.tempUserByEmail(tempUser.getEmail());
        if(tempUserDto.isPresent()) {
            if(!tempUserDto.get().getToken().equals(tempUser.getToken())) {
                result.put("message", "코드번호 불일치");
                status = HttpStatus.BAD_REQUEST;
            } else {
                result.put("message", "코드번호 일치.");
                status = HttpStatus.OK;
            }
        } else {
            result.put("message", "존재하지 않는 이메일입니다.");
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(result , status);
    }

    @PostMapping("/join/resend")
    @ApiOperation(value = "이메일 재전송 => email값 전달")
    public Object tempJoin(@RequestBody UserDto userDto) {
        HttpStatus status = HttpStatus.OK;
        Map<String, Object> result = new HashMap<>();
        String emailCode = userService.randomCode();
//        System.out.println(userDto.getEmail());
        Optional<TempUserDto> tempUserDto = userService.tempUserByEmail(userDto.getEmail());
        if(!tempUserDto.isPresent()) {
            result.put("message", "존재하지 않는 이메일입니다.");
            status = HttpStatus.BAD_REQUEST;
        } else {
            tempUserDto.get().setToken(emailCode);

            try {
                userService.tempUserSave(tempUserDto.get());
                //********************************유저 이메일로 변경해줘야함!!************************
                userService.sendMail(userDto.getEmail(), "[OHNACO 이메일 인증 코드]", emailCode);
                result.put("message", "전송 성공.");
                status = HttpStatus.OK;
            } catch(MessagingException e) {
                result.put("message", "메시지 전송 실패");
                status = HttpStatus.BAD_REQUEST;
            } catch(Exception e) {
                result.put("message", "임시회원저장 오류");
                status = HttpStatus.BAD_REQUEST;
            }

        }
        return new ResponseEntity<>(result , status);
    }

    @PostMapping("/join/profile")
    @ApiOperation(value = "회원정보 저장 => email, image(아직 구현x), nickname, githubid, position 전달 multipart?로 전송필요")
    public Object join(UserDto userDto) throws Exception {
        HttpStatus status = HttpStatus.OK;
        Map<String, Object> result = new HashMap<>();

        Optional<TempUserDto> tempUserDto = userService.tempUserByEmail(userDto.getEmail());
        if(!tempUserDto.isPresent()) {
            result.put("message", "존재하지 않는 이메일입니다.");
            status = HttpStatus.BAD_REQUEST;
        } else {
            try {
                userService.userSave(User.builder()
                        .userid(userService.createUserid())
                        .email(userDto.getEmail())
                        .password(tempUserDto.get().getPassword())
                        .nickname(userDto.getNickname())
                        .githubid(userDto.getGithubid())
                        .positions(userService.positionsName(userDto.getPosition()))
                        .image(userService.imageUpload(userDto.getImage()))
                        .build());

                userService.tempUserDelete(userDto.getEmail());
                result.put("message", "회원가입 성공.");
                status = HttpStatus.OK;
            } catch (Exception e) {
                result.put("message", "회원가입오류.");
                status = HttpStatus.BAD_REQUEST;
            }
        }
        return new ResponseEntity<>(result , status);
    }
}
