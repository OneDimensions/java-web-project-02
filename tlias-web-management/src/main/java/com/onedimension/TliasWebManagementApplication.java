package com.onedimension;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan // 开启springboot对servlet组件的支持
@SpringBootApplication // 具备组件扫码功能,但是只会扫描启动类所在包及其子包
// 如果需要扫描其他包的@component注解, 需要加上@componentScan(basePackages = "com.onedimension") 的注解
public class TliasWebManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TliasWebManagementApplication.class, args);
	}

}
