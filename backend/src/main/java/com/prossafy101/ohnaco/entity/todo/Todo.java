package com.prossafy101.ohnaco.entity.todo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prossafy101.ohnaco.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    @Id
    private String todoid;

    private String title;
    private LocalDateTime date;
    private Time goaltime;
    private Time completetime;
    private Boolean issuccess;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

}
