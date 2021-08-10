package com.prossafy101.ohnaco.repository;

import com.prossafy101.ohnaco.entity.devtalk.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findByTagname(String tagname);
    List<Tag> findByTagnameStartingWith(String tagname);
}
