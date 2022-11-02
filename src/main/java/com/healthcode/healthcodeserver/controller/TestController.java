package com.healthcode.healthcodeserver.controller;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class TestController {
  @GetMapping("/test")
  public String hello(@RequestParam(value="name", defaultValue="World")String name) {
    return String.format("Hello, %s!", name);
  }
  @GetMapping("/echo")
  public String echo(@RequestParam(value="content") String content) {
    return content;
  }
}
