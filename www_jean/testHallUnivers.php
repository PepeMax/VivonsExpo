<?php
require_once 'Param.php';
require_once 'DBConnex.php';
include_once 'universDAO.php';

// $_POST["codeU"] = 'A';

print(json_encode(universDAO::testHallUnivers($_POST["codeU"])));