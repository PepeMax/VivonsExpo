<?php

require_once 'param.php';
require_once 'DBConnex.php';
require_once 'UserDAO.php';

print(json_encode(UserDAO::registerExposant($_POST[login], $_POST[codes], $_POST[raison_sociale], $_POST[activite], $_POST[nom], $_POST[prenom], $_POST[tel], $_POST[mail], $_POST[mdp])));
