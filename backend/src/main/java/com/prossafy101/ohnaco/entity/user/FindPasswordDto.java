package com.prossafy101.ohnaco.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindPasswordDto {
    private String email;
    private String password;
    private String code;
}
