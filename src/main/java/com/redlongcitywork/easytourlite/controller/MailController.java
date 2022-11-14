package com.redlongcitywork.easytourlite.controller;

import com.redlongcitywork.easytourlite.javamail.core.Email;
import com.redlongcitywork.easytourlite.javamail.core.EmailConfiguration;
import com.redlongcitywork.easytourlite.javamail.core.EmailContentConverter;
import com.redlongcitywork.easytourlite.javamail.core.EmailSender;
import com.redlongcitywork.easytourlite.javamail.core.SimpleEmail;
import com.redlongcitywork.easytourlite.javamail.core.SimpleEmailSender;
import com.redlongcitywork.easytourlite.model.Order;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author redlongcity 
 * controller for manipulations with mail resources
 */
@RestController
@RequestMapping("/json")
public class MailController {

    private static final Logger LOG = Logger.getLogger(MailController.class.getName());

    @Autowired
    EmailContentConverter converter;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity<Void> send(@RequestBody Order order) {

        String from = "HotToursUkraine@gmail.com";
        String to = converter.getAddresses();
        String subject = "Order";
        
        converter.getMessage(order, new EmailContentConverter.GetMessageCallBack() {
            @Override
            public void onDataReceived(String message) {
                Email email = new SimpleEmail(from, to, subject, message);
                EmailSender sender = new SimpleEmailSender(new EmailConfiguration());

                try {
                    sender.send(email);
                    LOG.log(Level.INFO, "Sent message succesfully!");
                } catch (MessagingException e) {
                    LOG.log(Level.INFO, "Sending message was failed! " + e.getMessage());
                }
            }

            @Override
            public void onDataNotAwailable() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
