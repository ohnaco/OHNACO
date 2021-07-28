package com.prossafy101.ohnaco.controller;

import com.prossafy101.ohnaco.entity.user.SignInDto;
import com.prossafy101.ohnaco.entity.user.TempUserDto;
import com.prossafy101.ohnaco.entity.user.User;
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
        String msg = "success";
        if(userService.isSameEmail(email)) {
            msg = "false";
        }
        ResponseEntity response = new ResponseEntity<>(msg, HttpStatus.OK);

        //임시로 레디스 저장된 이메일key 삭제!!!! 나중에 지워야함
//        userService.tempUserDelete(email);
        return response;
    }

    @GetMapping("/namecheck")
    @ApiOperation(value = "닉네임 중복 체크")
    public Object checkNickname(@RequestParam(required = true) String nickname) {
        String msg = "success";
        if(userService.isSameNickname(nickname)) {
            msg = "false";
        }
        ResponseEntity response = new ResponseEntity<>(msg, HttpStatus.OK);

        return response;
    }

    @PostMapping("/join")
    @ApiOperation(value = "임시정보 저장 => email, password값 전달")
    public Object tempJoin(@RequestParam String email, @RequestParam String password) throws MessagingException {
        String msg = "success";
        String emailCode = userService.randomCode();
        userService.tempUserSave(TempUserDto.builder()
                .email(email)
                .password(password)
                .token(emailCode)
                .build());
        //********************************유저 이메일로 변경해줘야함!!************************
        userService.sendMail(email, "[OHNACO 이메일 인증 코드]", emailCode);
        ResponseEntity response = new ResponseEntity<>(msg, HttpStatus.OK);
        return response;
    }

    @PostMapping("/join/codecheck")
    @ApiOperation(value = "인증코드 검사 => email, token 전달")
    public Object checkEmailCode(@RequestParam String email, @RequestParam String code) {
        String msg = "success";
        Optional<TempUserDto> tempUserDto = userService.tempUserByEmail(email);
        if(tempUserDto.isPresent()) {
            if(!tempUserDto.get().getToken().equals(code)) {
                msg = "false";
            }
        } else {
            msg = "false";
        }

        ResponseEntity response = new ResponseEntity<>(msg, HttpStatus.OK);
        return response;
    }

    @PostMapping("/join/resend")
    @ApiOperation(value = "이메일 재전송 => email값 전달")
    public Object tempJoin(@RequestParam(required = true) String email) throws MessagingException {
        String msg = "success";
        String emailCode = userService.randomCode();
        System.out.println(email);
        Optional<TempUserDto> tempUserDto = userService.tempUserByEmail(email);
        if(!tempUserDto.isPresent()) {
            msg = "false";
        } else {
            tempUserDto.get().setToken(emailCode);
            userService.tempUserSave(tempUserDto.get());
            //********************************유저 이메일로 변경해줘야함!!************************
            userService.sendMail(email, "[OHNACO 이메일 인증 코드]", emailCode);
        }
        ResponseEntity response = new ResponseEntity<>(msg, HttpStatus.OK);
        return response;
    }

    @PostMapping("/join/profile")
    @ApiOperation(value = "회원정보 저장 => email, image(아직 구현x), nickname, githubid, position 전달")
    public Object join(@RequestParam String email, @RequestParam String nickname, @RequestParam String githubid
            , @RequestParam String position, @RequestParam MultipartFile image) throws Exception {
        String msg = "success";
        System.out.println(image);
        System.out.println(image.getOriginalFilename());
        Optional<TempUserDto> tempUserDto = userService.tempUserByEmail(email);

        userService.userSave(User.builder()
                .userid(userService.createUserid())
                .email(email)
                .password(tempUserDto.get().getPassword())
                .nickname(nickname)
                .githubid(githubid)
                .positions(userService.positionsName(position))
                .image(userService.imageUpload(image))
                .build());

        userService.tempUserDelete(email);
        ResponseEntity response = new ResponseEntity<>(msg, HttpStatus.OK);
        return response;
    }

}
