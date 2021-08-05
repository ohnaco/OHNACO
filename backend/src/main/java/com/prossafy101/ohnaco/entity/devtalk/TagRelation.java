package com.prossafy101.ohnaco.entity.devtalk;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TagRelation {
    @Id
    private int qestionid;
    @ManyToOne
    @JoinColumn(name = "tagid")
    private Tag tag;
}
