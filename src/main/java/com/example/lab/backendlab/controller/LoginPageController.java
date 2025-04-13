package com.example.lab.backendlab.controller;

import com.example.lab.backendlab.entity.User;
import com.example.lab.backendlab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginPageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/index"; // 루트로 접속하면 index로 리다이렉트
    }

    @GetMapping("/index")
    public String home(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "index"; // resources/templates/index.html
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login"; // resources/templates/login.html
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String id,
            @RequestParam String password,
            Model model,
            HttpSession session
    ) {
        System.out.println("로그인 시도: ID = " + id + ", PW = " + password);

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            System.out.println("DB에 저장된 해시: " + user.getPassword());

            if (passwordEncoder.matches(password, user.getPassword())) {
                System.out.println("로그인 성공!");
                session.setAttribute("username", user.getName());
                return "redirect:/";
            } else {
                System.out.println("비밀번호 불일치!");
            }
        } else {
            System.out.println("ID에 해당하는 사용자 없음");
        }

        model.addAttribute("error", "해당하는 정보가 없습니다.");
        return "login";
    }

}
