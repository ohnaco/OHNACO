package com.prossafy101.ohnaco.controller;

import com.prossafy101.ohnaco.entity.user.TempUserDto;
import com.prossafy101.ohnaco.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


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

        //임시로 레디스 저장된 이메일key 삭제!!!!
        userService.tempUserDelete(email);
        return response;
    }

    @PostMapping("/join")
    @ApiOperation(value = "임시정보 저장 => email, password값 전달")
    public Object tempJoin(@RequestBody Map<String, String> tempUser) {
        String msg = "success";
        userService.tempUserSave(TempUserDto.builder()
                .email(tempUser.get("email"))
                .password(tempUser.get("password"))
                .token(userService.randomCode())
                .build());
        ResponseEntity response = new ResponseEntity<>(msg, HttpStatus.OK);
        return response;
    }




}
