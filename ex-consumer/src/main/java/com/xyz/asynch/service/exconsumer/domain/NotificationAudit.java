package com.xyz.asynch.service.exconsumer.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class NotificationAudit {

    private String reference;
    private String type;
    private String authority;
    private String content;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
