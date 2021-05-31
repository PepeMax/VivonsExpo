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

	public static function updateSecteur($codeS,$libelleS){
		try{
            $sql = "update secteur set libelles = :libelles where codes = :codes;" ;
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("codes",$codeS);
            $requetePrepa->bindParam("libelles",$libelleS);
			$requetePrepa->execute();
			$reponse = $requetePrepa->rowCount();
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
	}

	public static function deleteSecteur($codeS){
		try{
            $sql = "delete from secteur where codes = :codes;" ;
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("codes",$codeS);
			$requetePrepa->execute();
			$reponse = $requetePrepa->rowCount();
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
	}

	public static function nombreExposantSecteursUnUnivers($codeU){
		try{
			$sql = "select S.codes,S.libelles, count(*) as nbeExposant from SECTEUR S
					join EXPOSANT E on S.CODES = E.CODES
					where s.codeu = :codeU
					group by s.codes;";
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("codeU",$codeU);
			$requetePrepa->execute();
			$reponse = $requetePrepa->fetchAll(PDO::FETCH_ASSOC);
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
	}

	public static function getTraveeSecteur($codeS){
		try{
			$sql = "select numt from travee
					where codes is null
					and numh = (select numH from univers
								join secteur on univers.codeu = secteur.codeu
								where codes = :codeS);";
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("codeS",$codeS);
			$requetePrepa->execute();
			$reponse = $requetePrepa->fetchAll(PDO::FETCH_ASSOC);
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
	}

	public static function setTraveeSecteur($arrayNumT,$codeS){
		try{
            $sql = "update travee set codeS = :codeS where numT in (:numT);" ;
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
			$arrayT = implode(',',  $arrayNumT);
			var_dump($arrayT);
			var_dump($arrayNumT);
            $requetePrepa->bindParam("codeS",$codeS);
            $requetePrepa->bindParam("numT",$arrayT);
			$requetePrepa->execute();
			$reponse = $requetePrepa->rowCount();
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
	}
}