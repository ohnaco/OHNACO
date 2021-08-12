package com.prossafy101.ohnaco.entity.tech;

import com.prossafy101.ohnaco.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long blogid;

    private String blogname;
    private String description;
    private String rss;
    private String link;


    @ManyToMany
    @JoinTable(name = "subscribe",
           joinColumns = @JoinColumn(name = "blogid"),
           inverseJoinColumns = @JoinColumn(name = "userid"))
    private List<User> users = new ArrayList<>();
}
