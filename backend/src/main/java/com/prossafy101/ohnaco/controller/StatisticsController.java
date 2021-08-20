package com.prossafy101.ohnaco.controller;

import com.prossafy101.ohnaco.service.JwtUtil;
import com.prossafy101.ohnaco.service.RedisUtil;
import com.prossafy101.ohnaco.service.StatisticsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/info")
    @ApiOperation(value = "통계정보 반환환 =>email, password값 전달")
    public Object tempJoin(@RequestParam String option, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        if(option.equals("week")) {
            Object weekObject = redisUtil.getObject("statistics:week:"+userid);
            if(weekObject == null) {
                statisticsService.updateWeekAndMonth(userid);
                weekObject = redisUtil.getObject("statistics:week:"+userid);
            }
            return new ResponseEntity<>(weekObject , HttpStatus.OK);
        } else {
            Object monthObject = redisUtil.getObject("statistics:month:"+userid);
            if(monthObject == null) {
                statisticsService.updateWeekAndMonth(userid);
                monthObject = redisUtil.getObject("statistics:month:"+userid);
            }
            return new ResponseEntity<>(monthObject , HttpStatus.OK);
        }
    }
}
