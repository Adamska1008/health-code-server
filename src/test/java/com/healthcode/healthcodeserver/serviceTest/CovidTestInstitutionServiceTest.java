package com.healthcode.healthcodeserver.serviceTest;

import com.healthcode.healthcodeserver.service.CovidTestInstitutionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CovidTestInstitutionServiceTest {

    @Autowired
    CovidTestInstitutionService covidTestInstitutionService;

    /**
     * 测试函数 getNameByIdTest
     * 用来测试函数    covidTestInstitutionService.getNameById(String personId);
     * 取值包括
     *     String personId_0 = "123er";
     *         String personId_1 = "43wserg43";
     *         String personId_2 = "";
     *         String personId_3 = "f239q80hwfuw2";
     *         String personId_4 = ".hj;km,tum";
     *         String personId_5 = "是的轮廓分明是";
     *         String personId_6 = "gwipe方式';,koisaf";
     *         String personId_7 = "oaiudfjaun2525141421241241fsdrg3w4gef";
     *         String personId_8 = "7";
     */
    @Test
    public void getNameByIdTest(){
        String personId_0 = "123er";
        String personId_1 = "43wserg43";
        String personId_2 = "";
        String personId_3 = "f239q80hwfuw2";
        String personId_4 = ".hj;km,tum";
        String personId_5 = "是的轮廓分明是";
        String personId_6 = "gwipe方式';,koisaf";
        String personId_7 = "oaiudfjaun2525141421241241fsdrg3w4gef";
        String personId_8 = "7";

        System.out.println(covidTestInstitutionService.getNameById(personId_0));
        System.out.println(covidTestInstitutionService.getNameById(personId_1));
        System.out.println(covidTestInstitutionService.getNameById(personId_2));
        System.out.println(covidTestInstitutionService.getNameById(personId_3));
        System.out.println(covidTestInstitutionService.getNameById(personId_4));
        System.out.println(covidTestInstitutionService.getNameById(personId_5));
        System.out.println(covidTestInstitutionService.getNameById(personId_6));
        System.out.println(covidTestInstitutionService.getNameById(personId_7));
        System.out.println(covidTestInstitutionService.getNameById(personId_8));
        System.out.println(covidTestInstitutionService.getNameById(null));
    }

}
