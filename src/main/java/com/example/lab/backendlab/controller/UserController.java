package com.example.lab.backendlab.controller;

import com.example.lab.backendlab.entity.User;
import com.example.lab.backendlab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 회원가입
    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        user.setUuid(UUID.randomUUID().toString());

        // 비밀번호 해싱
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        userRepository.save(user);
        return "가입 완료! UUID: " + user.getUuid();
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        Optional<User> optionalUser = userRepository.findById(loginRequest.getId());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            boolean matches = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());

            if (matches) {
                return "로그인 성공! 이름: " + user.getName();
            }
        }

        return "로그인 실패: ID 또는 비밀번호가 틀렸습니다.";
    }
}
