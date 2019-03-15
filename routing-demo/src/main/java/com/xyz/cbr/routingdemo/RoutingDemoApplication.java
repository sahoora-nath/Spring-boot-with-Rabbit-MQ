package com.xyz.cbr.routingdemo;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RoutingDemoApplication {

	@Value("${spring.rabbitmq.host}")
	private String hostName;

	@Value("${spring.rabbitmq.port}")
	private String port;

	public static void main(String[] args) {
		SpringApplication.run(RoutingDemoApplication.class, args);
	}

	@Bean
	public ConnectionFactory rabbitConnectionFactory(){
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(hostName);
		connectionFactory.setPort(Integer.valueOf(port));
		connectionFactory.setAutomaticRecoveryEnabled(true);
		// more config options here etc
		return connectionFactory;
	}
}
