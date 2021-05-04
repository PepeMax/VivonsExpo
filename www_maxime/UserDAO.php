<?php

class UserDAO
{
    public static function authentification($login, $mdp)
    {
        try {
            $sql = "select login, mdp, statut from user where login= :login AND mdp = :mdp ";
            $requetePrepa = DBConnex::getInstance()->prepare($sql);
            $mdp = md5($mdp);
            $requetePrepa->bindParam("login", $login);
            $requetePrepa->bindParam("mdp", $mdp);

            $requetePrepa->execute();
            $reponse = $requetePrepa->fetch(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            $reponse = "";
        }
        return $reponse;
    }

    public static function registerExposant($login, $codes, $numt, $nums, $codea, $numh, $numh_1, $raison_sociale, $activite, $nom, $prenom, $tel, $mail, $mdp)
    {
        try {
            $response = "";

            $mdp = md5($mdp);
            $year = date("Y");

            $sql1 = "INSERT INTO USER (LOGIN, MDP, STATUT) VALUES (:login, :mdp, 'exposant');";
            $requete_1_Prepa = DBConnex::getInstance()->prepare($sql1);
            $requete_1_Prepa->bindParam("login", $login);
            $requete_1_Prepa->bindParam("mdp", $mdp);

            $requete_1_Prepa->execute();
            $requete_1_Prepa->rowCount();

            if ($requete_1_Prepa == 1) {
                $sql2 = "INSERT INTO EXPOSANT (LOGIN, CODES, NUMT, NUMS, CODEA, NUMH, NUMH_1, RAISONSOCIALE, ACTIVITE, NOM, PRENOM, TELEPHONE, MAIL, ANNEEINSCRIPTION) VALUES (:login, :codes, NULL, NULL, NULL, NULL, NULL, :raison_sociale, :activite, :nom, :prenom, :tel, :mail, :annee_inscription); ";

                $requete_2_Prepa = DBConnex::getInstance()->prepare($sql2);

                $requete_2_Prepa->bindParam("login", $login);
                $requete_2_Prepa->bindParam("codes", $codes);
                $requete_2_Prepa->bindParam("raison_sociale", $raison_sociale);
                $requete_2_Prepa->bindParam("activite", $activite);
                $requete_2_Prepa->bindParam("nom", $nom);
                $requete_2_Prepa->bindParam("prenom", $prenom);
                $requete_2_Prepa->bindParam("tel", $tel);
                $requete_2_Prepa->bindParam("mail", $mail);
                $requete_2_Prepa->bindParam("annee_inscription", $year);

                $requete_2_Prepa->execute();
                $reponse2 = $requete_2_Prepa->rowCount();

                $response = "OK";

                if ($reponse2 == 0) {
                    $response .= "ERREUR : Une erreur est survenue";
                }

            } else {
                $response = "ERREUR : L'utilisateur existe déjà";
            }

        } catch (Exception $e) {
            $response .= $e;
        }
        return $response;
    }

    public static function getNbDemannde($login)
    {
        try {
            $sql = "SELECT COUNT(login) FROM DEMANDE where login= :login";
            $requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("login", $login);

            $requetePrepa->execute();
            $reponse = $requetePrepa->fetch(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            $reponse = "";
        }
        return $reponse;
    }



}
