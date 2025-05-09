package com.wiley.beginningspring.ch6;

import com.wiley.beginningspring.ch6.config.Ch6Configuration;
import com.wiley.beginningspring.ch6.service.AccountService;
import com.wiley.beginningspring.ch6.service.AccountServiceJdbcTxImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

public class MainApp {
    public static void main(String[] args) {
//        DatabaseInitializer.init();

        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(Ch6Configuration.class);
        PlatformTransactionManager transactionManager =
                applicationContext.getBean(PlatformTransactionManager.class);
        System.out.println(transactionManager != null);

/*        AccountService accountService = new AccountServiceJdbcTxImpl();
        accountService.transferMoney(100L, 101L, 5.0);*/
    }
}
