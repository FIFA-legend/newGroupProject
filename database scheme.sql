CREATE DATABASE project_park;

USE project_park;

CREATE TABLE cars
(
    id BIGINT AUTO_INCREMENT,
    number VARCHAR(50) NOT NULL,
    price double NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE regions
(
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE users
(
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    birth DATE NOT NULL,
    passport_id VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE regions_cars
(
    region_id BIGINT NOT NULL,
    car_id BIGINT NOT NULL,
    PRIMARY KEY (region_id, car_id),
    CONSTRAINT FKf9bijwbeaxntq40nsoeygghxq
	FOREIGN KEY (car_id) REFERENCES cars (id),
    CONSTRAINT FKn2twapcub0ohxhvek8yxul0vl
    FOREIGN KEY (region_id) REFERENCES regions (id)
);

CREATE TABLE requests
(
    id BIGINT AUTO_INCREMENT,
    car_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK500scapc53jpwp0dqr2hbbcan
    FOREIGN KEY (car_id) REFERENCES cars (id),
    CONSTRAINT FK8usbpx9csc6opbjg1d7kvtf8c
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE buses
(
	id BIGINT NOT NULL,
    capacity INT NOT NULL,
	PRIMARY KEY(id),
    CONSTRAINT FKdd2jtbmdjv8ml664vn6kpv7jl
    FOREIGN KEY (id) REFERENCES cars (id)
);

CREATE TABLE taxis
(
	id BIGINT NOT NULL,
    comfort VARCHAR(50) NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT FKq4d4tn221mjpx9ms4txu16vo7
    FOREIGN KEY (id) REFERENCES cars (id)
);

CREATE TABLE trucks
(
	id BIGINT NOT NULL,
    carrying INT NOT NULL,
	PRIMARY KEY(id),
    CONSTRAINT FKd3w0me9aiclfh9leutlxr1icq
    FOREIGN KEY (id) REFERENCES cars (id)
);