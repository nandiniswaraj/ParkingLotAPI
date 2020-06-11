package com.bridgelabz.parkinglot.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bridgelabz.parkinglot.model.Owner;
import com.bridgelabz.parkinglot.enumtype.EnumType;
import com.bridgelabz.parkinglot.response.Response;

@Service
public class EmailValidation {

    @Autowired
    private JwtToken tokenGenerator;

    private JavaMailSender mailSender;

    @Autowired
    public EmailValidation(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public Response sendMail(Owner owner, EnumType type) throws MailException {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(owner.getEmailId());
        mailMessage.setFrom("nandiniswaraj95@gmail.com");
        if (type.equals(EnumType.REGISTRATION)) {
            mailMessage.setSubject("thanks  for registering with us");
            mailMessage.setText("click on it to verify mail " + "http://localhost:8080/parkinglotStstem/verifyemail/" + tokenGenerator.generateToken(owner.getEmailId()));
            mailSender.send(mailMessage);
            return new Response("verify your email for register", 200);
        } else {
            mailMessage.setSubject("Hello : " + owner.getFirstName() + " " + owner.getLastName());
            mailMessage.setText("please click on the link to Reset your Password : " + "http://localhost:8080/parkinglotStstem/reset/" + tokenGenerator.generateToken(owner.getEmailId()));
            mailSender.send(mailMessage);
            return new Response("check password reset link in mail and update your password", 200);
        }
    }
}
