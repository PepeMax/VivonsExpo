<?php
require_once 'Param.php';
require_once 'DBConnex.php';
include_once 'secteursDAO.php';

// $_POST["codeS"] = "TE";
// $_POST["codeU"] = "A";
// $_POST["libelleS"] = "Secteur Test";

print(json_encode(secteursDAO::ajoutSecteur($_POST["codeS"],$_POST["codeU"],$_POST["libelleS"])));