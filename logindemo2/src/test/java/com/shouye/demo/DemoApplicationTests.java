package com.shouye.demo;

import com.shouye.demo.entity.User;
import com.shouye.demo.repository.UserRepository;
import com.shouye.demo.util.MD5Util;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
@SpringBootTest
class DemoApplicationTests {
    @Autowired
    UserRepository userRepository;

    @Test
    void contextLoads() {
//        User user = new User();                  //UserInsert
//        user.setUsername("user");
//        user.setPassword("123456");
//        user.setEmail("985134278@qq.com");
//        user.setLastLoginTime(new Date());
//        userRepository.InsertUser(user);




//        User user=new User();              //UserUpdate
//        user.setId(3);
//        user.setLastLoginTime(new Date());
//        userRepository.updateUser(user);



//        User user=userRepository.SelectUser("Xie Zhinian");   //SelectUser
//        log.info("email:"+user.getEmail());




//        int Result=userRepository.deleteUser("Xie Zhinian");   //DeleteUser

//        Date dateTime=new Date();
//        int Result=userRepository.updateLastLoginTimeByUserName(dateTime,"user");
//        log.info("dateTime:"+dateTime);




    }

}
