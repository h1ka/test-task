create table CAR
(
    id            IDENTITY primary key,
    brand         VARCHAR2(150),
    model         VARCHAR2(200),
    power         DOUBLE,
    year_of_issue YEAR,
);

create table AIRPLANE
(
    id            IDENTITY primary key,
    brand         VARCHAR2(150),
    model         VARCHAR2(200),
    manufacturer  VARCHAR2(500),
    year_of_issue YEAR,
    fuel_capacity  INT,
    seats         INT
);

CREATE TABLE MARK
(
    ID             IDENTITY primary key,
    DATE           TIMESTAMP,
    ASSESSED_VALUE DECIMAL(19, 2)
);

CREATE TABLE CAR_MARKS
(
    CAR_ID   BIGINT NOT NULL,
    MARKS_ID BIGINT NOT NULL
);

CREATE TABLE AIRPLANE_MARKS
(
    AIRPLANE_ID BIGINT NOT NULL,
    MARKS_ID    BIGINT NOT NULL
)