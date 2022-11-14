package com.healthcode.healthcodeserver.serviceTest;

import com.healthcode.healthcodeserver.entity.VaccineInoculationInfo;
import com.healthcode.healthcodeserver.service.VaccineInoculationInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class VaccineInoculationInfoServiceTest {
    @Autowired
    VaccineInoculationInfoService vaccineInoculationInfoService;

    /**
     * 测试函数 getInfoListByPersonIdTest
     * 用来测试函数    vaccineInoculationInfoService.getInfoListByPersonId(String personId);
     * 取值包括
     *         String personId_0 = "123er";
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
    public void getInfoListByPersonIdTest(){
        String personId_0 = "123er";
        String personId_1 = "43wserg43";
        String personId_2 = "";
        String personId_3 = "f239q80hwfuw2";
        String personId_4 = ".hj;km,tum";
        String personId_5 = "是的轮廓分明是";
        String personId_6 = "gwipe方式';,koisaf";
        String personId_7 = "oaiudfjaun2525141421241241fsdrg3w4gef";
        String personId_8 = "7";

        List<VaccineInoculationInfo> vaccineInoculationInfoList_0 =
                vaccineInoculationInfoService.getInfoListByPersonId(personId_0);
        System.out.println(vaccineInoculationInfoList_0 );
        List<VaccineInoculationInfo> vaccineInoculationInfoList_1 =
                vaccineInoculationInfoService.getInfoListByPersonId(personId_1);
        System.out.println(vaccineInoculationInfoList_1 );
        List<VaccineInoculationInfo> vaccineInoculationInfoList_2 =
                vaccineInoculationInfoService.getInfoListByPersonId(personId_2);
        System.out.println(vaccineInoculationInfoList_2 );
        List<VaccineInoculationInfo> vaccineInoculationInfoList_3 =
                vaccineInoculationInfoService.getInfoListByPersonId(personId_3);
        System.out.println(vaccineInoculationInfoList_3 );
        List<VaccineInoculationInfo> vaccineInoculationInfoList_4 =
                vaccineInoculationInfoService.getInfoListByPersonId(personId_4);
        System.out.println(vaccineInoculationInfoList_4 );
        List<VaccineInoculationInfo> vaccineInoculationInfoList_5 =
                vaccineInoculationInfoService.getInfoListByPersonId(personId_5);
        System.out.println(vaccineInoculationInfoList_5 );
        List<VaccineInoculationInfo> vaccineInoculationInfoList_6 =
                vaccineInoculationInfoService.getInfoListByPersonId(personId_6);
        System.out.println(vaccineInoculationInfoList_6 );
        List<VaccineInoculationInfo> vaccineInoculationInfoList_7 =
                vaccineInoculationInfoService.getInfoListByPersonId(personId_7);
        System.out.println(vaccineInoculationInfoList_7 );
        List<VaccineInoculationInfo> vaccineInoculationInfoList_8 =
                vaccineInoculationInfoService.getInfoListByPersonId(personId_8);
        System.out.println(vaccineInoculationInfoList_8 );

    }

}
