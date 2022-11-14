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

@SpringBootTest
public class TesterServiceTest {
  @Autowired
  TransferCodeInfoService transferCodeInfoService;
  @Autowired
  TesterService testerService;
  @Test
  public void test(){
    System.out.println(testerService.isTester("1"));
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
}
