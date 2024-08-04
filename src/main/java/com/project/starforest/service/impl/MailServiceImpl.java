package com.project.starforest.service.impl;


import com.project.starforest.service.MailService;
import com.project.starforest.util.BusinessLogicException;
import com.project.starforest.util.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class MailServiceImpl implements MailService {

    private final JavaMailSender emailSender;

    public MailServiceImpl(JavaMailSender emailSender){

        this.emailSender = emailSender;
    }

    public void sendEmail(String toEmail,
                          String title,
                          String text) {
        SimpleMailMessage emailForm = createEmailForm(toEmail, title, text);
        System.out.println("emailForm == "+emailForm);
        try {
            emailSender.send(emailForm);
        } catch (MailException e) {
            log.error("Failed to send email to: {}, error: {}", toEmail, e.getMessage());
            throw new BusinessLogicException(ExceptionCode.UNABLE_TO_SEND_EMAIL, "Failed to send email", e);
        }
    }

    public SimpleMailMessage createEmailForm(String toEmail,
                                             String title,
                                             String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(title);
        message.setText(text);

        return message;
    }
}