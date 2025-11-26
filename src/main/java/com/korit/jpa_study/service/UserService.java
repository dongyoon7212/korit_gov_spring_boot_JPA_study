package com.korit.jpa_study.service;

import com.korit.jpa_study.dto.ApiRespDto;
import com.korit.jpa_study.dto.EditUserReqDto;
import com.korit.jpa_study.dto.SignupReqDto;
import com.korit.jpa_study.entity.User;
import com.korit.jpa_study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ApiRespDto<?> signup(SignupReqDto signupReqDto) {
        Optional<User> foundUser = userRepository.findByUsername(signupReqDto.getUsername());
        if (foundUser.isPresent()) {
            return new ApiRespDto<>("failed", "username이 중복되었습니다.", null);
        }
        return new ApiRespDto<>("success", "회원가입이 완료되었습니다.", userRepository.save(signupReqDto.toEntity()));
    }

    public ApiRespDto<?> getUserAll() {
        return new ApiRespDto<>("success", "전체조회 완료", userRepository.findAll());
    }

    public ApiRespDto<?> getUserByUserId(Integer userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isEmpty()) {
            return new ApiRespDto<>("failed", "조회된 회원정보가 없습니다.", null);
        }
        return new ApiRespDto<>("success", "회원정보 조회 완료", foundUser.get());
    }

    public ApiRespDto<?> getUserByUsername(String username) {
        Optional<User> foundUser = userRepository.findByUsername(username);
        if (foundUser.isEmpty()) {
            return new ApiRespDto<>("failed", "조회된 회원정보가 없습니다.", null);
        }
        return new ApiRespDto<>("success", "회원정보 조회 완료", foundUser.get());
    }

    public ApiRespDto<?> editUser(EditUserReqDto editUserReqDto) {
        Optional<User> foundUser = userRepository.findById(editUserReqDto.getUserId());
        if (foundUser.isEmpty()) {
            return new ApiRespDto<>("failed", "가입된 회원정보가 없습니다.", null);
        }
        User user = foundUser.get();
        user.setPassword(editUserReqDto.getPassword());
        user.setEmail(editUserReqDto.getEmail());
        user.setUpdateDt(LocalDateTime.now());
        return new ApiRespDto<>("success", "회원정보가 수정되었습니다.", userRepository.save(user));
    }

    public ApiRespDto<?> removeUser(Integer userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isEmpty()) {
            return new ApiRespDto<>("failed", "가입된 회원정보가 없습니다.", null);
        }
        userRepository.deleteById(userId);
        return new ApiRespDto<>("success", "회원정보가 삭제되었습니다.", null);
    }
}














