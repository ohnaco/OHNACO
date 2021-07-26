package com.prossafy101.ohnaco.service;

import com.prossafy101.ohnaco.entity.user.TempUserDto;
import com.prossafy101.ohnaco.repository.TempUserRepository;
import com.prossafy101.ohnaco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private TempUserRepository tempUserRepo;

    //중복 이메일 체크
    public boolean isSameEmail(String email) {
        boolean isSameEmail = false;
        if(userRepo.findByEmail(email) != null) {
            isSameEmail = true;
        }
        return isSameEmail;
    }
    // 레디스에 이메일(기본키), 패스워드, 인증코드 저장
    public void tempUserSave(TempUserDto tempUserDto) {
        tempUserRepo.save(tempUserDto);
    }
    //이메일 랜덤 대문자 + 숫자 8개 choice
    public String randomCode() {
        StringBuffer sb = new StringBuffer();
        Random rand = new Random();
        for(int i=0; i<8; i++) {
            int randNum = rand.nextInt(2);
            if(randNum == 0) {
                sb.append((char)((int)(rand.nextInt(26)+'A')));
            } else {
                sb.append(rand.nextInt(10));
            }
        }
        return sb.toString();
    }
    //레디스에서 email 키로 삭제
    public void tempUserDelete(String email) {
        tempUserRepo.deleteById(email);
    }
    //레디스에서 email 키로 찾기
    public Optional<TempUserDto> tempUserByEmail(String email) {
        return tempUserRepo.findById(email);
    }

}
