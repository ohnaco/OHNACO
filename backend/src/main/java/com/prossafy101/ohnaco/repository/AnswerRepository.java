package com.prossafy101.ohnaco.repository;

import com.prossafy101.ohnaco.entity.devtalk.Answer;
import com.prossafy101.ohnaco.entity.devtalk.Question;
import com.prossafy101.ohnaco.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    @Transactional
    void deleteAnswerByAnsweridAndUser(int answerid, User user);

    List<Answer> findAllByQuestion(Question question);

    Page<Answer> findAllByUser(User user, Pageable pageable);

    @Query(value = "SELECT count(*) from answer where questionid = :questionid", nativeQuery = true)
    int answerCount(int questionid);

    @Query(value = "SELECT * from answer group by questionid order by COUNT(questionid) DESC limit 10", nativeQuery = true)
    List<Answer> answerOrder();
}
