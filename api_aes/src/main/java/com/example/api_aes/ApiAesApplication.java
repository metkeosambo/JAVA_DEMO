package com.example.api_aes;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.api_aes.model.Position;


@Configuration
@MappedTypes({Position.class})
@MapperScan("com.example.api_aes.mapper")
//@ComponentScan({"com.example.api_aes.config", "com.example.api_aes.test",""})
@SpringBootApplication
public class ApiAesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAesApplication.class, args);
	}

}
