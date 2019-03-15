package com.xyz.cbr.routingdemo.routes;

import com.xyz.cbr.routingdemo.bean.NotificationBean;
import com.xyz.cbr.routingdemo.entity.NotificationAudit;
import com.xyz.cbr.routingdemo.service.CbrService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CbrCamelRoutes extends RouteBuilder {

    @Autowired
    private CbrService cbrService;

    @Override
    public void configure() throws Exception {
        from("direct:firstRoute")
                .log("Camel body: ${body}")
                .to("rabbitmq:NOTIFICATION")
                .end();

        from("rabbitmq:NOTIFICATION")
                .log("From RabbitMQ: ${body}")

                .unmarshal().json(JsonLibrary.Jackson, NotificationBean.class)
                .process(exchange -> {

                    NotificationBean response = exchange.getIn().getBody(NotificationBean.class);
                    exchange.getIn().setHeader("documentAuthority", response.getDocumentAuthority());
                    exchange.getIn().setHeader("documentType", response.getDocumentType());

                    //insert Audit in mongo.
                    NotificationAudit notificationAudit = mapBeanToDomain(response);
                    NotificationAudit notificationAuditPersisted = this.cbrService.insertAudit(notificationAudit);
                })
                .log("Re-routing message to : ${in.header.documentAuthority}")
                .marshal().json(JsonLibrary.Jackson, String.class)
                .removeHeader("rabbitmq:NOTIFICATION")
                .toD("rabbitmq:NOTIFICATION.${in.header.documentAuthority}.${in.header.documentType}")
                .end();


        from("rabbitmq://localhost:5672/NOTIFICATION.LA0246.UC9999")
                .log("From Local authority LA0246 : ${body}")
                .end();
    }

    private NotificationAudit mapBeanToDomain(NotificationBean notificationMsg) {
        NotificationAudit notificationAudit = new NotificationAudit();
        notificationAudit.setReference(notificationMsg.getDocumentId());
        notificationAudit.setAuthority(notificationMsg.getDocumentAuthority());
        notificationAudit.setContent(notificationMsg.getDocumentContent());
        notificationAudit.setType(notificationMsg.getDocumentType());

        return notificationAudit;
    }
}
