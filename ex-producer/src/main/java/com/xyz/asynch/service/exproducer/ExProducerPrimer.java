package com.xyz.asynch.service.exproducer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ExProducerPrimer implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExProducerPrimer.class);

    private final RabbitTemplate rabbitTemplate;
    private final ConfigurableApplicationContext context;
    private final ObjectMapper objectMapper;

    @Value("${amqp.queue.name}")
    private String queueName;

    @Autowired
    public ExProducerPrimer(RabbitTemplate rabbitTemplate, ConfigurableApplicationContext context, ObjectMapper objectMapper){
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        //objectMapper is required to convert object to jsonString
        String inputStr = "{\n" +
                "    \"documentId\" : \"32b568bc-4104-4f85-b675-165c5ed18733\",\n" +
                "    \"documentType\" : \"UC9999\",\n" +
                "    \"documentAuthority\" : \"LA0246\",\n" +
                "    \"documentContent\" : \"Lorem ipsum dolor sit amet, consectetur adipiscing...\"\n" +
                "}\n";

        LOGGER.info("Sending Message.");
        rabbitTemplate.convertAndSend(queueName, inputStr);

        context.close();
    }
}
