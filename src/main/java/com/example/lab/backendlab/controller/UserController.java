package com.example.lab.backendlab.controller;

import com.example.lab.backendlab.entity.User;
import com.example.lab.backendlab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // 회원가입
    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        user.setUuid(UUID.randomUUID().toString());
        userRepository.save(user);
        return "가입 완료! UUID: " + user.getUuid();
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        Optional<User> user = userRepository.findByIdAndPassword(
                loginRequest.getId(), loginRequest.getPassword());

        return user.map(u -> "로그인 성공! 이름: " + u.getName())
                   .orElse("로그인 실패: ID 또는 비밀번호가 틀렸습니다.");
    }
}
