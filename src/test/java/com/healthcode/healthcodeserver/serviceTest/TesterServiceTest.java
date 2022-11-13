package com.healthcode.healthcodeserver.serviceTest;

import com.healthcode.healthcodeserver.service.TesterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TesterServiceTest {
  @Autowired
  TesterService testerService;
  @Test
  public void test(){
    System.out.println(testerService.isTester("1"));
  }
  @Test
  public void isTester(){
    System.out.println(testerService.isTester("ojKoj52igjq_xw7MpIKZ4LUZJnH8"));
  }
}
