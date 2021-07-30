package com.prossafy101.ohnaco.repository;

import com.prossafy101.ohnaco.entity.user.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
