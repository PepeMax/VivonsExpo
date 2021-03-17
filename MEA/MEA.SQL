DROP DATABASE IF EXISTS VivonsExpo;

CREATE DATABASE IF NOT EXISTS VivonsExpo;
USE VivonsExpo;
# -----------------------------------------------------------------------------
#       TABLE : TRAVEE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS TRAVEE
 (
   NUMH VARCHAR(1) NOT NULL  ,
   NUMT VARCHAR(2) NOT NULL  ,
   CODES VARCHAR(2) NULL  
   , PRIMARY KEY (NUMH,NUMT) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE TRAVEE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_TRAVEE_HALL
     ON TRAVEE (NUMH ASC);

CREATE  INDEX I_FK_TRAVEE_SECTEUR
     ON TRAVEE (CODES ASC);

# -----------------------------------------------------------------------------
#       TABLE : HALL
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS HALL
 (
   NUMH VARCHAR(1) NOT NULL  ,
   CODEU VARCHAR(3) NOT NULL  
   , PRIMARY KEY (NUMH) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE HALL
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_HALL_UNIVERS
     ON HALL (CODEU ASC);

# -----------------------------------------------------------------------------
#       TABLE : USER
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS USER
 (
   LOGIN VARCHAR(25) NOT NULL  ,
   MDP VARCHAR(25) NULL  
   , PRIMARY KEY (LOGIN) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : SECTEUR
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS SECTEUR
 (
   CODES VARCHAR(2) NOT NULL  ,
   CODEU VARCHAR(3) NOT NULL  ,
   LIBELLES VARCHAR(25) NULL  
   , PRIMARY KEY (CODES) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE SECTEUR
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_SECTEUR_UNIVERS
     ON SECTEUR (CODEU ASC);

# -----------------------------------------------------------------------------
#       TABLE : UNIVERS
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS UNIVERS
 (
   CODEU VARCHAR(3) NOT NULL  ,
   LIBELLEU VARCHAR(25) NULL  
   , PRIMARY KEY (CODEU) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : STAFF
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS STAFF
 (
   LOGIN VARCHAR(25) NOT NULL  ,
   STATUT VARCHAR(25) NULL  ,
   MDP VARCHAR(25) NULL  
   , PRIMARY KEY (LOGIN) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : ALLEE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS ALLEE
 (
   NUMH VARCHAR(1) NOT NULL  ,
   CODEA VARCHAR(2) NOT NULL  
   , PRIMARY KEY (NUMH,CODEA) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE ALLEE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_ALLEE_HALL
     ON ALLEE (NUMH ASC);

# -----------------------------------------------------------------------------
#       TABLE : EXPOSANT
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS EXPOSANT
 (
   LOGIN VARCHAR(25) NOT NULL  ,
   CODES VARCHAR(2) NOT NULL  ,
   NUMT VARCHAR(2) NULL  ,
   NUMS VARCHAR(3) NULL  ,
   CODEA VARCHAR(2) NULL  ,
   NUMH VARCHAR(1) NULL  ,
   NUMH_1 VARCHAR(1) NULL  ,
   RAISONSOCIALE VARCHAR(40) NULL  ,
   ACTIVITE VARCHAR(25) NULL  ,
   NOM VARCHAR(25) NULL  ,
   PRENOM VARCHAR(25) NULL  ,
   TELEPHONE VARCHAR(20) NULL  ,
   MAIL VARCHAR(40) NULL  ,
   ANNEEINSCRIPTION DATE NULL  ,
   MDP VARCHAR(25) NULL  
   , PRIMARY KEY (LOGIN) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE EXPOSANT
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_EXPOSANT_SECTEUR
     ON EXPOSANT (CODES ASC);

CREATE UNIQUE INDEX I_FK_EXPOSANT_STAND
     ON EXPOSANT (NUMH_1 ASC,NUMH ASC,CODEA ASC,NUMT ASC,NUMS ASC);

# -----------------------------------------------------------------------------
#       TABLE : STAND
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS STAND
 (
   NUMH_1 VARCHAR(1) NOT NULL  ,
   NUMH VARCHAR(1) NOT NULL  ,
   CODEA VARCHAR(2) NOT NULL  ,
   NUMT VARCHAR(2) NOT NULL  ,
   NUMS VARCHAR(3) NOT NULL  ,
   LOGIN VARCHAR(25) NULL  
   , PRIMARY KEY (NUMH_1,NUMH,CODEA,NUMT,NUMS) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE STAND
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_STAND_TRAVEE
     ON STAND (NUMH ASC,NUMT ASC);

CREATE UNIQUE INDEX I_FK_STAND_EXPOSANT
     ON STAND (LOGIN ASC);

CREATE  INDEX I_FK_STAND_ALLEE
     ON STAND (NUMH_1 ASC,CODEA ASC);

# -----------------------------------------------------------------------------
#       TABLE : DEMANDE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS DEMANDE
 (
   NUMD VARCHAR(4) NOT NULL  ,
   LOGIN VARCHAR(25) NOT NULL  ,
   DATED DATE NULL  ,
   MOTIF VARCHAR(40) NULL  
   , PRIMARY KEY (NUMD) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE DEMANDE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_DEMANDE_EXPOSANT
     ON DEMANDE (LOGIN ASC);


# -----------------------------------------------------------------------------
#       CREATION DES REFERENCES DE TABLE
# -----------------------------------------------------------------------------


ALTER TABLE TRAVEE 
  ADD FOREIGN KEY FK_TRAVEE_HALL (NUMH)
      REFERENCES HALL (NUMH) ;


ALTER TABLE TRAVEE 
  ADD FOREIGN KEY FK_TRAVEE_SECTEUR (CODES)
      REFERENCES SECTEUR (CODES) ;


ALTER TABLE HALL 
  ADD FOREIGN KEY FK_HALL_UNIVERS (CODEU)
      REFERENCES UNIVERS (CODEU) ;


ALTER TABLE SECTEUR 
  ADD FOREIGN KEY FK_SECTEUR_UNIVERS (CODEU)
      REFERENCES UNIVERS (CODEU) ;


ALTER TABLE STAFF 
  ADD FOREIGN KEY FK_STAFF_USER (LOGIN)
      REFERENCES USER (LOGIN) ;


ALTER TABLE ALLEE 
  ADD FOREIGN KEY FK_ALLEE_HALL (NUMH)
      REFERENCES HALL (NUMH) ;


ALTER TABLE EXPOSANT 
  ADD FOREIGN KEY FK_EXPOSANT_SECTEUR (CODES)
      REFERENCES SECTEUR (CODES) ;


ALTER TABLE EXPOSANT 
  ADD FOREIGN KEY FK_EXPOSANT_STAND (NUMH_1,NUMH,CODEA,NUMT,NUMS)
      REFERENCES STAND (NUMH_1,NUMH,CODEA,NUMT,NUMS) ;


ALTER TABLE EXPOSANT 
  ADD FOREIGN KEY FK_EXPOSANT_USER (LOGIN)
      REFERENCES USER (LOGIN) ;


ALTER TABLE STAND 
  ADD FOREIGN KEY FK_STAND_TRAVEE (NUMH,NUMT)
      REFERENCES TRAVEE (NUMH,NUMT) ;


ALTER TABLE STAND 
  ADD FOREIGN KEY FK_STAND_EXPOSANT (LOGIN)
      REFERENCES EXPOSANT (LOGIN) ;


ALTER TABLE STAND 
  ADD FOREIGN KEY FK_STAND_ALLEE (NUMH_1,CODEA)
      REFERENCES ALLEE (NUMH,CODEA) ;


ALTER TABLE DEMANDE 
  ADD FOREIGN KEY FK_DEMANDE_EXPOSANT (LOGIN)
      REFERENCES EXPOSANT (LOGIN) ;

