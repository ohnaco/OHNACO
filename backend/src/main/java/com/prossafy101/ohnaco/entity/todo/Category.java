package com.prossafy101.ohnaco.entity.todo;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Category {

    @Id
    private String categoryid;

    private String categoryname;
}
