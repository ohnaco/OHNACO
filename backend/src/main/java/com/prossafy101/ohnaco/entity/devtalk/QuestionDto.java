package com.prossafy101.ohnaco.entity.devtalk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
}
