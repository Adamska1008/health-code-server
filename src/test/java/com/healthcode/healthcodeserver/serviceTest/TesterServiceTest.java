package com.healthcode.healthcodeserver.serviceTest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.healthcode.healthcodeserver.entity.TransferCodeInfo;
import com.healthcode.healthcodeserver.entity.User;
import com.healthcode.healthcodeserver.service.TesterService;
import com.healthcode.healthcodeserver.service.TransferCodeInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TesterServiceTest {
  @Autowired
  TransferCodeInfoService transferCodeInfoService;
  @Autowired
  TesterService testerService;


  /**
   * 测试函数 test
   * 用来测试函数    testerService.isTester(String openId);
   * 取值包括
   *     String openId_0 = "1";
   *     String openId_1 = "12412414";
   *     String openId_2 = "sdjkfnskad";
   *     String openId_3 = "';.,.;[],";
   *     String openId_4 = "312eqadcq3./kol.";
   *     String openId_5 = "123q89weijders';.ldmbd,'12leq21";
   *     String openId_6 = "偶啊到房贫穷我今儿";
   *     String openId_7 = "fgpsgf2332r';.aid";
   *     String openId_8 = "asfkm克拉夫都34312;.";
   */
  @Test
  public void test(){
    String openId_0 = "1";
    String openId_1 = "12412414";
    String openId_2 = "sdjkfnskad";
    String openId_3 = "';.,.;[],";
    String openId_4 = "312eqadcq3./kol.";
    String openId_5 = "123q89weijders';.ldmbd,'12leq21";
    String openId_6 = "偶啊到房贫穷我今儿";
    String openId_7 = "fgpsgf2332r';.aid";
    String openId_8 = "asfkm克拉夫都34312;.";

    System.out.println(testerService.isTester(null));
    System.out.println(testerService.isTester(openId_0));
    System.out.println(testerService.isTester(openId_1));
    System.out.println(testerService.isTester(openId_2));
    System.out.println(testerService.isTester(openId_3));
    System.out.println(testerService.isTester(openId_4));
    System.out.println(testerService.isTester(openId_5));
    System.out.println(testerService.isTester(openId_6));
    System.out.println(testerService.isTester(openId_7));
    System.out.println(testerService.isTester(openId_8));
  }
  @Test
  public void isTester(){
    System.out.println(testerService.isTester("ojKoj52igjq_xw7MpIKZ4LUZJnH8"));
  }
  @Test
  public void selectPage(){
    QueryWrapper<TransferCodeInfo> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("tester_open_id","ojKoj52igjq_xw7MpIKZ4LUZJnH8");
    queryWrapper.eq("is_transferred",0);
    Page<TransferCodeInfo> page = new Page<>(1,2);
    IPage<TransferCodeInfo> iPage = transferCodeInfoService.page(page,queryWrapper);
    System.out.println("总页数"+iPage.getPages());
    System.out.println("总记录数"+iPage.getTotal());
    iPage.getRecords().forEach(System.out::println);
  }
  @Test
  public void selectTransferList(){
    List<String> list = new ArrayList<>();
    list.add("JSON122537789");
    list.add("JSON122543789");
    transferCodeInfoService.transferList(list);
  }
}
