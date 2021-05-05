-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : mer. 05 mai 2021 à 09:14
-- Version du serveur :  5.7.32
-- Version de PHP : 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données : `VivonsExpoTest`
--

-- --------------------------------------------------------

--
-- Structure de la table `ALLEE`
--

CREATE TABLE `ALLEE` (
  `NUMH` varchar(1) NOT NULL,
  `CODEA` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `ALLEE`
--

INSERT INTO `ALLEE` (`NUMH`, `CODEA`) VALUES
('1', 'A'),
('1', 'B'),
('1', 'C'),
('1', 'D'),
('3', 'A'),
('3', 'B'),
('3', 'C'),
('3', 'D');

-- --------------------------------------------------------

--
-- Structure de la table `DEMANDE`
--

CREATE TABLE `DEMANDE` (
  `NUMD` int(4) NOT NULL,
  `CODEE` int(2) NOT NULL,
  `DATED` date DEFAULT NULL,
  `MOTIF` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `EXPOSANT`
--

CREATE TABLE `EXPOSANT` (
  `CODEE` int(2) NOT NULL,
  `LOGIN` varchar(25) NOT NULL,
  `CODES` varchar(2) NOT NULL,
  `NUMT` int(2) DEFAULT NULL,
  `NUMS` int(2) DEFAULT NULL,
  `CODEA` varchar(2) DEFAULT NULL,
  `NUMH` varchar(1) DEFAULT NULL,
  `NUMH_1` varchar(1) DEFAULT NULL,
  `RAISONSOCIALE` varchar(40) DEFAULT NULL,
  `ACTIVITE` varchar(25) DEFAULT NULL,
  `NOM` varchar(25) DEFAULT NULL,
  `PRENOM` varchar(25) DEFAULT NULL,
  `TELEPHONE` varchar(20) DEFAULT NULL,
  `MAIL` varchar(40) DEFAULT NULL,
  `ANNEEINSCRIPTION` year(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `HALL`
--

CREATE TABLE `HALL` (
  `NUMH` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `HALL`
--

INSERT INTO `HALL` (`NUMH`) VALUES
('1'),
('2'),
('3'),
('4');

-- --------------------------------------------------------

--
-- Structure de la table `SECTEUR`
--

CREATE TABLE `SECTEUR` (
  `CODES` varchar(2) NOT NULL,
  `CODEU` varchar(3) NOT NULL,
  `LIBELLES` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `SECTEUR`
--

INSERT INTO `SECTEUR` (`CODES`, `CODEU`, `LIBELLES`) VALUES
('A', 'M', 'Ameublement'),
('AU', 'A', 'Auto'),
('BO', 'M', 'Vivons Bois'),
('CA', 'F', 'Cadeaux'),
('CC', 'E', 'Camping cars'),
('CH', 'M', 'Cheminées'),
('CI', 'M', 'Cuisines'),
('CR', 'F', 'Créateurs'),
('DE', 'M', 'Décoration'),
('EF', 'F', 'Épicerie fine'),
('EM', 'M', 'Equipement ménager'),
('ES', 'S', 'Équipements sportifs'),
('H', 'M', 'Amélioration Habitat'),
('I', 'M', 'Immobilier'),
('L', 'S', 'Ligues et comités'),
('N', 'S', 'Neige'),
('OF', 'E', 'Organisme de financement'),
('P', 'M', 'Paysagiste'),
('PI', 'M', 'Piscines'),
('PO', 'F', 'Producteurs'),
('PR', 'A', 'Presse'),
('RL', 'A', 'Roulez loisirs'),
('VO', 'E', 'Véhicules occasions'),
('VS', 'F', 'Vins et spiritueux');

-- --------------------------------------------------------

--
-- Structure de la table `STAND`
--

CREATE TABLE `STAND` (
  `NUMH_1` varchar(1) NOT NULL,
  `NUMH` varchar(1) NOT NULL,
  `CODEA` varchar(2) NOT NULL,
  `NUMT` int(2) NOT NULL,
  `NUMS` int(3) NOT NULL,
  `CODEE` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `TRAVEE`
--

CREATE TABLE `TRAVEE` (
  `NUMH` varchar(1) NOT NULL,
  `NUMT` int(2) NOT NULL,
  `CODES` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `UNIVERS`
--

CREATE TABLE `UNIVERS` (
  `CODEU` varchar(3) NOT NULL,
  `LIBELLEU` varchar(25) DEFAULT NULL,
  `NUMH` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `UNIVERS`
--

INSERT INTO `UNIVERS` (`CODEU`, `LIBELLEU`, `NUMH`) VALUES
('A', 'Auto', '1'),
('E', 'Evasions', '3'),
('F', 'Les fetes', '1'),
('M', 'Maison', '1'),
('S', 'Sport', '1');

-- --------------------------------------------------------

--
-- Structure de la table `USER`
--

CREATE TABLE `USER` (
  `LOGIN` varchar(25) NOT NULL,
  `MDP` varchar(32) NOT NULL,
  `STATUT` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `ALLEE`
--
ALTER TABLE `ALLEE`
  ADD PRIMARY KEY (`NUMH`,`CODEA`),
  ADD KEY `I_FK_ALLEE_HALL` (`NUMH`);

--
-- Index pour la table `DEMANDE`
--
ALTER TABLE `DEMANDE`
  ADD PRIMARY KEY (`NUMD`),
  ADD KEY `I_FK_DEMANDE_EXPOSANT` (`CODEE`);

--
-- Index pour la table `EXPOSANT`
--
ALTER TABLE `EXPOSANT`
  ADD PRIMARY KEY (`CODEE`),
  ADD UNIQUE KEY `I_FK_EXPOSANT_STAND` (`NUMH_1`,`NUMH`,`CODEA`,`NUMT`,`NUMS`),
  ADD KEY `I_FK_EXPOSANT_SECTEUR` (`CODES`),
  ADD KEY `I_FK_EXPOSANT_USER` (`LOGIN`);

--
-- Index pour la table `HALL`
--
ALTER TABLE `HALL`
  ADD PRIMARY KEY (`NUMH`);

--
-- Index pour la table `SECTEUR`
--
ALTER TABLE `SECTEUR`
  ADD PRIMARY KEY (`CODES`),
  ADD KEY `I_FK_SECTEUR_UNIVERS` (`CODEU`);

--
-- Index pour la table `STAND`
--
ALTER TABLE `STAND`
  ADD PRIMARY KEY (`NUMH_1`,`NUMH`,`CODEA`,`NUMT`,`NUMS`),
  ADD UNIQUE KEY `I_FK_STAND_EXPOSANT` (`CODEE`),
  ADD KEY `I_FK_STAND_TRAVEE` (`NUMH`,`NUMT`),
  ADD KEY `I_FK_STAND_ALLEE` (`NUMH_1`,`CODEA`);

--
-- Index pour la table `TRAVEE`
--
ALTER TABLE `TRAVEE`
  ADD PRIMARY KEY (`NUMH`,`NUMT`),
  ADD KEY `I_FK_TRAVEE_HALL` (`NUMH`),
  ADD KEY `I_FK_TRAVEE_SECTEUR` (`CODES`);

--
-- Index pour la table `UNIVERS`
--
ALTER TABLE `UNIVERS`
  ADD PRIMARY KEY (`CODEU`),
  ADD KEY `I_FK_UNIVERS_HALL` (`NUMH`);

--
-- Index pour la table `USER`
--
ALTER TABLE `USER`
  ADD PRIMARY KEY (`LOGIN`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `DEMANDE`
--
ALTER TABLE `DEMANDE`
  MODIFY `NUMD` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `EXPOSANT`
--
ALTER TABLE `EXPOSANT`
  MODIFY `CODEE` int(2) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `ALLEE`
--
ALTER TABLE `ALLEE`
  ADD CONSTRAINT `allee_ibfk_1` FOREIGN KEY (`NUMH`) REFERENCES `HALL` (`NUMH`);

--
-- Contraintes pour la table `DEMANDE`
--
ALTER TABLE `DEMANDE`
  ADD CONSTRAINT `demande_ibfk_1` FOREIGN KEY (`CODEE`) REFERENCES `EXPOSANT` (`CODEE`);

--
-- Contraintes pour la table `EXPOSANT`
--
ALTER TABLE `EXPOSANT`
  ADD CONSTRAINT `exposant_ibfk_1` FOREIGN KEY (`CODES`) REFERENCES `SECTEUR` (`CODES`),
  ADD CONSTRAINT `exposant_ibfk_2` FOREIGN KEY (`NUMH_1`,`NUMH`,`CODEA`,`NUMT`,`NUMS`) REFERENCES `STAND` (`NUMH_1`, `NUMH`, `CODEA`, `NUMT`, `NUMS`),
  ADD CONSTRAINT `exposant_ibfk_3` FOREIGN KEY (`LOGIN`) REFERENCES `USER` (`LOGIN`);

--
-- Contraintes pour la table `SECTEUR`
--
ALTER TABLE `SECTEUR`
  ADD CONSTRAINT `secteur_ibfk_1` FOREIGN KEY (`CODEU`) REFERENCES `UNIVERS` (`CODEU`);

--
-- Contraintes pour la table `STAND`
--
ALTER TABLE `STAND`
  ADD CONSTRAINT `stand_ibfk_1` FOREIGN KEY (`NUMH`,`NUMT`) REFERENCES `TRAVEE` (`NUMH`, `NUMT`),
  ADD CONSTRAINT `stand_ibfk_2` FOREIGN KEY (`CODEE`) REFERENCES `EXPOSANT` (`CODEE`),
  ADD CONSTRAINT `stand_ibfk_3` FOREIGN KEY (`NUMH_1`,`CODEA`) REFERENCES `ALLEE` (`NUMH`, `CODEA`);

--
-- Contraintes pour la table `TRAVEE`
--
ALTER TABLE `TRAVEE`
  ADD CONSTRAINT `travee_ibfk_1` FOREIGN KEY (`NUMH`) REFERENCES `HALL` (`NUMH`),
  ADD CONSTRAINT `travee_ibfk_2` FOREIGN KEY (`CODES`) REFERENCES `SECTEUR` (`CODES`);

--
-- Contraintes pour la table `UNIVERS`
--
ALTER TABLE `UNIVERS`
  ADD CONSTRAINT `univers_ibfk_1` FOREIGN KEY (`NUMH`) REFERENCES `HALL` (`NUMH`);
