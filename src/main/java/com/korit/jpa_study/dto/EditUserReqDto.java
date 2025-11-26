package com.korit.jpa_study.dto;

import com.korit.jpa_study.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditUserReqDto {
    private Integer userId;
    private String password;
    private String email;
}
