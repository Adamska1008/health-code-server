package com.healthcode.healthcodeserver.controller;

import com.healthcode.healthcodeserver.entity.Person;
import com.healthcode.healthcodeserver.service.UserService;
import com.healthcode.healthcodeserver.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/healthcode/user")
public class UserController {
  @Autowired
  UserService userService;


  @PostMapping("/login")
  public String getUserInfo(@RequestBody Person person) {

    return JWTUtil.createToken(person);
  }
}
