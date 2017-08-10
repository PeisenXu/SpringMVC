package com.common.model;

/**
 * Created by Sena on 2017/3/16.
 */
public class EmailModel {
    private String userName;
    private String to;
    private String subject;
    private String messageHtml;
    private String attachment;
    private String attachmentName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageHtml() {
        return messageHtml;
    }

    public void setMessageHtml(String messageHtml) {
        this.messageHtml = messageHtml;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
}
