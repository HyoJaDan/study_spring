package com.OAuthSession3.OAuthSession.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
class MyController {

    @GetMapping("/my")
    public String myPage() {
        return "my";
    }
}