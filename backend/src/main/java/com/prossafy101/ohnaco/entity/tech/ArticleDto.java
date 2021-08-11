package com.prossafy101.ohnaco.entity.tech;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.text.StringEscapeUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    private Long articleid;
    private String title;
    private String content;
    private String link;
    private Long blogid;
    private String image;
    private LocalDateTime publisheddate;
    private boolean isScrap;

    public ArticleDto(Article article, boolean isScrap) {
        this.articleid = article.getArticleid();
        this.title = article.getTitle();
        this.content = StringEscapeUtils.unescapeHtml4(article.getContent());
        this.link = article.getLink();
        this.blogid = article.getBlogid();
        this.image = article.getImage();
        this.publisheddate = article.getPublisheddate();
        this.isScrap = isScrap;
    }

}
