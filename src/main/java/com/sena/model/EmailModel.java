package com.sena.model;

import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import org.springframework.util.Base64Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sena on 2017/3/16.
 */
public class EmailModel {
    private List<String> to;
    private String replyTo;
    private String subject;
    private String html;
    private String text;
    private List<String> tags;
    private List<String> cc;
    private List<String> bcc;
    private List<MandrillMessage.MessageContent> attachments = new ArrayList<>();

    public EmailModel(){
        this.to=new ArrayList<>();
        this.tags=new ArrayList<>();
        this.cc = new ArrayList<>();
        this.bcc = new ArrayList<>();
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setTags(String... tags){
        ArrayList ts = new ArrayList();
        String[] arr$ = tags;
        int len$ = tags.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String tag = arr$[i$];
            ts.add(tag);
        }

        if(!ts.isEmpty()) {
            this.tags = ts;
        } else {
            this.tags = Collections.emptyList();
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(String to){
        List<String> tos=new ArrayList<>();
        tos.add(to);
        this.setTo(tos);
    }
    public void setTo(List<String> to) {
        this.to = to;
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public List<String> getBcc() {
        return bcc;
    }

    public void setBcc(List<String> bcc) {
        this.bcc = bcc;
    }

    public List<MandrillMessage.MessageContent> getAttachments() {
        return attachments;
    }

    public void addAttachment(String type, String name, byte[] stream) {
        MandrillMessage.MessageContent messageContent=new MandrillMessage.MessageContent();
        messageContent.setType(type);
        messageContent.setContent(Base64Utils.encodeToString(stream));
        messageContent.setName(name);
        this.attachments.add(messageContent);
    }

    public void setAttachments(List<MandrillMessage.MessageContent> attachments) {
        this.attachments = attachments;
    }
}
