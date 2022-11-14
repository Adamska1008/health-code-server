package com.healthcode.healthcodeserver.serviceTest;

import com.healthcode.healthcodeserver.service.TesterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TesterServiceTest {
  @Autowired
  TesterService testerService;


  /**
   * 测试函数 test
   * 用来测试函数    testerService.isTester(String openId);
   * 取值包括
   *     String openId_0 = "1";
   *     String openId_1 = "12412414";
   *     String openId_2 = "sdjkfnskad";
   *     String openId_3 = "';.,.;[],";
   *     String openId_4 = "312eqadcq3./kol.";
   *     String openId_5 = "123q89weijders';.ldmbd,'12leq21";
   *     String openId_6 = "偶啊到房贫穷我今儿";
   *     String openId_7 = "fgpsgf2332r';.aid";
   *     String openId_8 = "asfkm克拉夫都34312;.";
   */
  @Test
  public void test(){
    String openId_0 = "1";
    String openId_1 = "12412414";
    String openId_2 = "sdjkfnskad";
    String openId_3 = "';.,.;[],";
    String openId_4 = "312eqadcq3./kol.";
    String openId_5 = "123q89weijders';.ldmbd,'12leq21";
    String openId_6 = "偶啊到房贫穷我今儿";
    String openId_7 = "fgpsgf2332r';.aid";
    String openId_8 = "asfkm克拉夫都34312;.";

    System.out.println(testerService.isTester(null));
    System.out.println(testerService.isTester(openId_0));
    System.out.println(testerService.isTester(openId_1));
    System.out.println(testerService.isTester(openId_2));
    System.out.println(testerService.isTester(openId_3));
    System.out.println(testerService.isTester(openId_4));
    System.out.println(testerService.isTester(openId_5));
    System.out.println(testerService.isTester(openId_6));
    System.out.println(testerService.isTester(openId_7));
    System.out.println(testerService.isTester(openId_8));
  }
  @Test
  public void isTester(){
    System.out.println(testerService.isTester("ojKoj52igjq_xw7MpIKZ4LUZJnH8"));
  }
}
