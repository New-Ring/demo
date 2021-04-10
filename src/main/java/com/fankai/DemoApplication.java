package com.fankai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

/**
 * @SpringBootApplication注解详解：
 * 1.该注解是组合注解；由SpringBootConfiguration、EnableAutoConfiguration
 * 2.Configuration:配置类，解析配置文件。
 * 3.EnableAutoConfiguration：开启自动配置。
 * 		①由@AutoConfigurationPackage:自动配置包
 * 			①@Import(AutoConfigurationPackages.Registrar.class)导入自动扫描组件
 * 			②自动扫面组件将主配置类下的所在包及下面的所有子包的组件都扫描的spring容器中。
 * 		②@Import(AutoConfigurationImportSelector.class)：导入组件选择器
 * 			给容器中导入应用所需要的配置类，并配置好这些类。就不需要手动编写spring配置和注入功能主键。
 * 		该类会在springboot启动过程中从类路径下的META-INF/spring.factories中获取指定的值。
 * 		指定值所扫描的包在spring-boot-auto-configure-XXXX(version).jar的包下
 *
 * 	SpringBoot自动配置原理：
 * 		1.启动时加载主配置类：开启自动配置@EnableAutoConfiguration；
 * 		2.@EnableAutoConfiguration:利用selectTor选择器扫描类路径下的所有jar包，将扫描到的内容包装成properties对象
 * 		3.将类路径下的META-INF/spring.factories里面的所有EnableAutoConfiguration值加载到容器中
 * 		4.每一个自动配置类进行自动配置功能；
 */
