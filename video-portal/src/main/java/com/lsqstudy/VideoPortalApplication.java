package com.lsqstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.UrlPathHelper;

@SpringBootApplication
@EnableMongoRepositories("com.lsqstudy.dao")
@EnableJpaRepositories("com.lsqstudy.dao")
public class VideoPortalApplication  {

	public static void main(String[] args)  {
		SpringApplication.run(VideoPortalApplication.class, args);
	}
}
