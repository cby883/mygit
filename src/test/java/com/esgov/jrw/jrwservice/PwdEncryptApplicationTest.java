package com.esgov.jrw.jrwservice;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 描述: 加密
 *
 * @author Yangjinming
 * @create 2018-04-03 下午1:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PwdEncryptApplicationTest {
    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    public void encryptPwd() {
        try{
        String result = stringEncryptor.encrypt("要加密的串");
        System.out.println(result);
        }catch (Exception e){

        }
    }
}
