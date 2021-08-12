package com.prossafy101.ohnaco.repository;

import com.prossafy101.ohnaco.entity.tech.Blog;
import com.prossafy101.ohnaco.entity.tech.BlogDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    List<Blog> findAll();


}
