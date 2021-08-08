package com.prossafy101.ohnaco.service;

import com.prossafy101.ohnaco.entity.devtalk.Question;
import com.prossafy101.ohnaco.entity.devtalk.QuestionDto;
import com.prossafy101.ohnaco.entity.devtalk.Tag;
import com.prossafy101.ohnaco.entity.user.User;
import com.prossafy101.ohnaco.repository.QuestionRepository;
import com.prossafy101.ohnaco.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TagRepository tagRepository;

    // 질문 저장,수정정
   public void save(QuestionDto questionDto, User user) {
        List<Tag> tagList = new ArrayList<>();
        String[] tagNames = questionDto.getTagName();
        for(String tagName : tagNames) {
            tagList.add(tagRepository.findByTagname(tagName));
        }
       if(questionDto.getQuestiondate() == null) {
           questionRepository.save(Question.builder().questiontitle(questionDto.getQuestiontitle())
                   .questioncontent(questionDto.getQuestioncontent()).questiondate(LocalDateTime.now()).views(0).likes(0).user(user).tag(tagList).build());
       } else {
           questionRepository.save(Question.builder().questionid(questionDto.getQuestionid()).questiontitle(questionDto.getQuestiontitle())
                   .questioncontent(questionDto.getQuestioncontent()).questiondate(questionDto.getQuestiondate())
                   .views(questionDto.getViews()).likes(questionDto.getLikes()).user(user).tag(tagList).build());
       }

    }

    //질문 수정
    public void updateQuestion(QuestionDto questionDto, User user) throws Exception {
        Question question = getQuestionByid(questionDto.getQuestionid());
        if(!question.getUser().getUserid().equals(user.getUserid())) throw new Exception("userid와 작성자가 일치하지 않습니다.");
        questionDto.setQuestionid(question.getQuestionid());
        questionDto.setQuestiondate(question.getQuestiondate());
        questionDto.setViews(question.getViews());  //
        questionDto.setLike(question.getLikes());
        save(questionDto, user);
    }

    //질문 삭제
    public void deleteQuestion(int questionid, User user) throws Exception {
        Question question = getQuestionByid(questionid);
        if(!question.getUser().getUserid().equals(user.getUserid())) throw new Exception("userid와 작성자가 일치하지 않습니다.");
        questionRepository.deleteByQuestionidAndUser(questionid, user);
    }

   //question id로 question entity반환
    public Question getQuestionByid(int questionid) {
        return questionRepository.findByQuestionid(questionid);
    }

    //모든 질문 반환(pageable사용 예정)
    public Page<Question> getAllQuestion(Pageable pageable, String search, String tagname) {
       if(search.equals("") && tagname.equals("")) {
           return questionRepository.findAll(pageable);
       } else if(!search.equals("")) {
           return questionRepository.findAllByQuestiontitleStartingWithOrQuestioncontentStartingWith(search, search, pageable);
       } else if(!tagname.equals("")) {
           return questionRepository.findAllByTag(tagRepository.findByTagname(tagname), pageable);
       }
       return null;
    }

    //태그 like검색 일치하는 모든 정보 반환
    public List<Tag> findTagContain(String tagname) {
       return tagRepository.findByTagnameStartingWith(tagname);
    }

    //db에 있는 태그 정보 반환
    public List<Tag> getAllTag() {
       return tagRepository.findAll();
    }
    //핫이슈 질문 반환
    public Page<Question> getHotIssue(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }
}
