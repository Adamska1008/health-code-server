package com.healthcode.healthcodeserver.controllerTest;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.healthcode.healthcodeserver.common.Result;
import com.healthcode.healthcodeserver.controller.TesterController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TesterControllerTest {
    @Autowired
    TesterController testerController;

    /**
     * 测试函数 code2SessionTest
     * 用来测试函数    testerController.code2Session(String code,String appid);
     * 取值包括
     *  参数null 值测试
     * 812763gedqw 7
     * 0 r72389f243r
     * d19g36h72867r1 1728dh30u8er12
     * ylhj mghjuif
     * .'ylhj;',mghjuif , tpl',gkhogf'ghjfl
     */
    @Test
    public void code2SessionTest(){
        String code_0 = "812763gedqw";
        String appid_0 = "7";
        String code_1 = "0";
        String appid_1 = "r72389f243r";
        String code_2 = "d19g36h72867r1";
        String appid_2 = "1728dh30u8er12";
        String code_3 = ".'ylhj;',mghjuif";
        String appid_3 = ",tpl',gkhogf'ghjfl";
        Result result_0 = testerController.code2Session(code_0,null);
        System.out.println(JSON.toJSON(result_0));
        Result result_1 = testerController.code2Session(null,appid_0);
        System.out.println(JSON.toJSON(result_1));
        Result result_2 = testerController.code2Session(null,null);
        System.out.println(JSON.toJSON(result_2));
        Result result_3 = testerController.code2Session(code_0,appid_0);
        System.out.println(JSON.toJSON(result_3));
        Result result_4 = testerController.code2Session(code_1,appid_1);
        System.out.println(JSON.toJSON(result_4));
        Result result_5 = testerController.code2Session(code_2,appid_2);
        System.out.println(JSON.toJSON(result_5));
        Result result_6 = testerController.code2Session(code_3,appid_3);
        System.out.println(JSON.toJSON(result_6));
    }

    /**
     * 测试函数 getApplicationInfoTest
     * 用来测试函数    testerController.getApplicationInfo(String openid,String sessionKey,String name,String idNumber,String phoneNumber,String appid);
     * 取值包括
     *
     *          参数 null值测试
     *
     *         String openid_0 = "ry57tu";
     *         String sessionKey_0 = "7";
     *         String name_0 = "zhangsan";
     *         String idNumber_0 = "3";
     *         String phoneNumber_0 = "13245677654";
     *         String appid_0 = "d2837h";
     *
     *         String openid_1 = "8hf9023ui93f92u3";
     *         String sessionKey_1 = "0f29k03o";
     *         String name_1 = "吴清源";
     *         String idNumber_1 = "790";
     *         String phoneNumber_1 = "1241251313";
     *         String appid_1 = "，；yglhgpojfg";
     *
     *         String openid_2 = ",ypokjtu/i76;8";
     *         String sessionKey_2 = ",;[yl905";
     *         String name_2 = "g384982r3j";
     *         String idNumber_2 = "0134912";
     *         String phoneNumber_2 = "13245677654";
     *         String appid_2 = "mr9t8jher0hg3o4341f";
     */
    @Test
    public void getApplicationInfoTest(){
        String openid_0 = "ry57tu";
        String sessionKey_0 = "7";
        String name_0 = "zhangsan";
        String idNumber_0 = "3";
        String phoneNumber_0 = "13245677654";
        String appid_0 = "d2837h";

        String openid_1 = "8hf9023ui93f92u3";
        String sessionKey_1 = "0f29k03o";
        String name_1 = "吴清源";
        String idNumber_1 = "790";
        String phoneNumber_1 = "1241251313";
        String appid_1 = "，；yglhgpojfg";

        String openid_2 = ",ypokjtu/i76;8";
        String sessionKey_2 = ",;[yl905";
        String name_2 = "g384982r3j";
        String idNumber_2 = "0134912";
        String phoneNumber_2 = "13245677654";
        String appid_2 = "mr9t8jher0hg3o4341f";

        Result result_0 = testerController.getApplicationInfo(null,sessionKey_0,name_0,idNumber_0,phoneNumber_0,appid_0);
        System.out.println(JSON.toJSON(result_0));
        Result result_1 = testerController.getApplicationInfo(null,null,name_0,idNumber_0,phoneNumber_0,appid_0);
        System.out.println(JSON.toJSON(result_1));
        Result result_2 = testerController.getApplicationInfo(null,sessionKey_0,null,idNumber_0,phoneNumber_0,appid_0);
        System.out.println(JSON.toJSON(result_2));
        Result result_3 = testerController.getApplicationInfo(null,sessionKey_0,name_0,null,phoneNumber_0,appid_0);
        System.out.println(JSON.toJSON(result_3));
        Result result_4 = testerController.getApplicationInfo(null,sessionKey_0,name_0,idNumber_0,null,appid_0);
        System.out.println(JSON.toJSON(result_4));
        Result result_5 = testerController.getApplicationInfo(null,sessionKey_0,name_0,idNumber_0,phoneNumber_0,null);
        System.out.println(JSON.toJSON(result_5));
        Result result_6 = testerController.getApplicationInfo(openid_0,null,null,idNumber_0,phoneNumber_0,appid_0);
        System.out.println(JSON.toJSON(result_6));
        Result result_7 = testerController.getApplicationInfo(openid_0,null,name_0,null,phoneNumber_0,appid_0);
        System.out.println(JSON.toJSON(result_7));
        Result result_8 = testerController.getApplicationInfo(openid_0,null,name_0,idNumber_0,null,appid_0);
        System.out.println(JSON.toJSON(result_8));
        Result result_9 = testerController.getApplicationInfo(openid_0,null,name_0,idNumber_0,phoneNumber_0,null);
        System.out.println(JSON.toJSON(result_9));
        Result result_10 = testerController.getApplicationInfo(openid_0,null,name_0,idNumber_0,phoneNumber_0,appid_0);
        System.out.println(JSON.toJSON(result_10));
        Result result_11 = testerController.getApplicationInfo(openid_0,sessionKey_0,null,null,phoneNumber_0,appid_0);
        System.out.println(JSON.toJSON(result_11));
        Result result_12 = testerController.getApplicationInfo(openid_0,sessionKey_0,null,idNumber_0,null,appid_0);
        System.out.println(JSON.toJSON(result_12));
        Result result_13 = testerController.getApplicationInfo(openid_0,sessionKey_0,null,idNumber_0,phoneNumber_0,null);
        System.out.println(JSON.toJSON(result_13));
        Result result_14 = testerController.getApplicationInfo(openid_0,sessionKey_0,name_0,null,phoneNumber_0,appid_0);
        System.out.println(JSON.toJSON(result_14));
        Result result_15 = testerController.getApplicationInfo(openid_0,sessionKey_0,name_0,null,null,appid_0);
        System.out.println(JSON.toJSON(result_15));
        Result result_16 = testerController.getApplicationInfo(openid_0,sessionKey_0,name_0,null,phoneNumber_0,null);
        System.out.println(JSON.toJSON(result_16));
        Result result_17 = testerController.getApplicationInfo(openid_0,sessionKey_0,name_0,idNumber_0,null,null);
        System.out.println(JSON.toJSON(result_17));
        Result result_18 = testerController.getApplicationInfo(openid_0,sessionKey_0,null,idNumber_0,phoneNumber_0,appid_0);
        System.out.println(JSON.toJSON(result_18));
        Result result_19 = testerController.getApplicationInfo(openid_0,sessionKey_0,name_0,idNumber_0,null,appid_0);
        System.out.println(JSON.toJSON(result_19));
        Result result_20 = testerController.getApplicationInfo(openid_0,sessionKey_0,name_0,idNumber_0,phoneNumber_0,null);
        System.out.println(JSON.toJSON(result_20));
        Result result_21 = testerController.getApplicationInfo(null,null,null,idNumber_0,phoneNumber_0,appid_0);
        System.out.println(JSON.toJSON(result_21));
        Result result_22 = testerController.getApplicationInfo(null,null,name_0,null,phoneNumber_0,appid_0);
        System.out.println(JSON.toJSON(result_22));
        Result result_23 = testerController.getApplicationInfo(null,null,name_0,idNumber_0,null,appid_0);
        System.out.println(JSON.toJSON(result_23));
        Result result_24 = testerController.getApplicationInfo(null,null,name_0,idNumber_0,phoneNumber_0,null);
        System.out.println(JSON.toJSON(result_24));
        Result result_25 = testerController.getApplicationInfo(null,sessionKey_0,null,null,phoneNumber_0,appid_0);
        System.out.println(JSON.toJSON(result_25));
        Result result_26 = testerController.getApplicationInfo(null,sessionKey_0,null,idNumber_0,null,appid_0);
        System.out.println(JSON.toJSON(result_26));
        Result result_27 = testerController.getApplicationInfo(null,sessionKey_0,null,idNumber_0,phoneNumber_0,null);
        System.out.println(JSON.toJSON(result_27));
        Result result_28 = testerController.getApplicationInfo(null,sessionKey_0,name_0,null,null,appid_0);
        System.out.println(JSON.toJSON(result_28));
        Result result_29 = testerController.getApplicationInfo(null,sessionKey_0,name_0,null,phoneNumber_0,null);
        System.out.println(JSON.toJSON(result_29));
        Result result_30 = testerController.getApplicationInfo(openid_0,null,null,null,phoneNumber_0,appid_0);
        System.out.println(JSON.toJSON(result_30));
        Result result_31 = testerController.getApplicationInfo(openid_0,null,null,idNumber_0,null,appid_0);
        System.out.println(JSON.toJSON(result_31));
        Result result_32 = testerController.getApplicationInfo(openid_0,null,null,idNumber_0,phoneNumber_0,null);
        System.out.println(JSON.toJSON(result_32));
        Result result_33 = testerController.getApplicationInfo(openid_0,null,name_0,null,null,appid_0);
        System.out.println(JSON.toJSON(result_33));
        Result result_34 = testerController.getApplicationInfo(openid_0,null,name_0,null,phoneNumber_0,null);
        System.out.println(JSON.toJSON(result_34));
        Result result_35 = testerController.getApplicationInfo(openid_0,null,name_0,idNumber_0,null,null);
        System.out.println(JSON.toJSON(result_35));
        Result result_36 = testerController.getApplicationInfo(openid_0,sessionKey_0,null,null,null,appid_0);
        System.out.println(JSON.toJSON(result_36));
        Result result_37 = testerController.getApplicationInfo(openid_0,sessionKey_0,null,null,phoneNumber_0,null);
        System.out.println(JSON.toJSON(result_37));
        Result result_38 = testerController.getApplicationInfo(openid_0,sessionKey_0,null,idNumber_0,null,null);
        System.out.println(JSON.toJSON(result_38));
        Result result_39 = testerController.getApplicationInfo(openid_0,sessionKey_0,name_0,null,null,null);
        System.out.println(JSON.toJSON(result_39));
        Result result_40 = testerController.getApplicationInfo(null,sessionKey_0,name_0,idNumber_0,null,null);
        System.out.println(JSON.toJSON(result_40));
        Result result_41 = testerController.getApplicationInfo(null,null,null,null,phoneNumber_0,appid_0);
        System.out.println(JSON.toJSON(result_41));
        Result result_42 = testerController.getApplicationInfo(null,null,null,idNumber_0,null,appid_0);
        System.out.println(JSON.toJSON(result_42));
        Result result_43 = testerController.getApplicationInfo(null,null,null,idNumber_0,phoneNumber_0,null);
        System.out.println(JSON.toJSON(result_43));
        Result result_44 = testerController.getApplicationInfo(null,null,name_0,null,null,appid_0);
        System.out.println(JSON.toJSON(result_44));
        Result result_45 = testerController.getApplicationInfo(null,null,name_0,null,phoneNumber_0,null);
        System.out.println(JSON.toJSON(result_45));
        Result result_46 = testerController.getApplicationInfo(null,null,name_0,idNumber_0,null,null);
        System.out.println(JSON.toJSON(result_46));
        Result result_47 = testerController.getApplicationInfo(null,sessionKey_0,null,null,null,appid_0);
        System.out.println(JSON.toJSON(result_47));
        Result result_48 = testerController.getApplicationInfo(null,sessionKey_0,null,null,phoneNumber_0,null);
        System.out.println(JSON.toJSON(result_48));
        Result result_49 = testerController.getApplicationInfo(null,sessionKey_0,null,idNumber_0,null,null);
        System.out.println(JSON.toJSON(result_49));
        Result result_50 = testerController.getApplicationInfo(null,sessionKey_0,name_0,null,null,null);
        System.out.println(JSON.toJSON(result_50));
        Result result_51 = testerController.getApplicationInfo(openid_0,null,null,null,null,appid_0);
        System.out.println(JSON.toJSON(result_51));
        Result result_52 = testerController.getApplicationInfo(openid_0,null,null,null,phoneNumber_0,null);
        System.out.println(JSON.toJSON(result_52));
        Result result_53 = testerController.getApplicationInfo(openid_0,null,null,idNumber_0,null,null);
        System.out.println(JSON.toJSON(result_53));
        Result result_54 = testerController.getApplicationInfo(openid_0,sessionKey_0,null,null,null,null);
        System.out.println(JSON.toJSON(result_54));
        Result result_55 = testerController.getApplicationInfo(null,null,null,null,null,appid_0);
        System.out.println(JSON.toJSON(result_55));
        Result result_56 = testerController.getApplicationInfo(null,null,null,null,phoneNumber_0,null);
        System.out.println(JSON.toJSON(result_56));
        Result result_57 = testerController.getApplicationInfo(null,null,null,idNumber_0,null,null);
        System.out.println(JSON.toJSON(result_57));
        Result result_58 = testerController.getApplicationInfo(null,null,name_0,null,null,null);
        System.out.println(JSON.toJSON(result_58));
        Result result_59 = testerController.getApplicationInfo(null,sessionKey_0,null,null,null,null);
        System.out.println(JSON.toJSON(result_59));
        Result result_60 = testerController.getApplicationInfo(openid_0,null,null,null,null,null);
        System.out.println(JSON.toJSON(result_60));
        Result result_61 = testerController.getApplicationInfo(null,null,null,null,null,null);
        System.out.println(JSON.toJSON(result_61));
        Result result_62 = testerController.getApplicationInfo(openid_1,sessionKey_1,name_1,idNumber_1,phoneNumber_1,appid_1);
        System.out.println(JSON.toJSON(result_62));
        Result result_63 = testerController.getApplicationInfo(openid_2,sessionKey_2,name_2,idNumber_2,phoneNumber_2,appid_2);
        System.out.println(JSON.toJSON(result_63));
    }

    /**
     * 测试函数 registerTest
     * 用来测试函数    testerController.register(String openid,String sessionKey String appid);
     * 取值包括
     *         参数 null 值测试
     *
     *         String openid_0 = "87eth2q3";
     *         String sessionKey_0 = "7";
     *         String appid_0 = "398f73u";
     *
     *         String openid_1 = "2893rhty739283rj";
     *         String sessionKey_1 = ",;'ykl4590y";
     *         String appid_1 = "h930kg;l;p,mr321";
     *
     *         String openid_2 = "0;0mt2;4/3'trwl,ok";
     *         String sessionKey_2 = ";.',lkmuj,";
     *         String appid_2 = ";.mplo2089u";
     */
    @Test
    public void registerTest(){

        String openid_0 = "87eth2q3";
        String sessionKey_0 = "7";
        String appid_0 = "398f73u";

        String openid_1 = "2893rhty739283rj";
        String sessionKey_1 = ",;'ykl4590y";
        String appid_1 = "h930kg;l;p,mr321";

        String openid_2 = "0;0mt2;4/3'trwl,ok";
        String sessionKey_2 = ";.',lkmuj,";
        String appid_2 = ";.mplo2089u";

        Result result_0 = testerController.register(null,sessionKey_0,null,appid_0);
        System.out.println(JSON.toJSON(result_0));
        Result result_1 = testerController.register(openid_0,null,null,appid_0);
        System.out.println(JSON.toJSON(result_1));
        Result result_2 = testerController.register(openid_0,sessionKey_0,null,null);
        System.out.println(JSON.toJSON(result_2));
        Result result_3 = testerController.register(null,null,null,appid_0);
        System.out.println(JSON.toJSON(result_3));
        Result result_4 = testerController.register(null,sessionKey_0,null,null);
        System.out.println(JSON.toJSON(result_4));
        Result result_5 = testerController.register(openid_0,null,null,null);
        System.out.println(JSON.toJSON(result_5));
        Result result_6 = testerController.register(null,null,null,null);
        System.out.println(JSON.toJSON(result_6));
        Result result_7 = testerController.register(openid_0,sessionKey_0,null,appid_0);
        System.out.println(JSON.toJSON(result_7));
        Result result_8 = testerController.register(openid_1,sessionKey_1,null,appid_1);
        System.out.println(JSON.toJSON(result_8));
        Result result_9 = testerController.register(openid_2,sessionKey_2,null,appid_2);
        System.out.println(JSON.toJSON(result_9));

    }
}
