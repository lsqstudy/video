package com.lsqstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@EnableMongoRepositories("com.lsqstudy.bussiness.dao")
@EnableJpaRepositories("com.lsqstudy.system.dao")
public class VideoCmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoCmsApplication.class, args);
	}

}
