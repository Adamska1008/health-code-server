package com.healthcode.healthcodeserver.serviceTest;

import com.healthcode.healthcodeserver.entity.IdentityApplication;
import com.healthcode.healthcodeserver.service.IdentityApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class IdentityApplicationServiceTest {

    @Autowired
    IdentityApplicationService identityApplicationService;

    /**
     * 测试函数 getTesterApplicationListTest
     * 用来测试函数    identityApplicationService.getTesterApplicationList(int limit);
     * 取值包括
     *         int limit_0 = 30;
     *         int limit_1 = 99;
     *         int limit_2 = 10000000;
     *         int limit_3 = 3;
     *         int limit_4 = 0;
     *         int limit_5 = 241526345;
     *         int limit_6 = 1241412414;
     *         int limit_7 = 10000;
     *         int limit_8 = 9999999;
     */
    @Test
    public void getTesterApplicationListTest(){
        int limit_0 = 30;
        int limit_1 = 99;
        int limit_2 = 10000000;
        int limit_3 = 3;
        int limit_4 = 0;
        int limit_5 = 241526345;
        int limit_6 = 1241412414;
        int limit_7 = 10000;
        int limit_8 = 9999999;

        List<IdentityApplication> identityApplicationList_0 = identityApplicationService.getTesterApplicationList(limit_0);
        System.out.println(identityApplicationList_0);
        List<IdentityApplication> identityApplicationList_1 = identityApplicationService.getTesterApplicationList(limit_1);
        System.out.println(identityApplicationList_1);
        List<IdentityApplication> identityApplicationList_2 = identityApplicationService.getTesterApplicationList(limit_2);
        System.out.println(identityApplicationList_2);
        List<IdentityApplication> identityApplicationList_3 = identityApplicationService.getTesterApplicationList(limit_3);
        System.out.println(identityApplicationList_3);
        List<IdentityApplication> identityApplicationList_4 = identityApplicationService.getTesterApplicationList(limit_4);
        System.out.println(identityApplicationList_4);
        List<IdentityApplication> identityApplicationList_5 = identityApplicationService.getTesterApplicationList(limit_5);
        System.out.println(identityApplicationList_5);
        List<IdentityApplication> identityApplicationList_6 = identityApplicationService.getTesterApplicationList(limit_6);
        System.out.println(identityApplicationList_6);
        List<IdentityApplication> identityApplicationList_7 = identityApplicationService.getTesterApplicationList(limit_7);
        System.out.println(identityApplicationList_7);
        List<IdentityApplication> identityApplicationList_8 = identityApplicationService.getTesterApplicationList(limit_8);
        System.out.println(identityApplicationList_8);

    }

    /**
     * 测试函数 hasApplicationRecordTest
     * 用来测试函数    identityApplicationService.hasApplicationRecord(String personId);
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
    public void hasApplicationRecordTest(){
        String personId_0 = "123er";
        String personId_1 = "43wserg43";
        String personId_2 = "";
        String personId_3 = "f239q80hwfuw2";
        String personId_4 = ".hj;km,tum";
        String personId_5 = "是的轮廓分明是";
        String personId_6 = "gwipe方式';,koisaf";
        String personId_7 = "oaiudfjaun2525141421241241fsdrg3w4gef";
        String personId_8 = "7";

        System.out.println(identityApplicationService.hasApplicationRecord(null));
        System.out.println(identityApplicationService.hasApplicationRecord(personId_0));
        System.out.println(identityApplicationService.hasApplicationRecord(personId_1));
        System.out.println(identityApplicationService.hasApplicationRecord(personId_2));
        System.out.println(identityApplicationService.hasApplicationRecord(personId_3));
        System.out.println(identityApplicationService.hasApplicationRecord(personId_4));
        System.out.println(identityApplicationService.hasApplicationRecord(personId_5));
        System.out.println(identityApplicationService.hasApplicationRecord(personId_6));
        System.out.println(identityApplicationService.hasApplicationRecord(personId_7));
        System.out.println(identityApplicationService.hasApplicationRecord(personId_8));
    }

    /**
     * 测试函数 hasApplicationRecordTest
     * 用来测试函数    identityApplicationService.updateApplicantProcessed(String id , int isSucceed );
     * 取值包括
     *         String id_0 = "1214421";
     *         String isSucceed_0 = "132456";
     *
     *         String id_1 = "10278eue90823qe";
     *         String isSucceed_1 = "12";
     *
     *         String id_2 = "1";
     *         String isSucceed_2 = "121231512";
     *
     *         String id_3 = "1f23q8uqw";
     *         String isSucceed_3 = "12.lkjjgh";
     *
     *         String id_4 = "2308r7h9j8fw3/.;";
     *         String isSucceed_4 = "12d23qe98jqawda";
     *
     *         String id_5 = "123456987t346745";
     *         String isSucceed_5 = "dfgjksndjau";
     */
    @Test
    public void updateApplicantProcessedTest(){
        String id_0 = "1214421";
        String isSucceed_0 = "132456";

        String id_1 = "10278eue90823qe";
        String isSucceed_1 = "12";

        String id_2 = "1";
        String isSucceed_2 = "121231512";

        String id_3 = "1f23q8uqw";
        String isSucceed_3 = "12.lkjjgh";

        String id_4 = "2308r7h9j8fw3/.;";
        String isSucceed_4 = "12d23qe98jqawda";

        String id_5 = "123456987t346745";
        String isSucceed_5 = "dfgjksndjau";

        identityApplicationService.updateApplicantProcessed(null, Integer.valueOf(isSucceed_0));
        identityApplicationService.updateApplicantProcessed(id_0, Integer.valueOf(isSucceed_0));
        identityApplicationService.updateApplicantProcessed(id_1, Integer.valueOf(isSucceed_1));
        identityApplicationService.updateApplicantProcessed(id_2, Integer.valueOf(isSucceed_2));
        identityApplicationService.updateApplicantProcessed(id_3, Integer.valueOf(isSucceed_3));
        identityApplicationService.updateApplicantProcessed(id_4, Integer.valueOf(isSucceed_4));
        identityApplicationService.updateApplicantProcessed(id_5, Integer.valueOf(isSucceed_5));


    }
}
