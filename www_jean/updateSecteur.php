<?php
require_once 'Param.php';
require_once 'DBConnex.php';
include_once 'secteursDAO.php';

// $_POST["codeS"] = "TES";
// $_POST["libelleS"] = "Test secteur";

print(json_encode(secteursDAO::updateSecteur($_POST["codeS"],$_POST["libelleS"])));