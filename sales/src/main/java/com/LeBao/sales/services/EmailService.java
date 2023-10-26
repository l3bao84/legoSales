package com.LeBao.sales.services;

import com.LeBao.sales.config.EmailSenderConfig;
import com.LeBao.sales.entities.Order;
import freemarker.core.ParseException;
import freemarker.template.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private Configuration configuration;

    public void sendEmail(Order order) {

        Map<String, Object> model = new HashMap<>();
        model.put("orderDetails", order.getOrderDetails());
        MimeMessage mimeMessage = sender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Template template = configuration.getTemplate("email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            helper.setTo(order.getUser().getEmail());
            helper.setText(html, true);
            helper.setSubject("Order Confirmation and Invoice");
            helper.setFrom("leducbao12a1cmb1920@gmail.com");
            sender.send(mimeMessage);

        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
