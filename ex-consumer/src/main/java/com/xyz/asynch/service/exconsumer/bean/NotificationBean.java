package com.xyz.asynch.service.exconsumer.bean;

public class NotificationBean {
    private String documentId;
    private String documentType;
    private String documentAuthority;
    private String documentContent;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentAuthority() {
        return documentAuthority;
    }

    public void setDocumentAuthority(String documentAuthority) {
        this.documentAuthority = documentAuthority;
    }

    public String getDocumentContent() {
        return documentContent;
    }

    public void setDocumentContent(String documentContent) {
        this.documentContent = documentContent;
    }
}
