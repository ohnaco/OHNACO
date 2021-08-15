package com.prossafy101.ohnaco.repository;

import com.prossafy101.ohnaco.entity.devtalk.Question;
import com.prossafy101.ohnaco.entity.devtalk.Tag;
import com.prossafy101.ohnaco.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findByQuestionid(int questionid);

    Page<Question> findAll(Pageable pageable);

    Page<Question> findAllByQuestiontitleStartingWithOrQuestioncontentStartingWith(String serach, String search, Pageable pageable);

    Page<Question> findAllByUser(User user, Pageable pageable);

    Page<Question> findAllByTag(Tag tag, Pageable pageable);

    List<Question> findAll(Sort sort);

    @Query(value = "SELECT * from question where questionid in :questionid order by field(questionid, :questionid)", nativeQuery = true)
    List<Question> getHotIssue(List<Integer> questionid);

    @Transactional
    void deleteByQuestionidAndUser(int questionid, User user);
}
