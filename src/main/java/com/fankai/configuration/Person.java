package com.fankai.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Component //将组件加载进spring容器中
//@ConfigurationProperties(prefix = "person")
@Validated   //数据校验注解
public class Person {

    @Value("${person.name}")  //spring底层注解
    private String name;
    private Integer age;
    private Long tellPhone;
    private Boolean sex;
    private Date birth;
    private Map map;
    private List<Integer> list;
}
