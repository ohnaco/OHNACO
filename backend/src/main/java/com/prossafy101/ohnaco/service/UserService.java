package com.prossafy101.ohnaco.service;

import com.prossafy101.ohnaco.entity.user.SignInDto;
import com.prossafy101.ohnaco.entity.user.Positions;
import com.prossafy101.ohnaco.entity.user.TempUserDto;
import com.prossafy101.ohnaco.entity.user.User;
import com.prossafy101.ohnaco.repository.PositionsRepository;
import com.prossafy101.ohnaco.repository.TempUserRepository;
import com.prossafy101.ohnaco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Random;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TempUserRepository tempUserRepository;
    @Autowired
    private PositionsRepository positionsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private JavaMailSender javaMailSender;

    public UserService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public User signIn(SignInDto dto) throws Exception {
        User user = findByEmail(dto.getEmail());

        if(user == null)
            throw new Exception("회원이 존재하지 않습니다.");

        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new Exception("비밀번호가 틀립니다.");
        }
        return user;
    }

    //중복 이메일 체크
    public void isSameEmail(String email) throws Exception {
        if(findByEmail(email) != null) {
            throw new Exception("중복된 이메일이 존재합니다.");
        }
    }
    //중복 닉네임 체크
    public void isSameNickname(String nickname) throws Exception {
        if(userRepository.findByNickname(nickname) != null) {
            throw new Exception("중복된 닉네임이 존재합니다.");
        }
    }

    // 레디스에 이메일(기본키), 패스워드, 인증코드 저장
    public void tempUserSave(TempUserDto tempUserDto) {
        TempUserDto tempUser = tempUserDto;
        tempUser.setPassword(tempUserDto.getPassword());
        tempUserRepository.save(tempUser);
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
        tempUserRepository.deleteById(email);
    }
    //레디스에서 email 키로 찾기
    public Optional<TempUserDto> tempUserByEmail(String email) {
        return tempUserRepository.findById(email);
    }
    //이메일 보내기
    public void sendMail(String toEmail, String subject, TempUserDto tempUserDto) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setFrom("OHNACO"); //보내는사람
        helper.setTo(toEmail); //받는사람
        helper.setSubject(subject); //메일제목
        StringBuffer sb = new StringBuffer();
        sb.append("<!DOCTYPE html>");
        sb.append("<html>");
        sb.append("<head>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append(" <div" +
                "	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 400px; height: 600px; border-top: 4px solid #00BCD4; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">" +
                "	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">" +
                "		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">ONCACO</span><br />" +
                "		<span style=\"color: #00BCD4\">메일인증</span> 안내입니다." +
                "	</h1>\n" +
                "	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">" +
                "		안녕하세요.<br />" +
                "		ONACO에 가입해 주셔서 진심으로 감사드립니다.<br />");
        if(tempUserDto.getPassword() != null) {
            sb.append("		아래 <b style=\"color: #00BCD4\">'메일 인증 코드'</b>를 입력하셔서 회원가입을 완료해 주세요.<br />" +
                    "		감사합니다." +
                    "	</p>" +
                    "	<p" +
                    "		style=\"display: inline-block; width: 210px; height: 45px; margin: 30px 5px 40px; background: #607D8B; color: white; line-height: 45px; vertical-align: middle; font-size: 16px;\">" +
                    tempUserDto.getToken()
            );
        } else {
            sb.append("		아래 <b style=\"color: #02b875\">'패스워드 변경 링크'</b>를 클릭하여서 패스워드 변경을 완료해 주세요.<br />" +
                    "		감사합니다." +
                    "	</p>" +
                    "	<p" +
                    "		style=\"display: inline-block; width: 210px; height: 45px; margin: 30px 5px 40px; background: #02b875; line-height: 45px; vertical-align: middle; font-size: 16px;\">"
            );
            sb.append("<a href='https://i5a101.p.ssafy.io/findpwdnewpwd?email=" +
                    toEmail +
                    "&code=" +
                    tempUserDto.getToken() +
                    "' target='_blank'>이메일 인증 확인</a>"
            );
        }
        sb.append("		</p>" +
                "	<div style=\"border-top: 1px solid #DDD; padding: 5px;\"></div>" +
                " </div>");
        sb.append("</body>");
        sb.append("</html>");
        helper.setText(sb.toString(), true); //ture넣을경우 html

        javaMailSender.send(mimeMessage);
    }

    //유저 정보 저장
    public void userSave(User user) {
        userRepository.save(user);
    }

    //유저아이디 랜덤값 생성
    //이메일 랜덤 대문자 + 숫자 8개 choice
    public String createUserid() {
        StringBuffer sb = new StringBuffer();
        Random rand = new Random();
        do {
            for (int i = 0; i < 13; i++) {
                int randNum = rand.nextInt(2);
                if (randNum == 0) {
                    sb.append((char) ((int) (rand.nextInt(26) + 'A')));
                } else {
                    sb.append(rand.nextInt(10));
                }
            }
        } while(userRepository.findByUserid(sb.toString()) != null);
        return sb.toString();
    }

    //position명으로 id값 찾기
    public Positions positionsName(String position) {
        return positionsRepository.findByPositionname(position);
    }

    //임시저장 및 이메일 전송
    public void tempSaveAndSendEmail(TempUserDto tempUser, String toEmail) throws Exception {
        try {
            tempUserRepository.save(tempUser);
        } catch(Exception e) {
            throw new Exception("임시회원 저장 오류.");
        }
        try {
            //********************************유저 이메일로 변경해줘야함!!************************
            sendMail(toEmail,"[OHNACO 이메일 인증 코드]", tempUser);

        } catch(MessagingException e) {
            throw new Exception("메일 보내기 오류.");
        }
    }

    //패스워드 찾기 redis에 저장된 값과 비교
    public boolean isTokenConfirm(String email, String token) throws Exception {
        Optional<TempUserDto> tempUserDto;
        try {
            tempUserDto = tempUserRepository.findById("findPwd:" + email);
        } catch (Exception e) {
            throw new Exception("이메일 찾기 오류");
        }
        if(tempUserDto.isPresent()) {
            if(tempUserDto.get().getToken().equals(token)) {
                return true;
            }
        }
        return false;
    }

    // 유저 이메일로 찾기
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //유저 아이디로 찾기
    public User findByUserid(String userid) {
        return userRepository.findByUserid(userid);
    }

    //패스워드 체크
    public boolean checkPassword(String password, User user) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    public void deleteUser(String userid) {
        userRepository.deleteByUserid(userid);
    }
}
