CREATE TABLE INKOMST_CATEGORIE(
    ID                    INT8  NOT NULL,
    OMSCHRIJVING          VARCHAR(256) NOT NULL,
    FK_PARENT             INT8,
    VERSION               INT8 NOT NULL,
    CONSTRAINT INKOMST_CATEGORIE_PK PRIMARY KEY (ID),
    CONSTRAINT INKOMST_PARENT_CATEGORIE_FK FOREIGN KEY(FK_PARENT) REFERENCES INKOMST_CATEGORIE
);

CREATE TABLE UITGAVE_CATEGORIE(
    ID                    INT8  NOT NULL,
    OMSCHRIJVING          VARCHAR(256) NOT NULL,
    FK_PARENT             INT8,
    VERSION               INT8 NOT NULL,
    CONSTRAINT UITGAVE_CATEGORIE_PK PRIMARY KEY (ID),
    CONSTRAINT UITGAVE_PARENT_CATEGORIE_FK FOREIGN KEY(FK_PARENT) REFERENCES UITGAVE_CATEGORIE
);

CREATE TABLE INKOMST(
    ID                       INT8  NOT NULL,
    BEDRAG                   NUMERIC(8,2) NOT NULL,
    DATUM                    TIMESTAMP NOT NULL,
    FK_INKOMST_CATEGORIE     INT8  NOT NULL,
    VERSION                  INT8 NOT NULL,
    CONSTRAINT INKOMST_PK PRIMARY KEY (ID),
    CONSTRAINT INKOMST_CATEGORIE_FK FOREIGN KEY(FK_INKOMST_CATEGORIE) REFERENCES INKOMST_CATEGORIE
);

CREATE TABLE UITGAVE(
    ID                       INT8  NOT NULL,
    BEDRAG                   NUMERIC(8,2) NOT NULL,
    DATUM                    TIMESTAMP NOT NULL,
    FK_UITGAVE_CATEGORIE     INT8  NOT NULL,
    VERSION                  INT8 NOT NULL,
    CONSTRAINT UITGAVE_PK PRIMARY KEY (ID),
    CONSTRAINT UITGAVE_CATEGORIE_FK FOREIGN KEY(FK_UITGAVE_CATEGORIE) REFERENCES UITGAVE_CATEGORIE
);

CREATE TABLE PRINCIPAL(
    USERNAME              VARCHAR(255)  NOT NULL,
    PASSWORD              VARCHAR(255)  NOT NULL,
    CONSTRAINT PRINCIPAL_PK PRIMARY KEY (USERNAME)
);

CREATE TABLE ROLE(
    USERNAME              VARCHAR(255)  NOT NULL,
    USERROLE              VARCHAR(255)  NOT NULL,
    CONSTRAINT ROLE_PK PRIMARY KEY (USERNAME, USERROLE),
    CONSTRAINT ROLE_PRINCIPAL_FK FOREIGN KEY(USERNAME) REFERENCES PRINCIPAL
);

CREATE SEQUENCE INKOMST_CATEGORIE_SEQ INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE UITGAVE_CATEGORIE_SEQ INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE INKOMST_SEQ INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE UITGAVE_SEQ INCREMENT BY 1 START WITH 1;
