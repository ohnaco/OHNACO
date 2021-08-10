package com.prossafy101.ohnaco.repository;

import com.prossafy101.ohnaco.entity.tech.Article;
import com.prossafy101.ohnaco.entity.user.User;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findAll(Pageable pageable);

    Page<Article> findAllByTitleOrContentContaining(String title, String content,Pageable pageable);

    @Query(value = "select a.* from article a, subscribe s where (a.title like '%:title%' or a.content like '%:content%' ) and a.blogid in (select c.blogid from subscribe c where c.userid=:userid) ", nativeQuery = true)
    Page<Article> findArticles(@Param("title") String title, @Param("content") String content, @Param("userid")String userid, Pageable pageable);

    Article findArticleByLink(String link);

}
