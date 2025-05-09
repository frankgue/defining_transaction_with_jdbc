package com.wiley.beginningspring.ch6.service;

import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountServiceJdbcTxImplWithSpring implements AccountService {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    @Transactional
    public void transferMoney(Long sourceAccountId, Long targetAccountId, double amount) {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE account SET balance = balance - " + amount + " WHERE id = " + sourceAccountId);
            statement.executeUpdate("UPDATE  account SET balance = balance + " + amount + " WHERE id = " + targetAccountId);

            System.out.println("Transfert effectué avec succès.");

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    @Override
    @Transactional
    public void depostMoney(Long accountId, double amount) {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE  account SET balance = balance + " + amount + " WHERE id = " + accountId);

            System.out.println("Depot effectué avec succès.");
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }
}
