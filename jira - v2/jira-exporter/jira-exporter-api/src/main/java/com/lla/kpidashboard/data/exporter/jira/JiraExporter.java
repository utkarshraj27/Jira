package com.lla.kpidashboard.data.exporter.jira;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
/*@EnableAutoConfiguration
@ComponentScan
@EntityScan
@EnableJpaRepositories(basePackages="com.lla.kpidashboard.data.exporter.jira.repository")*/
public class JiraExporter{
	

	
	public static void main(final String[] args) {
		SpringApplication.run(JiraExporter.class, args);

	}
}
