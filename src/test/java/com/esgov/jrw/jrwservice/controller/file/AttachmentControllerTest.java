package com.esgov.jrw.jrwservice.controller.file;


import com.esgov.jrw.jrwservice.Application;
import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;
import com.esgov.jrw.jrwservice.controller.authority.SysMenuController;
import com.esgov.jrw.jrwservice.entity.enums.StatusEnum;
import com.esgov.jrw.jrwservice.entity.file.Attachment;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration

public class AttachmentControllerTest {

    @Autowired
    private AttachmentController attachmentController;

    @Test
    public void save(){
        Attachment attachment = new Attachment();
        attachment.setFileName("abc.jpg");
        attachment.setFilePath("/group1/xx/xx/xxxxxxxx.jpg");
        attachment.setFileSize(1024L);
        attachment.setFileType("jpg");
        attachment.setRemark("测试");
        attachment.setTargetFlag("xx");
        attachment.setTargetType("xxxxx");
        attachment.setTargetId("xxxxxxxxxxxxxxxxxx");
        attachment.setStatus(StatusEnum.NORMAL);
        attachment.setCreator("xxx");
        attachment.setCreateTime(new Date());
        attachment.setUpdateUser("yyyy");
        attachment.setUpdateTime(new Date());
        ServiceResponse response = attachmentController.save(attachment);

        System.out.println(ToStringBuilder.reflectionToString(response));

    }

    @Test
    public void delete(){
        ServiceResponse response = attachmentController.delete("8d1b2632963640aa835cd5e6b885d608");
        System.out.println(ToStringBuilder.reflectionToString(response));
    }

    @Test
    public void findByTarget(){
        ServiceResponse response = attachmentController.findByTarget("xxxxx","xxxxxxxxxxxxxxxxxx","xx");
        System.out.println(ToStringBuilder.reflectionToString(response));
    }
}
