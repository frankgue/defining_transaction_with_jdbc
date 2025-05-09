package com.wiley.beginningspring.ch6;

import com.wiley.beginningspring.ch6.config.Ch6Configuration;
import com.wiley.beginningspring.ch6.service.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
//        DatabaseInitializer.init();

        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(Ch6Configuration.class);

        AccountService accountService = applicationContext.getBean(AccountService.class);
        accountService.transferMoney(100L, 101L, 5.0);

    }
}
