package com.keepitfresh.model;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {

    @Mock
    private MailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    @Captor
    private ArgumentCaptor<SimpleMailMessage> mailMessage;

    private String emailRecipient = "test";
    private String userName = "user";
    private String itemName = "item";
    private Integer itemQuantity = 1;
    private int daysBeforeExpiration = 2;

    @Test
    public void sendEmailExpired() throws Exception {
        emailService.sendEmailExpired(
                emailRecipient, userName, itemName, itemQuantity);
        verify(mailSender).send(mailMessage.capture());
        assertEquals(emailRecipient, mailMessage.getValue().getTo()[0]);
    }

    @Test
    public void sendEmailPreExpired() throws Exception {
        emailService.sendEmailPreExpired(
                emailRecipient, userName, itemName, itemQuantity, new Date(), daysBeforeExpiration);
        verify(mailSender).send(mailMessage.capture());
        assertEquals(emailRecipient, mailMessage.getValue().getTo()[0]);
    }

}