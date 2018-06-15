package com.esgov.jrw.sysmgrservice;

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
        try {
            String result = stringEncryptor.encrypt("zshj");
            System.out.println(result);
            result = stringEncryptor.encrypt("zshjjrb");
            System.out.println(result);
        } catch (Exception e) {

        }
    }
}
