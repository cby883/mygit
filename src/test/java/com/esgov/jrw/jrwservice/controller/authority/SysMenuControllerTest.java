package com.esgov.jrw.jrwservice.controller.authority;

import com.esgov.jrw.jrwservice.Application;
import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;
import com.esgov.jrw.jrwservice.controller.authority.SysMenuController;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class SysMenuControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SysMenuController sysMenuController;

    @Test
    public void getTest() {
//
//        ServiceResponse user1 = restTemplate.getForObject("/sysmgr/service/sysmenu/menus/04fa6b0a57a1f5640157a1fd6d720003", ServiceResponse.class);
//        System.out.println(user1.toString());

        ServiceResponse user2 =  sysMenuController.get("04fa6b0a57a1f5640157a1fd6d720003");
        System.out.println(ToStringBuilder.reflectionToString(user2));

    }
}
