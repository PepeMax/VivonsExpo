<?php
class EtudiantDAO{
	public static function authentification($login , $mdp){
		try{
            $sql = "select numEtudiant, nomEtudiant , prenomEtudiant , mailEtudiant ,
            bacEtudiant,statutEtudiant from etudiant 
            where mailEtudiant= :login and mdpEtudiant = :mdp " ;
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
			$mdp =  md5($mdp);
			$requetePrepa->bindParam("login", $login);
			$requetePrepa->bindParam("mdp", $mdp);
			$requetePrepa->execute();
			$reponse = $requetePrepa->fetch(PDO::FETCH_ASSOC);
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
	}

	public static function etudiantsUneClasse($nomClasse){
		try{
			$sql = "select nomEtudiant,prenomEtudiant,mailEtudiant from etudiant where codeClasse = (select codeClasse from classe where nomClasse = :nomClasse);";
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
			$requetePrepa->bindParam("nomClasse",$nomClasse);
			$requetePrepa->execute();
			$reponse = $requetePrepa->fetchAll(PDO::FETCH_ASSOC);
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
	}

	public static function infoUnEtudiant($loginEtu){
		try{
			$sql = "select nomEtudiant,prenomEtudiant,mailEtudiant from etudiant where mailEtudiant = :mailEtudiant";
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
			$requetePrepa->bindParam("mailEtudiant",$loginEtu);
			$requetePrepa->execute();
			$reponse = $requetePrepa->fetch(PDO::FETCH_ASSOC);
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
	}
}
 