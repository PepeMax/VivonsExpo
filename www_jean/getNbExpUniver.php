<?php
require_once 'Param.php';
require_once 'DBConnex.php';
include_once 'universDAO.php';

print(json_encode(universDAO::nombreExposantUnivers()));