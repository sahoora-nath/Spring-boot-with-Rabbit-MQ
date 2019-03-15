package com.xyz.cbr.routingdemo.controller;


import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CbrController {

    @Autowired
    private CamelContext camelContext;

    @Autowired
    private ProducerTemplate producerTemplate;

    @RequestMapping(value = "/")
    public void startCamel() {
        producerTemplate.sendBody("direct:firstRoute", "{\n" +
                "    \"documentId\" : \"32b568bc-4104-4f85-b675-165c5ed18733\",\n" +
                "    \"documentType\" : \"UC9999\",\n" +
                "    \"documentAuthority\" : \"LA0246\",\n" +
                "    \"documentContent\" : \"Lorem ipsum dolor sit amet, consectetur adipiscing...\"\n" +
                "}\n");
    }
}
