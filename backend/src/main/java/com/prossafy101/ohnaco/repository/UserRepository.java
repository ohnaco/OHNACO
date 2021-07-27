package com.prossafy101.ohnaco.repository;

import com.prossafy101.ohnaco.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
