<?php
class universDAO{
    public static function getLesUnivers(){
        try{
            $sql = "select codeu, libelleu, numh from univers" ;
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
			$requetePrepa->execute();
			$reponse = $requetePrepa->fetchAll(PDO::FETCH_ASSOC);
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
    }

    public static function getLesHalls(){
        try{
            $sql = "select numh from hall" ;
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
			$requetePrepa->execute();
			$reponse = $requetePrepa->fetchAll(PDO::FETCH_ASSOC);
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
    }

    public static function ajoutUnivers($codeU,$libelleU,$numH){
        try{
            $sql = "insert into univers values(:codeu,:libelleu,:numH);" ;
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("codeu",$codeU);
            $requetePrepa->bindParam("libelleu",$libelleU);
            $requetePrepa->bindParam("numH",$numH);
			$requetePrepa->execute();
			$reponse = $requetePrepa->rowCount();
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
    }

	public static function nombreExposantUnivers(){
		try{
			$sql = "select UNIVERS.codeu,UNIVERS.libelleu, count(*) as nbeExposant from UNIVERS
					join SECTEUR S on UNIVERS.CODEU = S.CODEU
					join EXPOSANT E on S.CODES = E.CODES
					group by UNIVERS.CODEU;";
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
			$requetePrepa->execute();
			$reponse = $requetePrepa->fetchAll(PDO::FETCH_ASSOC);
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
	}

	public static function updateHallUnivers($codeU,$numH){
		try{
			$sql = "update univers set numh = :numH where codeu = :codeU";
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
			$requetePrepa->bindParam("codeU",$codeU);
            $requetePrepa->bindParam("numH",$numH);
			$requetePrepa->execute();
			$reponse = $requetePrepa->rowCount();
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
	}
}