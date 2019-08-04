DROP TABLE IF EXISTS users_car;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS car;
CREATE TABLE users (
    id_user BIGSERIAL PRIMARY KEY NOT NULL,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    birthday TIMESTAMP,
    photo varchar(255) UNIQUE
);
CREATE TABLE car (
    id_car BIGSERIAL PRIMARY KEY NOT NULL,
    brand VARCHAR(64) NOT NULL,
    model VARCHAR(64) NOT NULL,
    color VARCHAR(24) NOT NULL,
    horsepower INTEGER NOT NULL,
    price BIGINT NOT NULL,
    mileage BIGINT NOT NULL,
    picture VARCHAR(255) UNIQUE
);
CREATE TABLE users_car (
   id_user BIGINT NOT NULL,
   id_car BIGINT NOT NULL,
   FOREIGN KEY (id_user) REFERENCES users(id_user),
   FOREIGN KEY (id_car) REFERENCES car(id_car),
   PRIMARY KEY (id_user, id_car)
);