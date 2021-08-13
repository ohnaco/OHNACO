package com.prossafy101.ohnaco.entity.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindPasswordDto {
    private String email;
    private String password;
    private String code;
}