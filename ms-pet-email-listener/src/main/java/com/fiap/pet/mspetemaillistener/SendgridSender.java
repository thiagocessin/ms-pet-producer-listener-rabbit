package com.fiap.pet.mspetemaillistener;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SendgridSender {

    public SendgridSender(String emailFrom, String emailTo, String content, String subject) {
        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
        this.content = content;
        this.subject = subject;
    }

    private String emailFrom;
    private String emailTo;

    public SendgridSender() {

    }

    public SendgridSender(String emailFrom, String emailTo, String content, String subject, String sengridKey) {
        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
        this.content = content;
        this.subject = subject;
        this.sengridKey = sengridKey;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    private String content;
    private String subject;

    @Value("${SENDGRID_API_KEY}")
    private String sengridKey;

    public void sendEmail() throws IOException {
        Mail m = new Mail(new Email(this.emailFrom), this.subject, new Email(this.emailTo), new Content("text/plain", this.content));
        SendGrid s = new SendGrid(this.sengridKey);

        Request r = new Request();

        try {
            r.setMethod(Method.POST);
            r.setEndpoint("mail/send");
            r.setBody(m.build());
        } catch (IOException e) {
            throw e;
        }
    }

}
