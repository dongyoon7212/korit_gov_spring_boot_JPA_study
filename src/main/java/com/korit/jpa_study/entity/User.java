package com.korit.jpa_study.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_tb")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;
}
/*
* 추가 (username 중복검사)
* 조회 - 전체조회, userId단건조회, username단건조회
* 수정 - password 또는 이메일
* 삭제
* */
