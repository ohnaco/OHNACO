package com.prossafy101.ohnaco.entity.devtalk;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prossafy101.ohnaco.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int answerid;

    private String answertitle;
    private String answercontent;
    private LocalDateTime answerdate;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "questionid")
    private Question question;
}
