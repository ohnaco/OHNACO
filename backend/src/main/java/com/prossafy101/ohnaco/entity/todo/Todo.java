package com.prossafy101.ohnaco.entity.todo;

import com.prossafy101.ohnaco.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long todoid;

    private String title;
    private Date date;
    private Time goaltime;
    private Time completetime;
    private Boolean issuccess;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

}
