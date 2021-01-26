package com.beaconfireboba.authserver.util;

import com.beaconfireboba.authserver.config.MailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class MailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    private MailConfig mailConfig;

    @Autowired
    public void setMailConfig(MailConfig mailConfig) {
        this.mailConfig = mailConfig;
    }

    private static final HashMap<String, String[]> mail = new HashMap<>() {{
        put("receipt", new String[] {"Action required", "Please upload your OPT receipt"});
        put("i983", new String[] {"Action required", "Please upload your I-983 form"});
        put("i20", new String[] {"Action required", "Please upload your I-20"});
        put("EAD", new String[] {"Action required", "Please upload your OPT EAD card"});
    }};

    public void sendGeneralMail(String to, String subject, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailConfig.getFrom());
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        try{
            javaMailSender.send(mailMessage);
        }catch (Exception e){
            System.out.println("Mail failed to send.");
        }
    }

    public void sendOptionMail (String to, String option){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String[] message = mail.get(option);
        mailMessage.setFrom(mailConfig.getFrom());
        mailMessage.setTo(to);
        mailMessage.setSubject(message[0]);
        mailMessage.setText(message[1]);
        try{
            javaMailSender.send(mailMessage);
        }catch (Exception e){
            System.out.println("Mail failed to send.");
        }
    }


}
