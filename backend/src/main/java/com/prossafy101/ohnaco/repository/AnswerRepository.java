package com.prossafy101.ohnaco.repository;

import com.prossafy101.ohnaco.entity.devtalk.Answer;
import com.prossafy101.ohnaco.entity.devtalk.Question;
import com.prossafy101.ohnaco.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    @Transactional
    void deleteAnswerByAnsweridAndUser(int answerid, User user);

    List<Answer> findAllByQuestion(Question question);
}
