package org.linlinjava.litemall.os;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.linlinjava.litemall.os.service.AliyunOsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.FileCopyUtils;

import java.io.FileInputStream;
import java.io.IOException;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AosTest {
    @Autowired
    private AliyunOsService aliyunOsService;

    @Test
    public void test() throws IOException {
        String test = getClass().getClassLoader().getResource("litemall.png").getFile();
        byte[] content =  (byte[])FileCopyUtils.copyToByteArray(new FileInputStream(test));
        MockMultipartFile mockMultipartFile = new MockMultipartFile("litemall.png", "litemall.png", "image/png", content);
        aliyunOsService.store(mockMultipartFile, "aos.png");
        Resource resource = aliyunOsService.loadAsResource("aos.png");
        String url = aliyunOsService.generateUrl("aos.png");
        System.out.println("test file " + test);
        System.out.println("store file " + resource.getURI());
        System.out.println("generate url " + url);

//        tencentOsService.delete("aos.png");
    }

}