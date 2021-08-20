package com.prossafy101.ohnaco.controller;

import com.prossafy101.ohnaco.entity.devtalk.Answer;
import com.prossafy101.ohnaco.entity.devtalk.AnswerDto;
import com.prossafy101.ohnaco.entity.devtalk.Question;
import com.prossafy101.ohnaco.entity.devtalk.QuestionDto;
import com.prossafy101.ohnaco.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/devtalk")
public class DevtalkController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    String visitKey = "devtalk:question:visit:";
    String likeQuestionKey = "devtalk:question:like:";
    String likeAnswerKey = "devtalk:answer:like:";

    @PostMapping("/question")
    @ApiOperation(value = "question 저장 => questiontitle, questioncontent, tagName 배열 전달")
    public Object save(@RequestBody QuestionDto questionDto, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        Question question = questionService.save(questionDto, userService.findByUserid(userid));
        redisUtil.setInitalVisitData(visitKey+question.getQuestionid());
        result.put("status", "success");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/question")
    @ApiOperation(value = "question 불러오기 및 관련 List<answer> 불러오기 => questionid 전달")
    public Object getQuestion(@RequestParam int questionid, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);

        redisUtil.setVisitData(visitKey+questionid);
        Question question = questionService.getQuestionByid(questionid);
        List<Answer> answers = answerService.getAnswers(questionid);
        List<AnswerDto> answerDto = new ArrayList<>();
        for(Answer answer : answers) {
            answerDto.add(AnswerDto.builder().answerid(answer.getAnswerid())
                    .answercontent(answer.getAnswercontent()).answerdate(answer.getAnswerdate())
                    .user(answer.getUser()).userLike(redisUtil.getLikeUseridData(likeAnswerKey+answer.getAnswerid(), userid))
                    .like(redisUtil.getLikeCountData(likeAnswerKey+answer.getAnswerid())).build());
        }
        result.put("question",QuestionDto.builder().questionid(question.getQuestionid()).questiontitle(question.getQuestiontitle())
                .questioncontent(question.getQuestioncontent()).questiondate(question.getQuestiondate()).user(question.getUser())
                .tag(question.getTag()).visit(redisUtil.getData(visitKey+questionid))
                .userLike(redisUtil.getLikeUseridData(likeQuestionKey+questionid, userid)).like(redisUtil.getLikeCountData(likeQuestionKey+questionid)).build());
        result.put("answer", answerDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/question/like")
    public Object questionLike(@RequestBody Map<String, String> map, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        //좋아요 한 유저가 다시누를시 삭제하기위해서 이부분 지우면 좋아요는 딱한번 설정된는것
        if(redisUtil.getLikeUseridData(likeQuestionKey+map.get("questionid"), userid)) {
            redisUtil.delLikeUserData(likeQuestionKey+map.get("questionid"), userid);
        } else {
            redisUtil.setLikeData(likeQuestionKey+map.get("questionid"), userid);
        }
        result.put("like", redisUtil.getLikeCountData(likeQuestionKey+map.get("questionid")));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/answer/like")
    public Object answerLike(@RequestBody Map<String, String> map, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        //좋아요 한 유저가 다시누를시 삭제하기위해서 이부분 지우면 좋아요는 딱한번 설정된는것
        if(redisUtil.getLikeUseridData(likeAnswerKey+map.get("answerid"), userid)) {
            redisUtil.delLikeUserData(likeAnswerKey+map.get("answerid"), userid);
        } else {
            redisUtil.setLikeData(likeAnswerKey+map.get("answerid"), userid);
        }
        result.put("like", redisUtil.getLikeCountData(likeAnswerKey+map.get("answerid")));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/question")
    @ApiOperation(value = "question 수정 => questionid, questiontitle, questioncontent, tagName 배열 전달")
    public Object updateQuestion(@RequestBody QuestionDto questionDto, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        try {
            questionService.updateQuestion(questionDto, userService.findByUserid(userid));
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
        //*********************questionid로 연관된 answerid찾아서 담아두기******************
        List<Answer> answers = answerService.getAnswers(questionid);
        for(Answer answer : answers) {
            redisUtil.deleteData(likeAnswerKey+answer.getAnswerid());
        }
        try {
            questionService.deleteQuestion(questionid, userService.findByUserid(userid));
            redisUtil.deleteData(visitKey+questionid);
            redisUtil.deleteData(likeQuestionKey+questionid);
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
                                 @RequestParam(required = false, defaultValue = "") String tagname,
                                 HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        Pageable page = PageRequest.of(pageno-1, 10, Sort.by("questiondate").descending());
        List<Question> questions = questionService.getAllQuestion(page, search, tagname).getContent();
        List<QuestionDto> questionDtos = new ArrayList<>();
        for(Question question : questions) {
            questionDtos.add(QuestionDto.builder().questionid(question.getQuestionid()).questiontitle(question.getQuestiontitle())
                    .questioncontent(question.getQuestioncontent()).questiondate(question.getQuestiondate()).user(question.getUser())
                    .tag(question.getTag()).visit(redisUtil.getData(visitKey+question.getQuestionid()))
                    .userLike(redisUtil.getLikeUseridData(likeQuestionKey+question.getQuestionid(), userid)).like(redisUtil.getLikeCountData(likeQuestionKey+question.getQuestionid())).build());
        }
        result.put("question", questionDtos);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "question 최신순 불러오기")
    public Object getAllQuestionOrder(HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        List<Question> questions = questionService.getAllQuestionSort(Sort.by("questiondate").descending());
        List<QuestionDto> questionDtos = new ArrayList<>();
        for(Question question : questions) {
            questionDtos.add(QuestionDto.builder().questionid(question.getQuestionid()).questiontitle(question.getQuestiontitle())
                    .questioncontent(question.getQuestioncontent()).questiondate(question.getQuestiondate()).user(question.getUser())
                    .tag(question.getTag()).visit(redisUtil.getData(visitKey+question.getQuestionid()))
                    .userLike(redisUtil.getLikeUseridData(likeQuestionKey+question.getQuestionid(), userid)).like(redisUtil.getLikeCountData(likeQuestionKey+question.getQuestionid()))
                    .answercount(answerService.getCountAnswer(question.getQuestionid())).build());
        }
        result.put("question", questionDtos);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/load")
    @ApiOperation(value = "question 불러오기")
    public Object getAllQuestionSort(HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        List<Question> questions = questionService.getAllQuestionSort(Sort.by("questiondate").descending());
        List<QuestionDto> questionDtos = new ArrayList<>();
        for(Question question : questions) {
            questionDtos.add(QuestionDto.builder().questionid(question.getQuestionid()).questiontitle(question.getQuestiontitle())
                    .questioncontent(question.getQuestioncontent()).questiondate(question.getQuestiondate()).user(question.getUser())
                    .tag(question.getTag()).visit(redisUtil.getData(visitKey+question.getQuestionid()))
                    .userLike(redisUtil.getLikeUseridData(likeQuestionKey+question.getQuestionid(), userid)).like(redisUtil.getLikeCountData(likeQuestionKey+question.getQuestionid())).build());
        }
        result.put("question", questionDtos);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/tag")
    @ApiOperation(value = "tag 포함하는 정보 불러오기 => tagname")
    public Object getTagContain(@RequestParam String tagname) {
        Map<String, Object> result = new HashMap<>();
        result.put("tag", questionService.findTagContain(tagname));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/sublist")
    @ApiOperation(value = "tag/핫이슈 불러오기 => ")
    public Object getTagContain() {
        Map<String, Object> result = new HashMap<>();
        List<Answer> answers = answerService.getAnswerOrder();
        List<Integer> questionids = new ArrayList<>();
        for(Answer answer: answers) {
            questionids.add(answer.getQuestion().getQuestionid());
            System.out.println(answer.getQuestion().getQuestionid());
        }
        result.put("issue", questionService.getHotIssue(questionids));
        result.put("tag", questionService.getAllTag());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("/answer")
    @ApiOperation(value ="답변 작성하기 => questionid, answercontent 전달")
    public Object createAnswer(@RequestBody AnswerDto dto, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);

        result.put("answer", answerService.createAnswer(dto, userService.findByUserid(userid)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/answer")
    @ApiOperation(value ="답변 수정하기 => answerid, answercontent 전달")
    public Object updateAnswer(@RequestBody AnswerDto dto,HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);

        try {
            result.put("answer", answerService.updateAnswer(dto, userid));
            result.put("status", "success");
        } catch(Exception e) {
            result.put("status", "fail");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/answer")
    @ApiOperation(value ="답변 삭제하기 => answerid 전달")
    public Object deleteAnswer(@RequestParam int answerid,HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        try {
            answerService.deleteAnswer(answerid, userService.findByUserid(userid));
            redisUtil.deleteData(likeAnswerKey+answerid);
            result.put("status", true);
            result.put("message", "성공적으로 삭제 했습니다.");
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", e.getMessage());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
