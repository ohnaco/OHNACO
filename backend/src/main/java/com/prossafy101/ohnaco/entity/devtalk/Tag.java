package com.prossafy101.ohnaco.entity.devtalk;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tag {
    @Id
    private int tagid;
    private String tagname;
}
