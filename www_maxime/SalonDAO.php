<?php

include("UserDAO.php");

class SalonDAO
{

    public static function getSalon()
    {
        try {
            $sql = "SELECT CODES, LIBELLES FROM SECTEUR";
            $requetePrepa = DBConnex::getInstance()->prepare($sql);

            $requetePrepa->execute();
            $response = $requetePrepa->fetchAll(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            $response = "";
        }
        return $response;
    }

    public static function getStand($login) {

        $classe = new UserDAO();
        $codeExpo = $classe->getCodeExpo($login);

        try {
            $sql = "SELECT NUMH, CODEA, NUMT, NUMS FROM STAND WHERE CODEE = :codee";
            $requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("codee", $codeExpo);

            $requetePrepa->execute();
            $response = $requetePrepa->fetch(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            $response = $e;
        }
        return $response;
    }

    public static function demandeAutreStand($login, $motif)
    {
        try {
            $response = "";

            $date = date("Y-m-d");

            $sql1 = "INSERT INTO DEMANDE (LOGIN, DATED, MOTIF) VALUES (:login, :date, :motif);";
            $requete_1_Prepa = DBConnex::getInstance()->prepare($sql1);
            $requete_1_Prepa->bindParam("login", $login);
            $requete_1_Prepa->bindParam("date", $date);
            $requete_1_Prepa->bindParam("motif", $motif);

            $requete_1_Prepa->execute();

            $response = $requete_1_Prepa->rowCount();

        } catch (Exception $e) {
            $response .= $e;
        }
        return $response;
    }
}
