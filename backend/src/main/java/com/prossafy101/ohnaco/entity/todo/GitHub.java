package com.prossafy101.ohnaco.entity.todo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHub {
    private int total_count;
}
