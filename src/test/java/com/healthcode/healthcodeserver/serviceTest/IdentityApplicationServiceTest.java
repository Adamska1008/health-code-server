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

        List<IdentityApplication> identityApplicationList_0 =
                identityApplicationService.getTesterApplicationList(limit_0);
        System.out.println(identityApplicationList_0);
        List<IdentityApplication> identityApplicationList_1 =
                identityApplicationService.getTesterApplicationList(limit_1);
        System.out.println(identityApplicationList_1);
        List<IdentityApplication> identityApplicationList_2 =
                identityApplicationService.getTesterApplicationList(limit_2);
        System.out.println(identityApplicationList_2);
        List<IdentityApplication> identityApplicationList_3 =
                identityApplicationService.getTesterApplicationList(limit_3);
        System.out.println(identityApplicationList_3);
        List<IdentityApplication> identityApplicationList_4 =
                identityApplicationService.getTesterApplicationList(limit_4);
        System.out.println(identityApplicationList_4);
        List<IdentityApplication> identityApplicationList_5 =
                identityApplicationService.getTesterApplicationList(limit_5);
        System.out.println(identityApplicationList_5);
        List<IdentityApplication> identityApplicationList_6 =
                identityApplicationService.getTesterApplicationList(limit_6);
        System.out.println(identityApplicationList_6);
        List<IdentityApplication> identityApplicationList_7 =
                identityApplicationService.getTesterApplicationList(limit_7);
        System.out.println(identityApplicationList_7);
        List<IdentityApplication> identityApplicationList_8 =
                identityApplicationService.getTesterApplicationList(limit_8);
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
}
