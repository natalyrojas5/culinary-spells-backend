package com.hechizos.culinarios.Services.EmailService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.hechizos.culinarios.Dto.EmailDto.EmailValuesDTO;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    @Value("${mail.urlFront}")
    private String urlFront;

    public void sendEmail(EmailValuesDTO dto) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Context context = new Context();
            Map<String, Object> model = new HashMap<>();
            model.put("userName", dto.getUserName());
            model.put("url", urlFront + dto.getTokenPassword());
            context.setVariables(model);
            String htmlText = templateEngine.process("email-template", context);
            helper.setFrom(dto.getMailFrom());
            helper.setTo(dto.getMailTo());
            helper.setSubject(dto.getSubject());
            helper.setText(htmlText, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
