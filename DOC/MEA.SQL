--
-- Base de données : `vivonsexpo`
--

-- --------------------------------------------------------

--
-- Structure de la table `ALLEE`
--

CREATE TABLE `ALLEE` (
  `NUMH` varchar(1) NOT NULL,
  `CODEA` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `DEMANDE`
--

CREATE TABLE `DEMANDE` (
  `NUMD` varchar(4) NOT NULL,
  `LOGIN` varchar(25) NOT NULL,
  `DATED` date DEFAULT NULL,
  `MOTIF` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `EXPOSANT`
--

CREATE TABLE `EXPOSANT` (
  `LOGIN` varchar(25) NOT NULL,
  `MDP` varchar(32) DEFAULT NULL,
  `CODES` varchar(2) NOT NULL,
  `NUMT` varchar(2) DEFAULT NULL,
  `NUMS` varchar(3) DEFAULT NULL,
  `CODEA` varchar(2) DEFAULT NULL,
  `NUMH` varchar(1) DEFAULT NULL,
  `NUMH_1` varchar(1) DEFAULT NULL,
  `RAISONSOCIALE` varchar(40) DEFAULT NULL,
  `ACTIVITE` varchar(25) DEFAULT NULL,
  `NOM` varchar(25) DEFAULT NULL,
  `PRENOM` varchar(25) DEFAULT NULL,
  `TELEPHONE` varchar(20) DEFAULT NULL,
  `MAIL` varchar(40) DEFAULT NULL,
  `ANNEEINSCRIPTION` date DEFAULT NULL,
  `STATUT` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `HALL`
--

CREATE TABLE `HALL` (
  `NUMH` varchar(1) NOT NULL,
  `CODEU` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `NUMT` varchar(2) NOT NULL,
  `NUMS` varchar(3) NOT NULL,
  `LOGIN` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `TRAVEE`
--

CREATE TABLE `TRAVEE` (
  `NUMH` varchar(1) NOT NULL,
  `NUMT` varchar(2) NOT NULL,
  `CODES` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `UNIVERS`
--

CREATE TABLE `UNIVERS` (
  `CODEU` varchar(3) NOT NULL,
  `LIBELLEU` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `UNIVERS`
--

INSERT INTO `UNIVERS` (`CODEU`, `LIBELLEU`) VALUES
('A', 'Auto'),
('E', 'Evasions'),
('F', 'Les fetes'),
('M', 'Maison'),
('S', 'Sport');

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
  ADD KEY `I_FK_DEMANDE_EXPOSANT` (`LOGIN`);

--
-- Index pour la table `EXPOSANT`
--
ALTER TABLE `EXPOSANT`
  ADD PRIMARY KEY (`LOGIN`),
  ADD UNIQUE KEY `I_FK_EXPOSANT_STAND` (`NUMH_1`,`NUMH`,`CODEA`,`NUMT`,`NUMS`),
  ADD KEY `I_FK_EXPOSANT_SECTEUR` (`CODES`);

--
-- Index pour la table `HALL`
--
ALTER TABLE `HALL`
  ADD PRIMARY KEY (`NUMH`),
  ADD KEY `I_FK_HALL_UNIVERS` (`CODEU`);

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
  ADD UNIQUE KEY `I_FK_STAND_EXPOSANT` (`LOGIN`),
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
  ADD PRIMARY KEY (`CODEU`);

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
  ADD CONSTRAINT `demande_ibfk_1` FOREIGN KEY (`LOGIN`) REFERENCES `EXPOSANT` (`LOGIN`);

--
-- Contraintes pour la table `EXPOSANT`
--
ALTER TABLE `EXPOSANT`
  ADD CONSTRAINT `exposant_ibfk_1` FOREIGN KEY (`CODES`) REFERENCES `SECTEUR` (`CODES`),
  ADD CONSTRAINT `exposant_ibfk_2` FOREIGN KEY (`NUMH_1`,`NUMH`,`CODEA`,`NUMT`,`NUMS`) REFERENCES `STAND` (`NUMH_1`, `NUMH`, `CODEA`, `NUMT`, `NUMS`);

--
-- Contraintes pour la table `HALL`
--
ALTER TABLE `HALL`
  ADD CONSTRAINT `hall_ibfk_1` FOREIGN KEY (`CODEU`) REFERENCES `UNIVERS` (`CODEU`);

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
  ADD CONSTRAINT `stand_ibfk_2` FOREIGN KEY (`LOGIN`) REFERENCES `EXPOSANT` (`LOGIN`),
  ADD CONSTRAINT `stand_ibfk_3` FOREIGN KEY (`NUMH_1`,`CODEA`) REFERENCES `ALLEE` (`NUMH`, `CODEA`);

--
-- Contraintes pour la table `TRAVEE`
--
ALTER TABLE `TRAVEE`
  ADD CONSTRAINT `travee_ibfk_1` FOREIGN KEY (`NUMH`) REFERENCES `HALL` (`NUMH`),
  ADD CONSTRAINT `travee_ibfk_2` FOREIGN KEY (`CODES`) REFERENCES `SECTEUR` (`CODES`);
