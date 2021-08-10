package com.prossafy101.ohnaco.controller;

import com.prossafy101.ohnaco.entity.tech.Article;
import com.prossafy101.ohnaco.entity.tech.ArticleDto;
import com.prossafy101.ohnaco.entity.user.User;
import com.prossafy101.ohnaco.entity.user.UserDto;
import com.prossafy101.ohnaco.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/mypage")
public class MyPageController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private TodoService todoService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TechService techService;

    @GetMapping("/list")
    @ApiOperation(value = "내 정보, 질문, 답변, 스크랩트 정보 가져오기 (최신 5개씩)")
    public Object getMyInfoList(HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        User user = userService.findByUserid(userid);
        result.put("info", user);
        if(user.getGithubid() == null) {
            result.put("commit", null);
        } else {
            todoService.commitUpdate(userid, user.getGithubid(), LocalDate.now().toString());
            result.put("commit", todoService.getCommit(userid));
        }
        result.put("question", questionService.getQuestionByUser(user, PageRequest.of(0, 5, Sort.by("questiondate").descending())).getContent());
        result.put("answer",answerService.getAnswerByUser(user, PageRequest.of(0, 5, Sort.by("answerdate").descending())).getContent());
        List<String> scraps = redisUtil.getScrapData("tech:scrap:" + userid, 0, 4);
        List<Long> articleids = new ArrayList<>();
        for(String scrap : scraps) {
            articleids.add(Long.parseLong(scrap));
        }
        List<ArticleDto> articleDtos = new ArrayList<>();
        List<Article> articles = techService.getFindArticleId(articleids);
        for(Article article : articles) {
            articleDtos.add(new ArticleDto(article, true));
        }
        result.put("scrap",articleDtos);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/question")
    @ApiOperation(value = "내 질문 정보 가져오기 (최신 10개씩)")
    public Object getMyQuestion(@RequestParam(required = false, defaultValue = "1") int pageno, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        result.put("question", questionService.getQuestionByUser(userService.findByUserid(userid), PageRequest.of(pageno-1, 10, Sort.by("questiondate").descending())).getContent());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/answer")
    @ApiOperation(value = "내 답변 정보 가져오기 (최신 10개씩)")
    public Object getMyAnswer(@RequestParam(required = false, defaultValue = "1") int pageno, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        result.put("answer",answerService.getAnswerByUser(userService.findByUserid(userid), PageRequest.of(pageno-1, 10, Sort.by("answerdate").descending())).getContent());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/scrap")
    @ApiOperation(value = "내 스크랩트 정보 가져오기 (최신 10개씩)")
    public Object getMyScrap(@RequestParam(required = false, defaultValue = "1") int pageno, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        List<String> scraps = redisUtil.getScrapData("tech:scrap:" + userid, (pageno-1)*10, (pageno)*10-1);
        List<Long> articleids = new ArrayList<>();
        for(String scrap : scraps) {
            articleids.add(Long.parseLong(scrap));
        }
        List<ArticleDto> articleDtos = new ArrayList<>();
        List<Article> articles = techService.getFindArticleId(articleids);
        for(Article article : articles) {
            articleDtos.add(new ArticleDto(article, true));
        }
        result.put("scrap",articleDtos);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/info")
    @ApiOperation(value = "내 정보 정보 가져오기")
    public Object getMyInfo(HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        User user = userService.findByUserid(userid);
        result.put("info", UserDto.builder().email(user.getEmail()).nickname(user.getNickname()).githubid(user.getGithubid()).position(user.getPositions().getPositionname()).image(user.getImage()).build());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/namecheck")
    @ApiOperation(value = "닉네임 중복 체크")
    public Object checkNickname(@RequestParam(required = true) String nickname) {
        Map<String, Object> result = new HashMap<>();

        try {
            userService.isSameNickname(nickname);
            result.put("status", true);
            result.put("message", "중복된 닉네임이 없습니다.");
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", e.getMessage());
        }

        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @PutMapping("/info")
    @ApiOperation(value = "내 정보 변경 nickname,githubid,position")
    public Object changeMyInfo(@RequestBody UserDto userDto, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        User user = userService.findByUserid(userid);
        userService.userSave(User.builder().userid(user.getUserid()).email(user.getEmail()).password(user.getPassword())
                .nickname(userDto.getNickname()).githubid(userDto.getGithubid()).positions(userService.positionsName(userDto.getPosition())).build());
        result.put("status", true);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/changepwd")
    @ApiOperation(value = "비밀번호 변경 password, newpassword")
    public Object changePassword(@RequestBody Map<String, String> map, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        User user = userService.findByUserid(userid);
        boolean check = userService.checkPassword(map.get("password"), user);
        if(check) {
            userService.userSave(User.builder().userid(user.getUserid()).email(user.getEmail()).password(passwordEncoder.encode(map.get("newpassword")))
                    .nickname(user.getNickname()).githubid(user.getGithubid()).positions(user.getPositions()).build());
            result.put("status", true);
        } else {
            result.put("status", false);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
