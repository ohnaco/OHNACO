package com.prossafy101.ohnaco.entity.user;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private String email;
    private String password;
    private String nickname;
    private String githubid;
    private String social;
    private String position;
}
