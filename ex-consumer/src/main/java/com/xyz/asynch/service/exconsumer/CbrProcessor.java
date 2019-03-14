package com.xyz.asynch.service.exconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xyz.asynch.service.exconsumer.bean.NotificationBean;
import com.xyz.asynch.service.exconsumer.domain.NotificationAudit;
import com.xyz.asynch.service.exconsumer.service.CbrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CbrProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CbrProcessor.class);

    private final ObjectMapper objectMapper;
    private final CbrService cbrService;

    @Autowired
    public CbrProcessor(ObjectMapper objectMapper, CbrService cbrService){
        this.objectMapper=objectMapper;
        this.cbrService = cbrService;
    }

    public void receiveMessage(String inputJson) {
        LOGGER.info("Message received.");
        try {
            NotificationBean notificationMsg =
                    this.objectMapper.readValue(inputJson, NotificationBean.class);
            LOGGER.info("Message ready for Audit");

            NotificationAudit notificationAudit = mapBeanToDomain(notificationMsg);

            NotificationAudit notificationAuditPersisted = this.cbrService.insertAudit(notificationAudit);
            LOGGER.info("Audit inserted Successfully for ref: {}", notificationAuditPersisted.getReference());

        } catch (IOException exc) {
            LOGGER.error("Exception while readValue", exc);
        }
    }

    private NotificationAudit mapBeanToDomain(NotificationBean notificationMsg) {
        NotificationAudit notificationAudit = new NotificationAudit();
        notificationAudit.setReference(notificationMsg.getDocumentId());
        notificationAudit.setAuthority(notificationMsg.getDocumentAuthority());
        notificationAudit.setContent(notificationMsg.getDocumentContent());
        notificationAudit.setType(notificationMsg.getDocumentType());

        return notificationAudit;
    }

    private String getNotificationJsonString(NotificationAudit notificationAudit) {
        try {
            return objectMapper.writeValueAsString(notificationAudit);
        } catch (JsonProcessingException e) {
            LOGGER.error("Exception while write Value as Json String", e);
        }
        return null;
    }

}
