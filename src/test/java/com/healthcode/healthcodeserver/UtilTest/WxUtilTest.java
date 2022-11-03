package com.healthcode.healthcodeserver.UtilTest;

import com.healthcode.healthcodeserver.util.WxUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WxUtilTest {
  @Autowired
  WxUtil wxUtil;

  @Test
  public void code2SessionTest() {
    String code = "053f6l1w3IEltZ2AoT0w3CyrqA0f6l1R";
    System.out.println(wxUtil.code2Session(code, 0));
  }
}
