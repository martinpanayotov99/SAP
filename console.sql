DROP DATABASE IF EXISTS sap_test;
CREATE DATABASE sap_test;
USE sap_test;

CREATE TABLE users(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(30) NOT NULL,
    name VARCHAR(50) NOT NULL,
    password VARCHAR(16) NOT NULL,
    phone VARCHAR(10) NOT NULL,
    isAdmin BOOLEAN NOT NULL DEFAULT 0
);

INSERT INTO users(name, email, password, phone, isAdmin)
VALUES ('Reuben Marquez', 'reuben@gmail.com', '12345678', '0899567890', 0),
       ('Sakina Mclean', 's@gmail.com', '12345678', '0883456789', 0),
       ('Antoinette Irvine', 'a@gmail.com', '12345678', '0899567890', 0),
       ('Maisy Nolan', 'm@gmail.com', '12345678', '0883456789', 0),
       ('Hugo Alfaro', 'h@gmail.com', '12345678', '0899567890', 0),
       ('Carley Patterson', 'c@gmail.com', '12345678', '0883456789', 0),
       ('Shahzaib Rollins', 's@gmail.com', '12345678', '0899567890', 1),
       ('Rishi Caldwell', 'r@gmail.com', '12345678', '0883456789', 1),
       ('Frederic Richmond', 'f@gmail.com', '12345678', '0899567890', 1);

CREATE TABLE services(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  plan_name VARCHAR(30) NOT NULL,
  SMS INT NOT NULL,
  minutes INT NOT NULL,
  Mb INT NOT NULL
);

INSERT INTO services(plan_name, sms, minutes, mb)
VALUES ('STANDARD',500,500,500),
       ('MEGA',1000,1000,1000),
       ('GIGA',10000,10000,10000);


CREATE TABLE dataPlans(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES users(id),
    plan_id INT NOT NULL,
    CONSTRAINT FOREIGN KEY (plan_id) REFERENCES services(id)
);

INSERT INTO dataPlans (user_id, plan_id)
VALUES (1,1),
       (2,2),
       (3,3),
       (4,3),
       (5,2),
       (6,1),
       (7,1),
       (8,2),
       (9,3);

CREATE TABLE payment(
    id INT PRIMARY KEY AUTO_INCREMENT,
    dataPlan_id INT NOT NULL,
    CONSTRAINT FOREIGN KEY (dataPlan_id) REFERENCES dataPlans(id),
    lastPayment DATE NOT NULL,
    nextPayment DATE NOT NULL,
    Mb_remaining INT NOT NULL,
    minutes_remaining INT NOT NULL,
    SMS_remaining INT NOT NULL,
    Mb_active BOOLEAN DEFAULT 1,
    minutes_active BOOLEAN DEFAULT 1,
    SMS_active BOOLEAN DEFAULT 1,
    paid BOOLEAN DEFAULT 1
);

INSERT INTO payment (dataPlan_id,lastPayment, nextPayment, Mb_remaining, minutes_remaining, SMS_remaining)
VALUES (1, CURDATE(), DATE_ADD(CURDATE(), INTERVAL +1 MONTH),1000,1000,1000),
       (2, CURDATE(), DATE_ADD(CURDATE(), INTERVAL +1 MONTH),1000,1000,1000),
       (3, CURDATE(), DATE_ADD(CURDATE(), INTERVAL +1 MONTH),1000,1000,1000),
       (4, CURDATE(), DATE_ADD(CURDATE(), INTERVAL +1 MONTH),1000,1000,1000),
       (5, CURDATE(), DATE_ADD(CURDATE(), INTERVAL +1 MONTH),1000,1000,1000),
       (6, CURDATE(), DATE_ADD(CURDATE(), INTERVAL +1 MONTH),1000,1000,1000),
       (7, CURDATE(), DATE_ADD(CURDATE(), INTERVAL +1 MONTH),1000,1000,1000),
       (8, CURDATE(), DATE_ADD(CURDATE(), INTERVAL +1 MONTH),1000,1000,1000),
       (9, CURDATE(), DATE_ADD(CURDATE(), INTERVAL +1 MONTH),1000,1000,1000);


