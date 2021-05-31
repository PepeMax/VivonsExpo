<?php
require_once 'Param.php';
require_once 'DBConnex.php';
include_once 'secteursDAO.php';

$_POST["codeS"] = 'EF';
$_POST["arrayNumT"] = ["1","2","3"];

print(json_encode(secteursDAO::setTraveeSecteur($_POST["arrayNumT"],$_POST["codeS"])));
