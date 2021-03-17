<?php

class UserDAO
{
    public static function authentification($login, $mdp)
    {
        try {
            $sql = "select status from exposant where login= :login AND mdp = :mdp ";
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
}
