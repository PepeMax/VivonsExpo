<?php
require_once 'Param.php';
require_once 'DBConnex.php';
include_once 'secteursDAO.php';

// $_POST["codeS"] = "EF";

print(json_encode(secteursDAO::getTraveeSecteur($_POST["codeS"])));
