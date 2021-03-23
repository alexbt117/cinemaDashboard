DROP TABLE cinema CASCADE CONSTRAINTS;

DROP TABLE customer CASCADE CONSTRAINTS;

DROP TABLE movie CASCADE CONSTRAINTS;

DROP TABLE reservation CASCADE CONSTRAINTS;

DROP TABLE room CASCADE CONSTRAINTS;

DROP TABLE viewing CASCADE CONSTRAINTS;

CREATE TABLE cinema (
    id           INTEGER NOT NULL,
    cinemaname   VARCHAR(40)
);

ALTER TABLE cinema ADD CONSTRAINT cinema_pk PRIMARY KEY ( id );

CREATE TABLE customer (
    id            INTEGER NOT NULL,
    name          VARCHAR(40),
    surname       VARCHAR(40),
    email         VARCHAR(40),
    mobilephone   VARCHAR(10),
    numtickets    INTEGER
);

ALTER TABLE customer ADD CONSTRAINT customer_pk PRIMARY KEY ( id );

CREATE TABLE movie (
    id            INTEGER NOT NULL,
    title         VARCHAR(50),
    description   VARCHAR(1000),
    director      VARCHAR(40),
    genre         VARCHAR(20),
    actors        VARCHAR(10),
    duration      INTEGER
);

ALTER TABLE movie ADD check ( duration BETWEEN 00:15 AND 03 :55 );

ALTER TABLE movie ADD CONSTRAINT movie_pk PRIMARY KEY ( id );

CREATE TABLE reservation (
    id            INTEGER NOT NULL,
    idcustomer    INTEGER,
    idmovie       INTEGER,
    status        VARCHAR(10),
    viewing_id    INTEGER,
    customer_id   INTEGER NOT NULL
);

ALTER TABLE reservation ADD CONSTRAINT reservation_pk PRIMARY KEY ( id );

CREATE TABLE room (
    roomname     VARCHAR(10) NOT NULL,
    numofseats   INTEGER,
    ofcinema     VARCHAR(40),
    cinema_id    INTEGER NOT NULL
);

ALTER TABLE room ADD CONSTRAINT room_pk PRIMARY KEY ( roomname );

CREATE TABLE viewing (
    id              INTEGER NOT NULL,
    movieviewing    VARCHAR(50),
    roomviewing     VARCHAR(10),
    timeviewing     DATETIME,
    room_roomname   VARCHAR(10),
    movie_id        INTEGER
);

ALTER TABLE viewing ADD CONSTRAINT viewing_pk PRIMARY KEY ( id );

ALTER TABLE reservation
    ADD CONSTRAINT reservation_customer_fk FOREIGN KEY ( customer_id )
        REFERENCES customer ( id );

ALTER TABLE reservation
    ADD CONSTRAINT reservation_viewing_fk FOREIGN KEY ( viewing_id )
        REFERENCES viewing ( id );

ALTER TABLE room
    ADD CONSTRAINT room_cinema_fk FOREIGN KEY ( cinema_id )
        REFERENCES cinema ( id );

ALTER TABLE viewing
    ADD CONSTRAINT viewing_movie_fk FOREIGN KEY ( movie_id )
        REFERENCES movie ( id );

ALTER TABLE viewing
    ADD CONSTRAINT viewing_room_fk FOREIGN KEY ( room_roomname )
        REFERENCES room ( roomname );

CREATE SEQUENCE cinema_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER cinema_id_trg BEFORE
    INSERT ON cinema
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := nextval('cinema_id_seq');
END;
/

CREATE SEQUENCE customer_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER customer_id_trg BEFORE
    INSERT ON customer
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := nextval('customer_id_seq');
END;
/

CREATE SEQUENCE movie_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER movie_id_trg BEFORE
    INSERT ON movie
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := nextval('movie_id_seq');
END;
/

CREATE SEQUENCE reservation_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER reservation_id_trg BEFORE
    INSERT ON reservation
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := nextval('reservation_id_seq');
END;
/

CREATE SEQUENCE viewing_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER viewing_id_trg BEFORE
    INSERT ON viewing
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := nextval('viewing_id_seq');
END;
/



-- Oracle SQL Developer Data Modeler Summary Report:
--
-- CREATE TABLE                             6
-- CREATE INDEX                             0
-- ALTER TABLE                             12
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           5
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          5
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
--
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
--
-- REDACTION POLICY                         0
-- TSDP POLICY                              0
--
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
--
-- ERRORS                                   0
-- WARNINGS                                 0
