package com.prossafy101.ohnaco.repository;

import com.prossafy101.ohnaco.entity.todo.Category;
import com.prossafy101.ohnaco.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, Long> {

    Category findByCategoryid(String categoryid);
}
