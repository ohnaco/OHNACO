package com.prossafy101.ohnaco.controller;

import com.prossafy101.ohnaco.entity.user.Positions;
import com.prossafy101.ohnaco.entity.user.TempUserDto;
import com.prossafy101.ohnaco.entity.user.User;
import com.prossafy101.ohnaco.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/join/idcheck")
    @ApiOperation(value = "아이디 중복 체크")
    public Object checkId(@RequestParam(required = true) String email) {
        String msg = "success";
        if(userService.isSameEmail(email)) {
            msg = "false";
        }
        ResponseEntity response = new ResponseEntity<>(msg, HttpStatus.OK);

        //임시로 레디스 저장된 이메일key 삭제!!!! 나중에 지워야함
        userService.tempUserDelete(email);
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
    public Object tempJoin(@RequestBody Map<String, String> tempUser) throws MessagingException {
        String msg = "success";
        String emailCode = userService.randomCode();
        userService.tempUserSave(TempUserDto.builder()
                .email(tempUser.get("email"))
                .password(tempUser.get("password"))
                .token(emailCode)
                .build());
        //********************************유저 이메일로 변경해줘야함!!************************
        userService.sendMail("kimho1995@naver.com", "[OHNACO 이메일 인증 코드]", emailCode);
        ResponseEntity response = new ResponseEntity<>(msg, HttpStatus.OK);
        return response;
    }

    @PostMapping("/join/codecheck")
    @ApiOperation(value = "인증코드 검사 => email, token 전달")
    public Object checkEmailCode(@RequestBody Map<String, String> checkEmail) {
        String msg = "success";
        Optional<TempUserDto> tempUserDto = userService.tempUserByEmail(checkEmail.get("email"));
        if(tempUserDto.isPresent()) {
            if(!tempUserDto.get().getToken().equals(checkEmail.get("token"))) {
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
        Optional<TempUserDto> tempUserDto = userService.tempUserByEmail(email);
        if(!tempUserDto.isPresent()) {
            msg = "false";
        } else {
            tempUserDto.get().setToken(emailCode);
            userService.tempUserSave(tempUserDto.get());
            //********************************유저 이메일로 변경해줘야함!!************************
            userService.sendMail("kimho1995@naver.com", "[OHNACO 이메일 인증 코드]", emailCode);
        }
        ResponseEntity response = new ResponseEntity<>(msg, HttpStatus.OK);
        return response;
    }

    @PostMapping("/join/profile")
    @ApiOperation(value = "회원정보 저장 => email, image(아직 구현x), nickname, githubid, position 전달")
    public Object join(@RequestBody Map<String, String> tempUser) throws MessagingException {
        String msg = "success";
        Optional<TempUserDto> tempUserDto = userService.tempUserByEmail(tempUser.get("email"));
        userService.userSave(User.builder()
                                .userid(userService.createUserid())
                                .email(tempUser.get("email"))
                                .password(tempUserDto.get().getPassword())
                                .nickname(tempUser.get("nickname"))
                                .githubid(tempUser.get("githubid"))
                                .positions(userService.positionsName(tempUser.get("position")))
                                .build());

        ResponseEntity response = new ResponseEntity<>(msg, HttpStatus.OK);
        return response;
    }


}
