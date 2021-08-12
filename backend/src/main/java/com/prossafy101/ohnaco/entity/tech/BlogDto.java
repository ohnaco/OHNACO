package com.prossafy101.ohnaco.entity.tech;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto {

    private Long blogid;
    private String blogname;
    private String description;
    private String link;
    private boolean issubscribe;
}
