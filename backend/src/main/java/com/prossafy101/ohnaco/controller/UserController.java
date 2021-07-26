package com.prossafy101.ohnaco.controller;

import com.prossafy101.ohnaco.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
        return response;
    }



}
