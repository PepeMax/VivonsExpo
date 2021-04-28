<?php
require_once 'Param.php';
require_once 'DBConnex.php';
include_once 'secteursDAO.php';

$_POST["codeU"] = "A";

print(json_encode(secteursDAO::getLesSecteursUnivers($_POST["codeU"])));