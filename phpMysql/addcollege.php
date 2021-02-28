<?php 

require './gportal_android_database/database.php';
$pdo = Database::connect();
date_default_timezone_set('Asia/Manila');
$date = date("Y-m-d");
$schoolcode=@$_GET['schoolcode'];
$collegecode=@$_GET['collegecode'];
$collegename=@$_GET['collegename'];
$row=0;
//**** where schoolcode="66527c19b9"

	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "SELECT * FROM collegetbl where schoolcode=? AND collegecode=?";
    $q = $pdo->prepare($sql);
    $q->execute(array($schoolcode, $collegecode));
	 $rowCount = $q->rowCount();
		if($rowCount>0){
			echo $collegecode ." was added already";	
		}else{
			$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			$sql = "INSERT INTO collegetbl(collegecode, collegename, schoolcode) VALUES (?,?,?)";
			$q = $pdo->prepare($sql);
			$isSuccess = $q->execute(array($collegecode,$collegename,$schoolcode));

			if($isSuccess){
				echo  $collegecode ." has been added";		
			}else{
				echo "An error occured.";
			}	
		}


?>