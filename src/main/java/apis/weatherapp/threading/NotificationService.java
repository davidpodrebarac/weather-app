package apis.weatherapp.threading;

import apis.weatherapp.metaweather.rest.controllers.WeatherInfoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class NotificationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherInfoController.class);

//    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendThreadNotification(String to, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(this.from);
        mailMessage.setTo(to);
        mailMessage.setSubject("Metaweather app thread notification");
        mailMessage.setText("Message: " + message);
        javaMailSender.send(mailMessage);
        LOGGER.info("Message " + message + " sent to " + to + " successfully.");
    }
}
