package com.prossafy101.ohnaco.entity.devtalk;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prossafy101.ohnaco.entity.user.User;
import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionid;
    private String questiontitle;
    private String questioncontent;
    private LocalDateTime questiondate;
    private int view;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @OneToMany
    @JoinColumn(name = "questionid")
    private List<TagRelation> tagRelation;
}
