package com.fankai;

import com.fankai.configuration.Person;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class DemoApplicationTests {

//    Logger logger = LoggerFactory.getLogger(getClass());

   /* @Autowired
    Person person;

    @Test
    void contextLoads() {
        System.out.println(person);
    }*/

    /*@Test
    void updateFile() {
        String path = "D:\\B站视频\\soft\\springboot";
        File file = new File(path);
        File[] array = file.listFiles();
        for (int i = 0; i < array.length; i++) {
            if (array[i].isFile()) {
                File person = array[i];
                String newName = person.getName().replace("SpringBoot_权威教程_spring boot_springboot核心篇+springboot整合篇-_雷丰阳_尚硅谷 P" + (i + 1) + " ", "");
                if (person.renameTo(new File(newName))) {
                    System.out.println("修改成功!");
                } else {
                    System.out.println("修改失败");
                }
            } else if (array[i].isDirectory()) {
                System.out.println("错误了");
            }
        }
    }*/

	/**
	 * 日志级别
	 */
	/*@Test
    void logTest() {
		logger.trace("trace日志是最低级别");
		logger.debug("debug日志是第二低级别，默认不输出");
		logger.info("info日志是第三低级别，默认输出");
		logger.warn("warn日志是第四低级别，默认输出");
		logger.error("error日志是最高级别，默认输出");

    }*/
}
