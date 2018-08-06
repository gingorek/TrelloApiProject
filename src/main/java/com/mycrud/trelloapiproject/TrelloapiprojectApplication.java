package com.mycrud.trelloapiproject;

import com.mycrud.trelloapiproject.domain.TaskDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class TrelloapiprojectApplication  {

	public static void main(String[] args) {
		SpringApplication.run(TrelloapiprojectApplication.class, args);

	}

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
//		return application.sources(TrelloapiprojectApplication.class);
//
//	}
}
