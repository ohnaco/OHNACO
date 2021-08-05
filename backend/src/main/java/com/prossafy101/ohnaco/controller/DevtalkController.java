package com.prossafy101.ohnaco.controller;

import com.prossafy101.ohnaco.entity.devtalk.QuestionDto;
import com.prossafy101.ohnaco.service.JwtUtil;
import com.prossafy101.ohnaco.service.QuestionSerivce;
import com.prossafy101.ohnaco.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/devtalk")
public class DevtalkController {
    @Autowired
    private QuestionSerivce questionSerivce;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @PostMapping("/question")
    @ApiOperation(value = "question 저장 => title, content, tag배열 전달")
    public Object save(@RequestBody QuestionDto questionDto, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        questionSerivce.save(questionDto, userService.findByUserid(userid));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/question")
    @ApiOperation(value = "question 수정 => questionid, title, content, tag배열 전달")
    public Object updateQuestion(@RequestBody QuestionDto questionDto, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        try {
            questionSerivce.updateQuestion(questionDto, userService.findByUserid(userid));
            result.put("status", true);
            result.put("message", "성공적으로 수정 했습니다.");
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", e.getMessage());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/question")
    @ApiOperation(value = "question 삭제 => questionid 전달")
    public Object deleteQuestion(@RequestParam int questionid, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        try {
            questionSerivce.deleteQuestion(questionid, userService.findByUserid(userid));
            result.put("status", true);
            result.put("message", "성공적으로 삭제 했습니다.");
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", e.getMessage());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/list")
    @ApiOperation(value = "question 불러오기 => pageno, keyword, tagname 전달")
    public Object getAllQuestion(@RequestParam(required = false, defaultValue = "1") int pageno,
                                 @RequestParam(required = false, defaultValue = "") String searchtype,
                                 @RequestParam(required = false, defaultValue = "") String search,
                                 @RequestParam(required = false, defaultValue = "") String tagname) {
        Map<String, Object> result = new HashMap<>();
        Pageable page = PageRequest.of(pageno-1, 10, Sort.by("questiondate").descending());
        result.put("question", questionSerivce.getAllQuestion(page, search, tagname).getContent());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/tag")
    @ApiOperation(value = "tag 포함하는 정보 불러오기 => tagname")
    public Object getTagContain(@RequestParam String tagname) {
        Map<String, Object> result = new HashMap<>();
        result.put("tag", questionSerivce.findTagContain(tagname));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/sublist")
    @ApiOperation(value = "tag/핫이슈 불러오기 => ")
    public Object getTagContain() {
        Map<String, Object> result = new HashMap<>();
        Pageable page = PageRequest.of(0, 10, Sort.by("views").descending());
        result.put("issue", questionSerivce.getHotIssue(page).getContent());
        result.put("tag", questionSerivce.getAllTag());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
