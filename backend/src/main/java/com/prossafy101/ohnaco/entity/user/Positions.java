package com.prossafy101.ohnaco.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Positions {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Id
    private int positionid;
    private String positionname;
}
