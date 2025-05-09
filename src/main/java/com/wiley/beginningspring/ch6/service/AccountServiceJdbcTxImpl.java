package com.wiley.beginningspring.ch6.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountServiceJdbcTxImpl implements AccountService {

    private final String jdbcUrl = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1"; // à adapter selon ta DB
    private final String jdbcUser = "sa";
    private final String jdbcPassword = "";

    @Override
    public void transferMoney(Long sourceAccountId, Long targetAccountId, double amount) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            connection.setAutoCommit(false);

            // Débit du compte source
            try (PreparedStatement ps1 = connection.prepareStatement(
                    "UPDATE account SET balance = balance - ? WHERE ID = ?"
            )) {
                ps1.setDouble(1, amount);
                ps1.setLong(2, sourceAccountId);
                ps1.executeUpdate();
            }

            // Crédit du compte cible
            try (PreparedStatement ps2 = connection.prepareStatement(
                    "UPDATE account SET balance = balance + ? WHERE ID = ?"
            )) {
                ps2.setDouble(1, amount);
                ps2.setLong(2, targetAccountId);
                ps2.executeUpdate();
            }


            connection.commit(); // tout s’est bien passé, on commit
            System.out.println("Transfert effectué avec succès.");

        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback(); // en cas d’erreur, rollback
                    System.out.println("Erreur rencontrée. Transaction annulée.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void depostMoney(Long accountId, double amount) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement(
                    "UPDATE  account SET balance = balance + ? WHERE id = ?"
            )) {
                ps.setDouble(1, amount);
                ps.setLong(2, accountId);
                ps.executeUpdate();
            }

            connection.commit(); // tout s’est bien passé, on commit
            System.out.println("Depot effectué avec succès.");

        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback(); // en cas d’erreur, rollback
                    System.out.println("Erreur rencontrée. Transaction annulée.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
