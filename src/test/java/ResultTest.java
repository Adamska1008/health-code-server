import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.healthcode.healthcodeserver.common.Result;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ResultTest {
  @Test
  public void UnitTest() {
  }

  /**
   * 存放数据测试
   */
  @Test
  public void putDataTest() {
    JSONObject object = JSONObject.parseObject("{'1': '2', '3': '5'}");
    Result res = new Result();
    object.forEach(res::putData);
  }
}
