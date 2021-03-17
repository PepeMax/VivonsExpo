#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: HALL
#------------------------------------------------------------

CREATE TABLE HALL(
        numH Varchar (1) NOT NULL
	,CONSTRAINT HALL_PK PRIMARY KEY (numH)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: TRAVEE
#------------------------------------------------------------

CREATE TABLE TRAVEE(
        numT Varchar (2) NOT NULL ,
        numH Varchar (1) NOT NULL
	,CONSTRAINT TRAVEE_PK PRIMARY KEY (numT)

	,CONSTRAINT TRAVEE_HALL_FK FOREIGN KEY (numH) REFERENCES HALL(numH)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: ALLEE
#------------------------------------------------------------

CREATE TABLE ALLEE(
        codeA Varchar (2) NOT NULL ,
        numH  Varchar (1) NOT NULL
	,CONSTRAINT ALLEE_PK PRIMARY KEY (codeA)

	,CONSTRAINT ALLEE_HALL_FK FOREIGN KEY (numH) REFERENCES HALL(numH)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: STAND
#------------------------------------------------------------

CREATE TABLE STAND(
        numS  Varchar (3) NOT NULL ,
        numT  Varchar (2) NOT NULL ,
        codeA Varchar (2) NOT NULL
	,CONSTRAINT STAND_PK PRIMARY KEY (numS)

	,CONSTRAINT STAND_TRAVEE_FK FOREIGN KEY (numT) REFERENCES TRAVEE(numT)
	,CONSTRAINT STAND_ALLEE0_FK FOREIGN KEY (codeA) REFERENCES ALLEE(codeA)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: UNIVERS
#------------------------------------------------------------

CREATE TABLE UNIVERS(
        codeU    Varchar (3) NOT NULL ,
        libelleU Varchar (25)
	,CONSTRAINT UNIVERS_PK PRIMARY KEY (codeU)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: SECTEUR
#------------------------------------------------------------

CREATE TABLE SECTEUR(
        codeS    Varchar (2) NOT NULL ,
        libelleS Varchar (25) ,
        codeU    Varchar (3) NOT NULL
	,CONSTRAINT SECTEUR_PK PRIMARY KEY (codeS)

	,CONSTRAINT SECTEUR_UNIVERS_FK FOREIGN KEY (codeU) REFERENCES UNIVERS(codeU)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: EXPOSANT
#------------------------------------------------------------

CREATE TABLE EXPOSANT(
        Login            Varchar (25) NOT NULL ,
        Mdp              Varchar (25) ,
        raisonSociale    Varchar (40) ,
        activite         Varchar (25) ,
        nom              Varchar (25) ,
        prenom           Varchar (25) ,
        telephone        Varchar (20) ,
        mail             Varchar (40) ,
        anneeInscription Int ,
        codeS            Varchar (2) NOT NULL
	,CONSTRAINT EXPOSANT_PK PRIMARY KEY (Login)

	,CONSTRAINT EXPOSANT_SECTEUR_FK FOREIGN KEY (codeS) REFERENCES SECTEUR(codeS)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: DEMANDE
#------------------------------------------------------------

CREATE TABLE DEMANDE(
        numD  Varchar (4) NOT NULL ,
        dateD Date ,
        motif Varchar (40) ,
        Login Varchar (25) NOT NULL
	,CONSTRAINT DEMANDE_PK PRIMARY KEY (numD)

	,CONSTRAINT DEMANDE_EXPOSANT_FK FOREIGN KEY (Login) REFERENCES EXPOSANT(Login)
)ENGINE=InnoDB;

