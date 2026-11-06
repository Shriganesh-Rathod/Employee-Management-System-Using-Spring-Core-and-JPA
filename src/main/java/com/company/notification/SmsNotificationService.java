package com.company.notification;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//To make this bean lazy, we can use @Lazy annotation. This means that the bean will not be created until it is needed.
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SmsNotificationService implements NotificationService {

    @Override
    public void sendNotification(String message){
        System.out.println("Sending SMS Notification");
        System.out.println("SMS Notification: "+message);
    }

}
