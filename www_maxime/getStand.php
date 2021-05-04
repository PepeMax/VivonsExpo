<?php

require_once 'param.php';
require_once 'DBConnex.php';
require_once 'SalonDAO.php';

print(json_encode(SalonDAO::getStand($_POST["login"])));

