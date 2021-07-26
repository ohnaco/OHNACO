package com.prossafy101.ohnaco.service;

import com.prossafy101.ohnaco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public boolean isSameEmail(String email) {
        boolean isSameEmail = false;
        if(userRepo.findByEmail(email) != null) {
            isSameEmail = true;
        }
        return isSameEmail;
    }

}
