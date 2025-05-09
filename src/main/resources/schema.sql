CREATE TABLE ACCOUNT (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         owner_name VARCHAR(100),
                         balance DOUBLE,
                         access_time TIMESTAMP,
                         locked BOOLEAN
);

INSERT INTO ACCOUNT(ID, OWNER_NAME, BALANCE, ACCESS_TIME, LOCKED)
SELECT 100, 'owner-1', 10.0, CURRENT_TIMESTAMP, FALSE
    WHERE NOT EXISTS (SELECT * FROM ACCOUNT WHERE ID = 100);

INSERT INTO ACCOUNT(ID, OWNER_NAME, BALANCE, ACCESS_TIME, LOCKED)
SELECT 101, 'owner-2', 0.0, CURRENT_TIMESTAMP, FALSE
    WHERE NOT EXISTS (SELECT * FROM ACCOUNT WHERE ID = 101);