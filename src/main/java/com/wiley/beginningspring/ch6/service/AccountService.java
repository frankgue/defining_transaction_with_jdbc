package com.wiley.beginningspring.ch6.service;

public interface AccountService {
    void transferMoney(Long sourceAccountId, Long targetAccountId, double amount);
}
