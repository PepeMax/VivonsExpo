<?php
require_once 'Param.php';
require_once 'DBConnex.php';
include_once 'etudiantsDAO.php';

// $_POST['login'] = "contact@jeanmazagot.fr";
// $_POST['mdp'] = "jmazagot";

print(json_encode(EtudiantDAO::authentification($_POST['login'], $_POST['mdp'])));


