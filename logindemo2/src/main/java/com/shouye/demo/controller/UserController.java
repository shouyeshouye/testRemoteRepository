package com.shouye.demo.controller;

import com.shouye.demo.entity.User;
import com.shouye.demo.repository.UserRepository;
import com.shouye.demo.response.UserResponse;
import com.shouye.demo.util.Constants;
import com.shouye.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;


    //注册或登录
    @RequestMapping("/login")
    @Transactional
    public UserResponse login(User user){
        String username=user.getUsername();
        String password=user.getPassword();

        UserResponse userResponse=new UserResponse();
        User tUser=userRepository.SelectUser(username);  //验证username是否存在
        user.setLastLoginTime(new Date());
        if(tUser!=null){
            if (!tUser.getPassword().equals(password)){
                userResponse.setErrorNum(Constants.ERR_NUM_PWD_ERR);
                userResponse.setErrorMsg(Constants.ERR_MSG_PWD_ERR);
            }
           userRepository.updateLastLoginTimeByUserName(user.getLastLoginTime(),username);
        }else {
            try {
                tUser=userRepository.InsertUser(user);
            }catch (Exception e){
                userResponse.setErrorNum(Constants.ERR_NUM_SERVER_ERR);
                userResponse.setErrorMsg(Constants.ERR_MSG_SERVER_ERR);
                return userResponse;
            }
        }
        userResponse.setErrorNum(Constants.ERR_NUM_OK);
        userResponse.setErrorMsg(Constants.ERR_MSG_OK);
        userResponse.setUserName(username);
        userResponse.setUserId(tUser.getId());
        userResponse.setToken(JwtUtil.generateToken(username,user.getLastLoginTime()));
        return userResponse;
    }
}
