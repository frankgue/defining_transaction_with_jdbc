package com.wiley.beginningspring.ch6.dao;


import com.wiley.beginningspring.ch6.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountDao {
    Account find(long accountId);
    Optional<Account> findByOwnerAndLocked(String ownerName, boolean locked);
    List<Account> find(List<Long> accountId);
    List<Account> find(boolean locked);
    Account find(String ownerName);
    void save(Account account);
    void update(Account account);
    void delete(long accountId);
}
