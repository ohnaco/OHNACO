package com.prossafy101.ohnaco.controller;

import com.prossafy101.ohnaco.entity.user.SignInDto;
import com.prossafy101.ohnaco.entity.user.User;
import com.prossafy101.ohnaco.service.JwtUtil;
import com.prossafy101.ohnaco.service.RedisUtil;
import com.prossafy101.ohnaco.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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

}
