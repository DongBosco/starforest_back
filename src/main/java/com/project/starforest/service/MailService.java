package com.project.starforest.service;

import jakarta.transaction.Transactional;
import org.springframework.mail.SimpleMailMessage;

@Transactional
public interface MailService {
    void sendEmail(String toEmail,
                          String title,
                          String text);

     SimpleMailMessage createEmailForm(String toEmail,
                                              String title,
                                              String text);
}
