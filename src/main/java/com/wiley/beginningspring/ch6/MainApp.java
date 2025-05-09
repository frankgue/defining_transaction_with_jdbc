package com.wiley.beginningspring.ch6;

import com.wiley.beginningspring.ch6.config.Ch6Configuration;
import com.wiley.beginningspring.ch6.dao.AccountDao;
import com.wiley.beginningspring.ch6.model.Account;
import com.wiley.beginningspring.ch6.service.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
//        DatabaseInitializer.init();

        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(Ch6Configuration.class);

        AccountService accountService = applicationContext.getBean(AccountService.class);
        AccountDao accountDao = applicationContext.getBean(AccountDao.class);
        Account sourceAccount = accountDao.find(100L);
        Account targetAccount = accountDao.find(101L);
        System.out.println("Sender Owner Name : " + sourceAccount.getOwnerName() + " Old Balance : " + sourceAccount.getBalance());
        System.out.println("Beneficiary Owner Name : " + targetAccount.getOwnerName() + " Old Balance : " + targetAccount.getBalance());
        accountService.transferMoney(100L, 101L, 5.0);


        sourceAccount = accountDao.find(100L);
        targetAccount = accountDao.find(101L);
        System.out.println("Sender Owner Name : " + sourceAccount.getOwnerName() + " New  Balance : " + sourceAccount.getBalance());
        System.out.println("Beneficiary Owner Name : " + targetAccount.getOwnerName() + " New Balance : " + targetAccount.getBalance());


        accountService.depostMoney(targetAccount.getId(), 200);
        targetAccount = accountDao.find(targetAccount.getId());
        System.out.println("Beneficiary Owner Name : " + targetAccount.getOwnerName() + " DEPOSIT New Balance : " + targetAccount.getBalance());

    }
}
