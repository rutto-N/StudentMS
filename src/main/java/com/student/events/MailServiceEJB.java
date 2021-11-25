package com.student.events;


import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class MailServiceEJB {
    @Resource(mappedName="java:jboss/mail/Default")
    private Session session;

    @Asynchronous
    public void sendMail(@Observes Email email){
        try{
            MimeMessage mime=new MimeMessage(session);
            Address[] to=new InternetAddress[]{
                    new InternetAddress(email.getAddress())
            };
            mime.setRecipients(Message.RecipientType.TO,to);
            mime.setSubject(email.getSubject());
            mime.setContent(email.getMessage(),"text/plain");
            Transport.send(mime);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
