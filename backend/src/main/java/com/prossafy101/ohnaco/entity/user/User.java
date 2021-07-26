package com.prossafy101.ohnaco.entity.user;

import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Getter
@Service
public class User {

    @Id
    @GeneratedValue
    private Long userId;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    private String githubId;
    private String social;
    private String image;

//    @OneToOne
//    @JoinColumn(name="positionId")
//    private Position position;
}
