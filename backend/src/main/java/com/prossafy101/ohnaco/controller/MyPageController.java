package com.prossafy101.ohnaco.controller;

import com.prossafy101.ohnaco.entity.devtalk.Answer;
import com.prossafy101.ohnaco.entity.devtalk.AnswerDto;
import com.prossafy101.ohnaco.entity.devtalk.Question;
import com.prossafy101.ohnaco.entity.devtalk.QuestionDto;
import com.prossafy101.ohnaco.entity.tech.Article;
import com.prossafy101.ohnaco.entity.tech.ArticleDto;
import com.prossafy101.ohnaco.entity.user.User;
import com.prossafy101.ohnaco.entity.user.UserDto;
import com.prossafy101.ohnaco.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    String visitKey = "devtalk:question:visit:";
    String likeQuestionKey = "devtalk:question:like:";
    String likeAnswerKey = "devtalk:answer:like:";

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

        Page<Question> questions = questionService.getQuestionByUser(user, PageRequest.of(0, 5, Sort.by("questiondate").descending()));
        List<Question> questionList = questions.getContent();
        List<QuestionDto> questionDtos = new ArrayList<>();
        for(Question question : questionList) {
            questionDtos.add(QuestionDto.builder().questionid(question.getQuestionid()).questiontitle(question.getQuestiontitle())
                    .questioncontent(question.getQuestioncontent()).questiondate(question.getQuestiondate()).user(question.getUser())
                    .tag(question.getTag()).visit(redisUtil.getData(visitKey+question.getQuestionid()))
                    .userLike(redisUtil.getLikeUseridData(likeQuestionKey+question.getQuestionid(), userid)).like(redisUtil.getLikeCountData(likeQuestionKey+question.getQuestionid())).build());
        }

        Page<Answer> answers = answerService.getAnswerByUser(user, PageRequest.of(0, 5, Sort.by("answerdate").descending()));
        List<Answer> answerList = answers.getContent();
        List<AnswerDto> answerDto = new ArrayList<>();
        for(Answer answer : answerList) {
            answerDto.add(AnswerDto.builder().answerid(answer.getAnswerid()).answertitle(answer.getAnswertitle())
                    .answercontent(answer.getAnswercontent()).answerdate(answer.getAnswerdate())
                    .user(answer.getUser()).userLike(redisUtil.getLikeUseridData(likeAnswerKey+answer.getAnswerid(), userid))
                    .like(redisUtil.getLikeCountData(likeAnswerKey+answer.getAnswerid())).build());
        }

        result.put("question", questionDtos);
        result.put("questionCount", questions.getTotalElements());
        result.put("answer",answerDto);
        result.put("answerCount", answers.getTotalElements());
        List<String> scraps = redisUtil.getScrapData("tech:scrap:" + userid, 0, 4);
        List<Long> articleids = new ArrayList<>();
        for(String scrap : scraps) {
            articleids.add(Long.parseLong(scrap));
        }
        List<ArticleDto> articleDtos = new ArrayList<>();
        List<Article> articles = techService.getFindArticleId(articleids);
        for(Article article : articles) {
            articleDtos.add(new ArticleDto(article, true, true));
        }
        result.put("scrap",articleDtos);
        result.put("scrapCount", redisUtil.getScrapSizeData("tech:scrap:" + userid));
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
        List<Answer> answers = answerService.getAnswerByUser(userService.findByUserid(userid), PageRequest.of(pageno-1, 10, Sort.by("answerdate").descending())).getContent();
        List<AnswerDto> answerDto = new ArrayList<>();
        for(Answer answer : answers) {
            answerDto.add(AnswerDto.builder().answerid(answer.getAnswerid()).answertitle(answer.getAnswertitle())
                    .answercontent(answer.getAnswercontent()).answerdate(answer.getAnswerdate()).questionid(answer.getQuestion().getQuestionid())
                    .user(answer.getUser()).userLike(redisUtil.getLikeUseridData(likeAnswerKey+answer.getAnswerid(), userid))
                    .like(redisUtil.getLikeCountData(likeAnswerKey+answer.getAnswerid())).build());
        }
        result.put("answer",answerDto);
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
            articleDtos.add(new ArticleDto(article, true, true));
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
