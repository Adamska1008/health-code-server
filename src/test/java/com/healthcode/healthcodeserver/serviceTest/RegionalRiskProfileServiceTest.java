package com.healthcode.healthcodeserver.serviceTest;

import com.healthcode.healthcodeserver.service.RegionalRiskProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.regex.Pattern;

@SpringBootTest
public class RegionalRiskProfileServiceTest {
  @Autowired
  RegionalRiskProfileService regionalRiskProfileService;

  @Test
  public void getSubAreasTest() {
    String province = "湖南省";
    String city = "长沙市";
    List<String> subAreas = regionalRiskProfileService.getSubArea(province, city);
    for (String subArea : subAreas) {
      assert Pattern.matches(".*区", subArea);
    }
    System.out.println(subAreas);
    subAreas = regionalRiskProfileService.getSubArea(province, null);
    for (String subArea : subAreas) {
      assert Pattern.matches(".*市", subArea);
    }
    System.out.println(subAreas);
    subAreas = regionalRiskProfileService.getSubArea(null, null);
    for (String subArea : subAreas) {
      assert Pattern.matches(".*省", subArea);
    }
    System.out.println(subAreas);
  }
}
