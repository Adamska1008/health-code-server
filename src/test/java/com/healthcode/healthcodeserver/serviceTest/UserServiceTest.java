package com.healthcode.healthcodeserver.serviceTest;

import com.alibaba.fastjson2.JSON;
import com.healthcode.healthcodeserver.entity.User;
import com.healthcode.healthcodeserver.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
  @Autowired
  UserService userService;

  @Test
  public void UnitTest() {
    getUserInfoByPersonIdTest("35341719961123514X");
    getUserInfoByOpenIdTest("0");
  }

  @Test
  public void getUserInfoByPersonIdTest(String personId) {
    User user = userService.getUserInfoByPersonId(personId);
    System.out.println(JSON.toJSON(user));
  }

  @Test
  public void getUserInfoByOpenIdTest(String openId) {
    User user = userService.getUserInfoByOpenId(openId);
    System.out.println(JSON.toJSON(user));
  }

    /**
     * 测试函数 insertUserInfoTest
     * 用来测试函数    userService.insertUserInfo(String personId ,
     *       String personName ,
     *       String phoneNumber ,
     *       String wxOpenid_0 ,
     *       String gender_0 );
     * 取值包括
     *   String personId_0 = "1312d1";
     *       String personName_0 = "iuut";
     *       String phoneNumber_0 = "1234567898";
     *       String wxOpenid_0 = "d1382you1";
     *       String gender_0 = "0";
     *
     *       String personId_1 = "d32uj7ew8rwq89ujw";
     *       String personName_1 = "d;KAMNKJLSNDFASMFOA,LK";
     *       String phoneNumber_1 = "1";
     *       String wxOpenid_1 = "d1382you1FQ'PO/;'.L";
     *       String gender_1 = "15";
     *
     *       String personId_2 = "131DASFOINJA031984';,2d1";
     *       String personName_2 = "ui那部分打arafaa";
     *       String phoneNumber_2 = "1231241252341341";
     *       String wxOpenid_2 = "vsko.l;3ugd83qkfasd";
     *       String gender_2 = "1";
     *
     *       String personId_3 = "1";
     *       String personName_3 = ",kdfh2782jdika";
     *       String phoneNumber_3 = "1234567898";
     *       String wxOpenid_3 = "sdf89klmasd;.mjkias";
     *       String gender_3 = "0";
     */
  @Test
  public void insertUserInfoTest(){
    //User user = userService.getUserInfoByOpenId(openId);
    //System.out.println(user);
      String personId_0 = "1312d1";
      String personName_0 = "iuut";
      String phoneNumber_0 = "1234567898";
      String wxOpenid_0 = "d1382you1";
      String gender_0 = "0";

      String personId_1 = "d32uj7ew8rwq89ujw";
      String personName_1 = "d;KAMNKJLSNDFASMFOA,LK";
      String phoneNumber_1 = "1";
      String wxOpenid_1 = "d1382you1FQ'PO/;'.L";
      String gender_1 = "15";

      String personId_2 = "131DASFOINJA031984';,2d1";
      String personName_2 = "ui那部分打arafaa";
      String phoneNumber_2 = "1231241252341341";
      String wxOpenid_2 = "vsko.l;3ugd83qkfasd";
      String gender_2 = "1";

      String personId_3 = "1";
      String personName_3 = ",kdfh2782jdika";
      String phoneNumber_3 = "1234567898";
      String wxOpenid_3 = "sdf89klmasd;.mjkias";
      String gender_3 = "0";

      System.out.println(userService.insertUserInfo(null , personName_0 , phoneNumber_0 , wxOpenid_0 , gender_0 ));
      System.out.println(userService.insertUserInfo(personId_0 , null , phoneNumber_0 , wxOpenid_0 , gender_0 ));
      System.out.println(userService.insertUserInfo(personId_0 , personName_0 , null , wxOpenid_0 , gender_0 ));
      System.out.println(userService.insertUserInfo(personId_0 , personName_0 , phoneNumber_0 , null , gender_0 ));
      System.out.println(userService.insertUserInfo(personId_0 , personName_0 , phoneNumber_0 , wxOpenid_0 , null ));
      System.out.println(userService.insertUserInfo(null , null , phoneNumber_0 , wxOpenid_0 , gender_0 ));
      System.out.println(userService.insertUserInfo(null , personName_0 , null , wxOpenid_0 , gender_0 ));
      System.out.println(userService.insertUserInfo(null , personName_0 , phoneNumber_0 , null , gender_0 ));
      System.out.println(userService.insertUserInfo(null , personName_0 , phoneNumber_0 , wxOpenid_0 , null ));
      System.out.println(userService.insertUserInfo(personId_0 , null , null , wxOpenid_0 , gender_0 ));
      System.out.println(userService.insertUserInfo(personId_0 , null , phoneNumber_0 , null , gender_0 ));
      System.out.println(userService.insertUserInfo(personId_0 , null , phoneNumber_0 , wxOpenid_0 , null ));
      System.out.println(userService.insertUserInfo(personId_0 , personName_0 , null , null , gender_0 ));
      System.out.println(userService.insertUserInfo(personId_0 , personName_0 , null , wxOpenid_0 , null ));
      System.out.println(userService.insertUserInfo(personId_0 , personName_0 , phoneNumber_0 , null , null ));
      System.out.println(userService.insertUserInfo(null , null , null , wxOpenid_0 , gender_0 ));
      System.out.println(userService.insertUserInfo(null , null , phoneNumber_0 , null , gender_0 ));
      System.out.println(userService.insertUserInfo(null , null , phoneNumber_0 , wxOpenid_0 , null ));
      System.out.println(userService.insertUserInfo(null , personName_0 , null , null , gender_0 ));
      System.out.println(userService.insertUserInfo(null , personName_0 , null , wxOpenid_0 , null ));
      System.out.println(userService.insertUserInfo(null , personName_0 , phoneNumber_0 , null , null ));
      System.out.println(userService.insertUserInfo(personId_0 , null , null , null , gender_0 ));
      System.out.println(userService.insertUserInfo(personId_0 , null , null , wxOpenid_0 , null ));
      System.out.println(userService.insertUserInfo(personId_0 , personName_0 , null , null , null ));
      System.out.println(userService.insertUserInfo(personId_0 , null , phoneNumber_0 , null , null ));
      System.out.println(userService.insertUserInfo(null , null , null , null , gender_0 ));
      System.out.println(userService.insertUserInfo(null , null , null , wxOpenid_0 , null ));
      System.out.println(userService.insertUserInfo(null , null , phoneNumber_0 , null , null ));
      System.out.println(userService.insertUserInfo(null , personName_0 , null , null , null ));
      System.out.println(userService.insertUserInfo(personId_0 , null , null , null , null ));
      System.out.println(userService.insertUserInfo(personId_0 , personName_0 , phoneNumber_0 , wxOpenid_0 , gender_0 ));
      System.out.println(userService.insertUserInfo(personId_1 , personName_1 , phoneNumber_1 , wxOpenid_1 , gender_1 ));
      System.out.println(userService.insertUserInfo(personId_2 , personName_2 , phoneNumber_2 , wxOpenid_2 , gender_2 ));
      System.out.println(userService.insertUserInfo(personId_3 , personName_3 , phoneNumber_3 , wxOpenid_3 , gender_3 ));



  }
}
