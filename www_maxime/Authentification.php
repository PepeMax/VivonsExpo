<?php

require_once 'param.php';
require_once 'DBConnex.php';
require_once 'UserDAO.php';

print(json_encode(UserDAO::authentification($_POST['login'], $_POST['mdp'])));

