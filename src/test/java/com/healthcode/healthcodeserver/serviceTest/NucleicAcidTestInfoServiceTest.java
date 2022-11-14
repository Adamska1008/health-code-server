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

  /**
   * 测试函数 getNucleicAcidTestInfoListByPersonIdTest
   * 用来测试函数    nucleicAcidTestInfoService.getNucleicAcidTestInfoListByPersonId(String personId);
   * 取值包括
   *     String personId_0 = "123er";
   *     String personId_1 = "43wserg43";
   *     String personId_2 = "";
   *     String personId_3 = "f239q80hwfuw2";
   *     String personId_4 = ".hj;km,tum";
   *     String personId_5 = "是的轮廓分明是";
   *     String personId_6 = "gwipe方式';,koisaf";
   *     String personId_7 = "oaiudfjaun2525141421241241fsdrg3w4gef";
   *     String personId_8 = "7";
   */
  @Test
  public void getNucleicAcidTestInfoListByPersonIdTest(String personId) {
    String personId_0 = "123er";
    String personId_1 = "43wserg43";
    String personId_2 = "";
    String personId_3 = "f239q80hwfuw2";
    String personId_4 = ".hj;km,tum";
    String personId_5 = "是的轮廓分明是";
    String personId_6 = "gwipe方式';,koisaf";
    String personId_7 = "oaiudfjaun2525141421241241fsdrg3w4gef";
    String personId_8 = "7";

    List<NucleicAcidTestInfo> nucleicAcidTestInfoList_0 = nucleicAcidTestInfoService.getNucleicAcidTestInfoListByPersonId(null);
    System.out.println(nucleicAcidTestInfoList_0);
    List<NucleicAcidTestInfo> nucleicAcidTestInfoList_1 = nucleicAcidTestInfoService.getNucleicAcidTestInfoListByPersonId(personId_0);
    System.out.println(nucleicAcidTestInfoList_1);
    List<NucleicAcidTestInfo> nucleicAcidTestInfoList_2 = nucleicAcidTestInfoService.getNucleicAcidTestInfoListByPersonId(personId_1);
    System.out.println(nucleicAcidTestInfoList_2);
    List<NucleicAcidTestInfo> nucleicAcidTestInfoList_3 = nucleicAcidTestInfoService.getNucleicAcidTestInfoListByPersonId(personId_2);
    System.out.println(nucleicAcidTestInfoList_3);
    List<NucleicAcidTestInfo> nucleicAcidTestInfoList_4 = nucleicAcidTestInfoService.getNucleicAcidTestInfoListByPersonId(personId_3);
    System.out.println(nucleicAcidTestInfoList_4);
    List<NucleicAcidTestInfo> nucleicAcidTestInfoList_5= nucleicAcidTestInfoService.getNucleicAcidTestInfoListByPersonId(personId_4);
    System.out.println(nucleicAcidTestInfoList_5);
    List<NucleicAcidTestInfo> nucleicAcidTestInfoList_6 = nucleicAcidTestInfoService.getNucleicAcidTestInfoListByPersonId(personId_5);
    System.out.println(nucleicAcidTestInfoList_6);
    List<NucleicAcidTestInfo> nucleicAcidTestInfoList_7 = nucleicAcidTestInfoService.getNucleicAcidTestInfoListByPersonId(personId_6);
    System.out.println(nucleicAcidTestInfoList_7);
    List<NucleicAcidTestInfo> nucleicAcidTestInfoList_8 = nucleicAcidTestInfoService.getNucleicAcidTestInfoListByPersonId(personId_7);
    System.out.println(nucleicAcidTestInfoList_8);
    List<NucleicAcidTestInfo> nucleicAcidTestInfoList_9 = nucleicAcidTestInfoService.getNucleicAcidTestInfoListByPersonId(personId_8);
    System.out.println(nucleicAcidTestInfoList_9);
  }
}
