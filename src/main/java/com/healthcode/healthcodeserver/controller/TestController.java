package com.healthcode.healthcodeserver.controller;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class TestController {

  /**
   * 测试代码，传回 hello 语句
   * @param name 测试参数
   * @return hello字符串
   */
  @GetMapping("/test")
  public String hello(@RequestParam(value="name", defaultValue="World")String name) {
    return String.format("Hello, %s!", name);
  }

  /**
   * 测试代码，返回与请求内容完全相同的内容
   * @param content 请求内容
   * @return 请求内容
   */
  @GetMapping("/echo")
  public String echo(@RequestParam(value="content") String content) {
    return content;
  }
}
