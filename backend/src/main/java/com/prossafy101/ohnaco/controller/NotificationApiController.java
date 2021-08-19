package com.prossafy101.ohnaco.controller;

import com.prossafy101.ohnaco.service.JwtUtil;
import com.prossafy101.ohnaco.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/noti")
public class NotificationApiController {

    @Autowired
    private JwtUtil jwtUtil;

    private final NotificationService notificationService;

    public NotificationApiController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Map<String,String> token, HttpServletRequest req) {
        String jwt = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(jwt);
        System.out.println(token.get("token"));
        notificationService.register(userid, token.get("token"));
        return ResponseEntity.ok().build();
    }

}
