package com.lx.springbootstorm.controller;


import com.lx.springbootstorm.entity.User;
import com.lx.springbootstorm.service.UserService;
import com.lx.springbootstorm.utils.JSONModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LX
 * @since 2019-03-12
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/test")
    public JSONModel test(){
        User user = userService.selectByPrimaryKey(1);
        System.out.println(user.toString());
        return JSONModel.buildSuccess("ok",user);
    }

}
