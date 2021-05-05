<?php
require_once 'Param.php';
require_once 'DBConnex.php';
include_once 'universDAO.php';

// $_POST["codeU"] = 'TES';
// $_POST["libelleU"] = 'Univer Test';
// $_POST["numH"] = '4';

print(json_encode(universDAO::ajoutUnivers($_POST["codeU"],$_POST["libelleU"],null)));