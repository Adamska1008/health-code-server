package com.healthcode.healthcodeserver.serviceTest;

import com.healthcode.healthcodeserver.entity.RemoteReporting;
import com.healthcode.healthcodeserver.service.RemoteReportingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.rmi.Remote;
import java.util.List;

@SpringBootTest
public class RemoteReportingServiceTest {
  @Autowired
  RemoteReportingService remoteReportingService;

  // 测试分页
  @Test
  public void listByPageTest() {
    List<RemoteReporting> all = remoteReportingService.list();
    List<RemoteReporting> page_one = remoteReportingService.listByPage(1, 5);
    for (int i = 0; i < 5; i++)
    {
      if (i >= all.size()) {
        break;
      }
      assert all.get(i).equals(page_one.get(i));
    }
    List<RemoteReporting> page_two = remoteReportingService.listByPage(2, 5);
    for (int i = 5; i < 10; i++) {
      if (i >= all.size()) {
        break;
      }
      assert all.get(i).equals(page_two.get(i - 5));
    }
  }
}
