package com.prossafy101.ohnaco.repository;

import com.prossafy101.ohnaco.entity.tech.Article;
import com.prossafy101.ohnaco.entity.user.User;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findAll(Pageable pageable);

    Page<Article> findAllByTitleOrContentContaining(String title, String content,Pageable pageable);

    @Query(value = "select a.* from `article` a where a.blogid in (select c.blogid from `subscribe` c where c.userid=:userid) ", nativeQuery = true)
    Page<Article> findArticles(@Param("userid")String userid, Pageable pageable);

    Article findArticleByLink(String link);

    @Query(value = "select a from Article a where a.articleid in :articleids")
    List<Article> findAllByArticleids(List<Long> articleids);

}
