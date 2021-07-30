package com.prossafy101.ohnaco.repository;

import com.prossafy101.ohnaco.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByNickname(String nickname);
    User save(User user);
    User findByUserid(String userid);
}
