package com.prossafy101.ohnaco.entity.devtalk;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prossafy101.ohnaco.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
    private int answerid;

    private String answercontent;
    private int questionid;
    private LocalDateTime answerdate;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int likes;

    private boolean userLike;
    private long like;
    private User user;
}
