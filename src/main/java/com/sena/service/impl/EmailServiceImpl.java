package com.sena.service.impl;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.sena.exception.system.EmailException;
import com.sena.model.EmailModel;
import com.sena.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sena on 2017/3/16.
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Value("${app.supportEmail}")
    private String appSupportEmail;
    @Value("${app.name}")
    private String appName;
    @Autowired
    private MandrillApi mandrillApi;

    @Override
    public void sendAsync(EmailModel email) throws EmailException {
        MandrillMessage message = new MandrillMessage();
        message.setSubject(email.getSubject());
        message.setHtml(email.getHtml());
        message.setText(email.getText());
        message.setFromEmail(appSupportEmail);
        message.setFromName(appName);
        message.setAttachments(email.getAttachments());

        Map<String, String> header = new HashMap<>();
        if (email.getReplyTo() == null) {
            header.put("Reply-To", appSupportEmail);
        } else {
            header.put("Reply-To", email.getReplyTo());
        }
        message.setTags(email.getTags());
        List<MandrillMessage.Recipient> recipients = new ArrayList<>();
        for (String to : email.getTo()) {
            MandrillMessage.Recipient recipient = new MandrillMessage.Recipient();
            recipient.setEmail(to);
            recipient.setType(MandrillMessage.Recipient.Type.TO);
            recipients.add(recipient);
        }
        for (String cc : email.getCc()) {
            MandrillMessage.Recipient recipient = new MandrillMessage.Recipient();
            recipient.setEmail(cc);
            recipient.setType(MandrillMessage.Recipient.Type.CC);
            recipients.add(recipient);
        }
        for (String bcc : email.getBcc()) {
            MandrillMessage.Recipient recipient = new MandrillMessage.Recipient();
            recipient.setEmail(bcc);
            recipient.setType(MandrillMessage.Recipient.Type.BCC);
            recipients.add(recipient);
        }
        message.setTo(recipients);
        try {
            mandrillApi.messages().send(message, true);
        } catch (Exception e) {
            throw new EmailException();
        }
    }
}
