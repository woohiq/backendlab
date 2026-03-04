package com.example.lab.backendlab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HandudleController {

    @GetMapping("/handudle")
    public String handudlePage() {
        return "handudle";
    }
}