package com.redlongcitywork.easytourlite.javamail.core;

import javax.mail.MessagingException;

public interface EmailSender {

    void send(Email email) throws MessagingException;

}
