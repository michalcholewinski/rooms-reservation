CREATE SEQUENCE MYBOOKING_USER_ID_SEQ
  START 2
  INCREMENT BY 1;

CREATE TABLE MYBOOKING_USER (
  ID         BIGINT PRIMARY KEY       NOT NULL,
  FIRST_NAME VARCHAR(50)              NOT NULL,
  LAST_NAME  VARCHAR(50),
  EMAIL      VARCHAR(100) UNIQUE      NOT NULL
);


CREATE SEQUENCE MYBOOKING_ROLE_ID_SEQ
  START 3
  INCREMENT BY 1;

CREATE TABLE MYBOOKING_ROLE (
  ID   BIGINT PRIMARY KEY     NOT NULL,
  NAME VARCHAR(50) UNIQUE     NOT NULL
);
CREATE TABLE MYBOOKING_USERS_ROLES (
  USER_ID BIGINT REFERENCES MYBOOKING_USER (ID),
  ROLE_ID BIGINT REFERENCES MYBOOKING_ROLE (ID)
);

CREATE SEQUENCE MYBOOKING_ROOM_ID_SEQ
  START 1
  INCREMENT BY 1;
CREATE TABLE MYBOOKING_ROOM (
  ID         BIGINT PRIMARY KEY NOT NULL,
  NUMBER     INTEGER            NOT NULL,
  DETAILS    VARCHAR(500),
  HOTEL_NAME VARCHAR(100)       NOT NULL,
  CITY       VARCHAR(100)       NOT NULL,
  CONSTRAINT phrase_unique_number_and_city_and_hotel_name UNIQUE (NUMBER, HOTEL_NAME, CITY)

);


CREATE SEQUENCE MYBOOKING_RESERVATION_ID_SEQ
  START 1
  INCREMENT BY 1;
CREATE TABLE MYBOOKING_RESERVATION (
  ID        BIGINT PRIMARY KEY       NOT NULL,
  FROM_TIME TIMESTAMP WITH TIME ZONE NOT NULL,
  TO_TIME   TIMESTAMP WITH TIME ZONE NOT NULL,
  ROOM_ID   BIGINT REFERENCES MYBOOKING_ROOM (ID),
  CUSTOMER_ID   BIGINT REFERENCES MYBOOKING_USER (ID)
);

