package com.prossafy101.ohnaco.service;

import com.prossafy101.ohnaco.entity.devtalk.Answer;
import com.prossafy101.ohnaco.entity.devtalk.AnswerDto;
import com.prossafy101.ohnaco.entity.user.User;
import com.prossafy101.ohnaco.repository.AnswerRepository;
import com.prossafy101.ohnaco.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

//    @Autowired
//    private NotificationService notificationService;

    public Answer createAnswer(AnswerDto dto, User user) {
//        try {
//            notificationService.answerNofify(questionRepository.findById(dto.getQuestionid()).get().getUser().getUserid());
//        } catch( Exception e) {
//            e.printStackTrace();
//        }

        return answerRepository.save(Answer.builder().answercontent(dto.getAnswercontent())
                .answerdate(LocalDateTime.now()).likes(dto.getLikes()).question(questionRepository.findByQuestionid(dto.getQuestionid())).user(user).build());
    }


    public Answer updateAnswer(AnswerDto dto, String userid) throws Exception {
        Optional<Answer> opt = answerRepository.findById(dto.getAnswerid());
        Answer answer = opt.get();

        if(!answer.getUser().getUserid().equals(userid)) {
            throw new Exception("userid");
        }

        return answerRepository.save(Answer.builder()
                .answerdate(answer.getAnswerdate())
                .answercontent(dto.getAnswercontent())
                .answerid(answer.getAnswerid())
                .likes(answer.getLikes())
                .user(answer.getUser())
                .question(answer.getQuestion())
                .build());
    }

    public List<Answer> getAnswers(int questionid) {
        return answerRepository.findAllByQuestion(questionRepository.findByQuestionid(questionid));
    }

    public void deleteAnswer(int answerid, User user) throws Exception {
        if(!answerRepository.findById(answerid).get().getUser().getUserid().equals(user.getUserid()))
            throw new Exception("userid와 작성자가 일치하지 않습니다.");

        answerRepository.deleteAnswerByAnsweridAndUser(answerid, user);
    }

    public Page<Answer> getAnswerByUser(User user, Pageable pageable) {
        return answerRepository.findAllByUser(user, pageable);
    }

    public int getCountAnswer(int questionid) {
        return answerRepository.answerCount(questionid);
    }

    public List<Answer> getAnswerOrder() {
        return answerRepository.answerOrder();
    }
}
