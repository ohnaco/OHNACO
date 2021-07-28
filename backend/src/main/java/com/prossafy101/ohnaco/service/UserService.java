package com.prossafy101.ohnaco.service;

import com.prossafy101.ohnaco.entity.user.SignInDto;
import com.prossafy101.ohnaco.entity.user.Image;
import com.prossafy101.ohnaco.entity.user.Positions;
import com.prossafy101.ohnaco.entity.user.TempUserDto;
import com.prossafy101.ohnaco.entity.user.User;
import com.prossafy101.ohnaco.repository.ImageRepository;
import com.prossafy101.ohnaco.repository.PositionsRepository;
import com.prossafy101.ohnaco.repository.TempUserRepository;
import com.prossafy101.ohnaco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    private ImageRepository imageRepository;

    private JavaMailSender javaMailSender;

    public UserService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public User signIn(SignInDto dto) throws Exception {
        User user = userRepository.findByEmail(dto.getEmail());

        if(user == null)
            throw new Exception("회원이 존재하지 않습니다.");

        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new Exception("비밀번호가 틀립니다.");
        }

        return user;
    }

    //중복 이메일 체크
    public boolean isSameEmail(String email) {
        boolean isSameEmail = false;
        if(userRepository.findByEmail(email) != null) {
            isSameEmail = true;
        }
        return isSameEmail;
    }
    //중복 닉네임 체크
    public boolean isSameNickname(String nickname) {
        boolean isSameNickname = false;
        if(userRepository.findByNickname(nickname) != null) {
            isSameNickname = true;
        }
        return isSameNickname;
    }

    // 레디스에 이메일(기본키), 패스워드, 인증코드 저장
    public void tempUserSave(TempUserDto tempUserDto) {
        TempUserDto tempUser = tempUserDto;
        tempUser.setPassword(passwordEncoder.encode(tempUserDto.getPassword()));
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

    //image upload test
    public Image imageUpload(MultipartFile multipartFile) throws Exception {
        // 파일이 빈 것이 들어오면 빈 것을 반환
        if(multipartFile.isEmpty()){
            return null;
        }

        // 파일 이름을 업로드 한 날짜로 바꾸어서 저장할 것이다
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());

        // 프로젝트 폴더에 저장하기 위해 절대경로를 설정 (Window 의 Tomcat 은 Temp 파일을 이용한다)
        String absolutePath = System.getProperty("user.dir") + "\\";

        // 경로를 지정하고 그곳에다가 저장할 심산이다
        String path = "images/" + current_date;
        File file = new File(path);
        // 저장할 위치의 디렉토리가 존지하지 않을 경우
        if(!file.exists()){
            // mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
            file.mkdirs();
        }

        // jpeg, png, gif 파일들만 받아서 처리할 예정
        String contentType = multipartFile.getContentType();
        String originalFileExtension = "";
        // 확장자 명이 없으면 이 파일은 잘 못 된 것이다
        if (ObjectUtils.isEmpty(contentType)){
            return null;
        } else{
            if(contentType.contains("image/jpeg")){
                originalFileExtension = ".jpg";
            }
            else if(contentType.contains("image/png")){
                originalFileExtension = ".png";
            }
            else if(contentType.contains("image/gif")){
                originalFileExtension = ".gif";
            }
        }

        // 각 이름은 겹치면 안되므로 나노 초까지 동원하여 지정
        String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;

        // 저장된 파일로 변경하여 이를 보여주기 위함
        file = new File(absolutePath + path + "/" + new_file_name);
        FileCopyUtils.copy(multipartFile.getBytes(),file);
        Image image = Image.builder()
                .imagename(multipartFile.getOriginalFilename())
                .imagepath(path + "/" + new_file_name)
                .imagesize(multipartFile.getSize())
                .build();

        imageRepository.save(image);

        return image;
    }
}
