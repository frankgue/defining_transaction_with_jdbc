package com.wiley.beginningspring.ch6.service;

import com.wiley.beginningspring.ch6.dao.AccountDao;
import com.wiley.beginningspring.ch6.model.Account;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Transactional
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;
    private TransactionTemplate transactionTemplate;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public void transferMoney(Long sourceAccountId, Long targetAccountId, double amount) {
       transactionTemplate.execute(new TransactionCallbackWithoutResult() {
           @Override
           protected void doInTransactionWithoutResult(TransactionStatus status) {
               Account sourceAccount = accountDao.find(sourceAccountId);
               Account targetAccount = accountDao.find(targetAccountId);

               sourceAccount.setBalance(sourceAccount.getBalance() - amount);
               targetAccount.setBalance(targetAccount.getBalance() + amount);

               accountDao.update(sourceAccount);
               accountDao.update(targetAccount);
           }
       });
    }

    @Override
    public void depostMoney(Long accountId, double amount) {

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {

                Account account = accountDao.find(accountId);
                if (account == null) throw new IllegalArgumentException("Account not found");
                account.setBalance(account.getBalance() + amount);
                accountDao.update(account);
            }
        });


    }


}
