package com.prossafy101.ohnaco.entity.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String userid;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    private String githubid;
    private String social;

    @OneToOne
    @JoinColumn(name = "positionid")
    private Positions positions;

//    private String imageUrl;
}
