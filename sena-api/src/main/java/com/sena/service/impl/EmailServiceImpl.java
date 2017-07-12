package com.sena.service.impl;

import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.sena.dao.EmailDao;
import com.sena.enums.EmailStatusType;
import com.sena.message.MessageInfo;
import com.sena.model.EmailModel;
import com.sena.result.Result;
import com.sena.service.EmailService;
import com.sena.util.FileUtil;
import com.sena.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * Created by Sena on 2017/3/16.
 */
@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private EmailDao emailDao;

    // 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    // PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
    //     对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
    public static String myEmailAccount = "email.prompt@foxmail.com";
    public static String myEmailPassword = "pviddftxnfcebhae";
    public static String myEmailName = "Confidential";

    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
    // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
    public static String myEmailSMTPHost = "smtp.qq.com";

    // 收件人邮箱（替换为自己知道的有效邮箱）
    public static String receiveMailAccount = "shadowred@foxmail.com";

    public static String mailSuffix = "<br/><br/>------------------<br/><br/>\n" +
            "The information contained in this communication is intended solely for the use of the individual or entity to " +
            "whom it is addressed and others authorized to receive it...";

    @Override
    public Result<String> sendEmail(final EmailModel emailModel) {
        if (StringUtil.isEmptyOrBlank(emailModel.getUserName())) {
            return Result.result(MessageInfo.USER_PARAM_IS_NULL_CODE, "Sender name cannot be empty.");
        }
        if (StringUtil.isEmptyOrBlank(emailModel.getTo())) {
            return Result.result(MessageInfo.USER_PARAM_IS_NULL_CODE, "Recipient mailbox cannot be empty.");
        }
        if (StringUtil.isEmptyOrBlank(emailModel.getSubject())) {
            return Result.result(MessageInfo.USER_PARAM_IS_NULL_CODE, "Message headers cannot be empty.");
        }
        if (StringUtil.isEmptyOrBlank(emailModel.getMessageHtml())) {
            return Result.result(MessageInfo.USER_PARAM_IS_NULL_CODE, "Mail content cannot be empty.");
        }
        if (StringUtil.isNotEmptyOrBlank(emailModel.getAttachment()) && StringUtil.isEmptyOrBlank(emailModel.getAttachmentName())) {
            return Result.result(MessageInfo.USER_PARAM_IS_NULL_CODE, "The attachment name cannot be empty(AS: log.txt).");
        } else if (StringUtil.isNotEmptyOrBlank(emailModel.getAttachment()) && StringUtil.isNotEmptyOrBlank(emailModel.getAttachmentName())) {
            if (emailModel.getAttachmentName().indexOf(".") < 0) {
                return Result.result(MessageInfo.USER_PARAM_IS_NULL_CODE, "Attachment name format error(AS: log.txt).");
            }
        }
        myEmailName = emailModel.getUserName();
        receiveMailAccount = emailModel.getTo();
        final String emailId = UUID.randomUUID().toString();
        emailDao.createEmailRecord(emailId, myEmailName, receiveMailAccount, emailModel.getAttachmentName(), emailModel.getAttachment(),
                emailModel.getSubject(), emailModel.getMessageHtml(), EmailStatusType.CREATED.toString());
        try {
            Thread importThread = new Thread() {
                @Transactional
                @Override
                public void run() {
                    try {
                        postEmail(emailModel.getSubject(), emailModel.getMessageHtml(), emailModel.getAttachment(), emailModel.getAttachmentName());
                        emailDao.updateEmailStatus(emailId, EmailStatusType.SUCCESS.toString());
                    } catch (Exception e) {
                        emailDao.updateEmailStatus(emailId, EmailStatusType.FAILED.toString());
                    }
                }
            };
            importThread.start();
        } catch (Exception e) {
            e.printStackTrace();
            emailDao.updateEmailStatus(emailId, EmailStatusType.FAILED.toString());
            return Result.result(MessageInfo.SYSTEM_SEND_EMAIL_ERRO, "Please check the attachment address or input box content.");
        }
        return Result.result(null);
    }

    private void postEmail(String subject, String messageHtml, String attachment, String attachmentName) throws Exception {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);

        // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
        //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
        //     打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
        /*
        // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
        //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
        //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        */

        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log

        // 3. 创建一封邮件
        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount, subject, messageHtml,
                attachment, attachmentName);

        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();

        // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        //
        //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
        //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
        //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
        //
        //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
        //           (1) 邮箱没有开启 SMTP 服务;
        //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
        //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
        //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
        //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
        //
        //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
        transport.connect(myEmailAccount, myEmailPassword);

        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

        // 7. 关闭连接
        transport.close();
    }

    /**
     * 创建一封带附件的邮件
     *
     * @param session     和服务器交互的会话
     * @param sendMail    发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    private static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail, String subject,
                                                 String messageHtml, String attachment, String attachmentName) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, myEmailName, "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "Confidential", "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject(subject, "UTF-8");

        // 5. Content: 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
        Multipart multipart = new MimeMultipart();

        // 6. 添加邮件正文
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(messageHtml + mailSuffix, "text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);

        // 7. 添加附件
        if (StringUtil.isNotEmptyOrBlank(attachment)) {
            String path = FileUtil.randomTempFilePath(null);
            FileUtil.makeDir(path);
            String filePath = path + File.separator;
            String fileName = UUID.randomUUID().toString() + FileUtil.getSuffix(attachmentName);
            FileInputStream inputStream = FileUtil.downLoadFromUrl(attachment, fileName, filePath);
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            int ch;
            while ((ch = inputStream.read()) != -1) {
                swapStream.write(ch);
            }
            inputStream.close();
            BodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filePath + fileName);
            attachmentBodyPart.setDataHandler(new DataHandler(source));

            // 8. MimeUtility.encodeWord可以避免文件名乱码
            attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachmentName));
            multipart.addBodyPart(attachmentBodyPart);
        }

        // 9. 将multipart对象放到message中
        message.setContent(multipart);

        // 10. 设置发件时间
        message.setSentDate(new Date());

        // 11. 保存设置
        message.saveChanges();

        return message;
    }
}
