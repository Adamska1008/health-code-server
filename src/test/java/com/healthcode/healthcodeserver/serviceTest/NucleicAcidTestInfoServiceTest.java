package com.healthcode.healthcodeserver.serviceTest;

import com.healthcode.healthcodeserver.service.NucleicAcidTestInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NucleicAcidTestInfoServiceTest {
  @Autowired
  NucleicAcidTestInfoService nucleicAcidTestInfoService;

  @Test
  public void getLatestTest() {
    System.out.println(nucleicAcidTestInfoService.getLatestTestInfoByPersonId("457342192208107100"));
  }

  @Test
  public void getTest() {
    System.out.println(nucleicAcidTestInfoService.getNucleicAcidTestInfoListByPersonId("457342192208107100"));
  }
}
