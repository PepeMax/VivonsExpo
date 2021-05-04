<?php
require_once 'Param.php';
require_once 'DBConnex.php';
include_once 'secteursDAO.php';

// $_POST["codeS"] = "te";

print(json_encode(secteursDAO::deleteSecteur($_POST["codeS"])));