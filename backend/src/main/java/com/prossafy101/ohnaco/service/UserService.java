package com.prossafy101.ohnaco.service;

import com.prossafy101.ohnaco.entity.user.SignInDto;
import com.prossafy101.ohnaco.entity.user.User;
import com.prossafy101.ohnaco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User signIn(SignInDto dto) throws Exception {
        User user = userRepository.findByEmail(dto.getEmail());

        if(user == null)
            throw new Exception("회원이 존재하지 않습니다.");

        if(!user.getPassword().equals(dto.getPassword()))
            throw new Exception("비밀번호가 틀립니다.");

        return user;
    }
}
