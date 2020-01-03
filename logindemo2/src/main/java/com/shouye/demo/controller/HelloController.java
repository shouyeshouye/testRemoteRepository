package com.shouye.demo.controller;

import com.shouye.demo.util.JwtUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public Map login(HttpServletRequest request){
        String token = request.getParameter("token");
        return  JwtUtil.validateToken(token);
    }
}