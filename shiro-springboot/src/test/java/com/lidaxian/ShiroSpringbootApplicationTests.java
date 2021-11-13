package com.lidaxian;

import com.lidaxian.service.BlogServiceImpl;
import com.lidaxian.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroSpringbootApplicationTests {

    @Autowired
    BlogServiceImpl blogService;
    @Test
    void contextLoads() {
        System.out.println(blogService.queryBlogById(1));
    }

//    @Autowired
//    UserServiceImpl userService;
//    @Test
//    void contextLoads() {
//        System.out.println(userService.queryUserByName("lidaxian"));
//    }

}
