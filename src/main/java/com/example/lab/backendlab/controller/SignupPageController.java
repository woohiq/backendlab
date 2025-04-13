package com.example.lab.backendlab.controller;

import com.example.lab.backendlab.entity.User;
import com.example.lab.backendlab.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
public class SignupPageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String signupForm() {
        return "signup"; // templates/signup.html
    }

    @PostMapping("/signup")
    public String signupSubmit(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String name = request.getParameter("name");

        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            model.addAttribute("error", "이미 존재하는 아이디입니다.");
            return "signup";
        }

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setUuid(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
        return "redirect:/login";
    }
}