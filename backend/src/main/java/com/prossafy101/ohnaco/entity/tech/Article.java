package com.prossafy101.ohnaco.entity.tech;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long articleid;

    private String title;
    private String content;
    private String link;

    @JoinColumn(name = "blogid")
    private Long blogid;

    private String image;
    private LocalDateTime publisheddate;


}
