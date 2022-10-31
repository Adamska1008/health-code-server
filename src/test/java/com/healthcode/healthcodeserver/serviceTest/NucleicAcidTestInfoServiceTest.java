package com.healthcode.healthcodeserver.serviceTest;

import com.healthcode.healthcodeserver.entity.NucleicAcidTestInfo;
import com.healthcode.healthcodeserver.service.NucleicAcidTestInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class NucleicAcidTestInfoServiceTest {
  @Autowired
  NucleicAcidTestInfoService nucleicAcidTestInfoService;

  @Test
  public void UnitTest() {
    getNucleicAcidTestInfoListByPersonIdTest("35341719961123514X");
  }

  @Test
  public void getNucleicAcidTestInfoListByPersonIdTest(String personId) {
    List<NucleicAcidTestInfo> nucleicAcidTestInfoList = nucleicAcidTestInfoService.getNucleicAcidTestInfoListByPersonId(personId);
    System.out.println(nucleicAcidTestInfoList);
  }
}
