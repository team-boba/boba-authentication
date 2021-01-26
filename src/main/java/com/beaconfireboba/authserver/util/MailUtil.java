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
    @Autowired
    private MailConfig mailConfig;
    private String from = mailConfig.getFrom();
    static HashMap<String, String[]> mail = new HashMap<>();

    static{
        String[] receipt = {"Action required", "Please upload your OPT receipt"};
        String[] i983 = {"Action required", "Please upload your I-983 form"};
        String[] i20 = {"Action required", "Please upload your I-20"};
        String[] EAD = {"Action required", "Please upload your OPT EAD card"};
        mail.put("receipt", receipt);
        mail.put("i983", i983);
        mail.put("i20", i20);
        mail.put("EAD", EAD);
    }

    public void sendGeneralMail(String to, String subject, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        try{
            javaMailSender.send(mailMessage);
            System.out.println("Mail sent.");
        }catch (Exception e){
            System.out.println("Mail failed to send.");
        }
    }

    public void sendOptionMail (String to, String option){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String[] message = mail.get(option);
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(message[0]);
        mailMessage.setText(message[1]);
        try{
            javaMailSender.send(mailMessage);
            System.out.println("Mail sent.");
        }catch (Exception e){
            System.out.println("Mail failed to send.");
        }
    }


}
