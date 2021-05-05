<?php
require_once 'Param.php';
require_once 'DBConnex.php';
include_once 'universDAO.php';

// $_POST["codeU"] = 'F';
// $_POST["numH"] = '4';

print(json_encode(universDAO::updateHallUnivers($_POST["codeU"],$_POST["numH"])));