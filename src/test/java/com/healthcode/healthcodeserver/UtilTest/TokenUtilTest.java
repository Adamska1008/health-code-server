package com.healthcode.healthcodeserver.UtilTest;

import com.healthcode.healthcodeserver.util.TokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenUtilTest {
    @Autowired
    TokenUtil tokenUtil;

    /**
     * 测试函数 verifyTest
     * 用来测试函数    tokenUtil.verify(String token);
     * 取值包括
     *         String token_0 = "123123f";
     *         String token_1 = "11123524322";
     *         String token_2 = "1";
     *         String token_3 = "afsjansfafasf";
     *         String token_4 = ",[;ph[hjikg";
     *         String token_5 = "2387weru;'.lmw;";
     *         String token_6 = "正确的1827ehq";
     *         String token_7 = "fgw98u39;'k,o";
     *         String token_8 = "a";
     *         String token_9 = "1wqesdawa231";
     */
    @Test
    public void verifyTest(){
        String token_0 = "123123f";
        String token_1 = "11123524322";
        String token_2 = "1";
        String token_3 = "afsjansfafasf";
        String token_4 = ",[;ph[hjikg";
        String token_5 = "2387weru;'.lmw;";
        String token_6 = "正确的1827ehq";
        String token_7 = "fgw98u39;'k,o";
        String token_8 = "a";
        String token_9 = "1wqesdawa231";


        System.out.println(tokenUtil.verify(null));
        System.out.println(tokenUtil.verify(token_0));
        System.out.println(tokenUtil.verify(token_1));
        System.out.println(tokenUtil.verify(token_2));
        System.out.println(tokenUtil.verify(token_3));
        System.out.println(tokenUtil.verify(token_4));
        System.out.println(tokenUtil.verify(token_5));
        System.out.println(tokenUtil.verify(token_6));
        System.out.println(tokenUtil.verify(token_7));
        System.out.println(tokenUtil.verify(token_8));
        System.out.println(tokenUtil.verify(token_9));
    }

    /**
     * 测试函数 getAccountIdTest
     * 用来测试函数    tokenUtil.getAccountId(String token);
     * 取值包括
     *         String token_0 = "123123f";
     *         String token_1 = "11123524322";
     *         String token_2 = "1";
     *         String token_3 = "afsjansfafasf";
     *         String token_4 = ",[;ph[hjikg";
     *         String token_5 = "2387weru;'.lmw;";
     *         String token_6 = "正确的1827ehq";
     *         String token_7 = "fgw98u39;'k,o";
     *         String token_8 = "a";
     *         String token_9 = "1wqesdawa231";
     */
    @Test
    public void getAccountIdTest(){
        String token_0 = "123123f";
        String token_1 = "11123524322";
        String token_2 = "1";
        String token_3 = "afsjansfafasf";
        String token_4 = ",[;ph[hjikg";
        String token_5 = "2387weru;'.lmw;";
        String token_6 = "正确的1827ehq";
        String token_7 = "fgw98u39;'k,o";
        String token_8 = "a";
        String token_9 = "1wqesdawa231";

        System.out.println(tokenUtil.getAccountId(null));
        System.out.println(tokenUtil.getAccountId(token_0));
        System.out.println(tokenUtil.getAccountId(token_1));
        System.out.println(tokenUtil.getAccountId(token_2));
        System.out.println(tokenUtil.getAccountId(token_3));
        System.out.println(tokenUtil.getAccountId(token_4));
        System.out.println(tokenUtil.getAccountId(token_5));
        System.out.println(tokenUtil.getAccountId(token_6));
        System.out.println(tokenUtil.getAccountId(token_7));
        System.out.println(tokenUtil.getAccountId(token_8));
        System.out.println(tokenUtil.getAccountId(token_9));
    }
}
