<?php
require_once 'Param.php';
require_once 'DBConnex.php';
include_once 'userDAO.php';

// $_POST['login'] = "jmazagot";
// $_POST['mdp'] = "jmazagot";

print(json_encode(userDAO::authentification($_POST['login'], $_POST['mdp'])));