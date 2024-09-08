SET FOREIGN_KEY_CHECKS=0; DROP TABLE IF EXISTS roles CASCADE; SET FOREIGN_KEY_CHECKS=1;
CREATE TABLE roles
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

SET FOREIGN_KEY_CHECKS=0; DROP TABLE IF EXISTS users CASCADE; SET FOREIGN_KEY_CHECKS=1;
CREATE TABLE users
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    age INT(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO users (name, last_name, age, password)
VALUES ('user', 'user', 1, 'user'); -- password: user

INSERT INTO users (name, last_name, age, password)
VALUES ('admin', 'admin', 2, 'admin'); -- password: admin

SET FOREIGN_KEY_CHECKS=0; DROP TABLE IF EXISTS users_roles; SET FOREIGN_KEY_CHECKS=1;
CREATE TABLE users_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

INSERT INTO users_roles (user_id, role_id)
VALUES ((SELECT id FROM users WHERE name = 'user'),
        (SELECT id FROM roles WHERE name = 'ROLE_USER'));

INSERT INTO users_roles (user_id, role_id)
VALUES ((SELECT id FROM users WHERE name = 'admin'),
        (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));

INSERT INTO users_roles (user_id, role_id)
VALUES ((SELECT id FROM users WHERE name = 'admin'),
        (SELECT id FROM roles WHERE name = 'ROLE_USER'));