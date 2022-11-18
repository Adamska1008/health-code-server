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
