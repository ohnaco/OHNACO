package com.prossafy101.ohnaco.entity.devtalk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Tag {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Id
    private int tagid;
    private String tagname;
}
