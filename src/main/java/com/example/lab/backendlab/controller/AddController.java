package com.example.lab.backendlab.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/add")
public class AddController {

    // GET 요청: /add?x=3&y=4
    @GetMapping
    public String addGet(@RequestParam int x, @RequestParam int y) {
        int result = x + y;
        return "x + y = " + result;
    }

    // POST 요청: /add (JSON: { "x": 3, "y": 4 })
    @PostMapping
    public String addPost(@RequestBody AddRequest request) {
        int result = request.getX() + 2 * request.getY();
        return "x + 2y = " + result;
    }

    // JSON 입력 받기 위한 내부 클래스
    public static class AddRequest {
        private int x;
        private int y;

        public int getX() { return x; }
        public void setX(int x) { this.x = x; }

        public int getY() { return y; }
        public void setY(int y) { this.y = y; }
    }
}
