<?php
require_once 'Param.php';
require_once 'DBConnex.php';
include_once 'userDAO.php';

$_POST['login'] = "ljaymot";
$_POST['mdp'] = "jaymot";

print(json_encode(userDAO::authentification($_POST['login'], $_POST['mdp'])));