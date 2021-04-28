<?php
class secteursDAO{
    public static function getLesSecteursUnivers($codeU){
        try{
            $sql = "select codes, libelles from secteur where codeu = :codeu;" ;
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("codeu",$codeU);
			$requetePrepa->execute();
			$reponse = $requetePrepa->fetchAll(PDO::FETCH_ASSOC);
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
    }

    public static function ajoutSecteur($codeS,$codeU,$libelleS){
        try{
            $sql = "insert into secteur values(:codes,:codeu,:libelles);" ;
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("codes",$codeS);
            $requetePrepa->bindParam("codeu",$codeU);
            $requetePrepa->bindParam("libelles",$libelleS);
			$requetePrepa->execute();
			$reponse = $requetePrepa->rowCount();
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
    }
}