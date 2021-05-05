<?php
require_once 'Param.php';
require_once 'DBConnex.php';
include_once 'secteursDAO.php';

$_POST["codeU"] = "F";

print(json_encode(secteursDAO::nombreExposantSecteursUnUnivers($_POST["codeU"])));
