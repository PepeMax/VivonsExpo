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
}