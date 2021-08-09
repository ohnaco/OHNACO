package com.prossafy101.ohnaco.controller;

import com.google.api.Http;
import com.prossafy101.ohnaco.service.JwtUtil;
import com.prossafy101.ohnaco.service.TechService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tech")
public class TechController {

    @Autowired
    private TechService techService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/blog/list")
    @ApiOperation("블로그 목록 전체 불러오기")
    public Object getBlogList(HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        result.put("list", techService.getBlogList(userid));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/blog/subscribe")
    public Object getSubscribedBlogList(HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);

        result.put("list", techService.getSubscribeBlogList(userid));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/blog/subscribe")
    public Object subscribeBlog(@RequestBody Map<String, String> map, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);

        try {
            techService.subscribeBlog(userid, Long.parseLong(map.get("blogid")));
            result.put("status", "success");
        } catch(Exception e) {
            e.printStackTrace();
            result.put("status", "fail");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/blog/subscribe")
    public Object cancelSubscribe(@RequestBody Map<String, String> map, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);


        try {
            techService.cancelSubscribe(userid, Long.parseLong(map.get("blogid")));
            result.put("status", "success");
        } catch(Exception e) {
            e.printStackTrace();
            result.put("status", "fail");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
