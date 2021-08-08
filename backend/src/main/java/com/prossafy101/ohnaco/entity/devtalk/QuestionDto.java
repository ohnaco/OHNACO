package com.prossafy101.ohnaco.entity.devtalk;

import com.prossafy101.ohnaco.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private int questionid;
    private String questiontitle;
    private String questioncontent;
    private LocalDateTime questiondate;
    private int views;
    private String[] tagName;
    private int likes;

    private boolean userLike;
    private long like;
    private String visit;
    private User user;
    private List<Tag> tag;
}
