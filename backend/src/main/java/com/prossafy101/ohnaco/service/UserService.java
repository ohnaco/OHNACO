package com.prossafy101.ohnaco.service;

import com.prossafy101.ohnaco.entity.user.TempUserDto;
import com.prossafy101.ohnaco.repository.TempUserRepository;
import com.prossafy101.ohnaco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private TempUserRepository tempUserRepo;

    private JavaMailSender javaMailSender;
    public UserService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

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
    //이메일 보내기
    public void sendMail(String toEmail, String subject, String emailCode) throws MessagingException {
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
        sb.append(
                " <div" 																																																	+
                        "	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 400px; height: 600px; border-top: 4px solid #02b875; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">"		+
                        "	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">"																															+
                        "		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">ONCACO</span><br />"																													+
                        "		<span style=\"color: #02b875\">메일인증</span> 안내입니다."																																				+
                        "	</h1>\n"																																																+
                        "	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">"																													+
                        "		안녕하세요.<br />"																																													+
                        "		ONACO에 가입해 주셔서 진심으로 감사드립니다.<br />"																																						+
                        "		아래 <b style=\"color: #02b875\">'메일 인증 코드'</b>를 입력하셔서 회원가입을 완료해 주세요.<br />"																													+
                        "		감사합니다."																																															+
                        "	</p>"																																																	+
                        "	<p"																																																	+
                        "		style=\"display: inline-block; width: 210px; height: 45px; margin: 30px 5px 40px; background: #02b875; line-height: 45px; vertical-align: middle; font-size: 16px;\">"							+
                        emailCode                                                                                                                                                                                           +
                        "		</p>"																																														+
                        "	<div style=\"border-top: 1px solid #DDD; padding: 5px;\"></div>"																																		+
                        " </div>"
        );
        sb.append("</body>");
        sb.append("</html>");
        helper.setText(sb.toString(), true); //ture넣을경우 html

        javaMailSender.send(mimeMessage);
    }

}
