<?php 

require './gportal_android_database/database.php';
$pdo = Database::connect();
date_default_timezone_set('Asia/Manila');
$date = date("Y-m-d");
$aysem=@$_GET['aysem'];
$ayear1=@$_GET['ayear1'];
$ayear2=@$_GET['ayear2'];
$ayearlevel=@$_GET['ayearlevel'];
$schoolcode=@$_GET['schoolcode'];
$row=0;
//**** where schoolcode="66527c19b9"

	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "SELECT * FROM aytbl where aysem=? AND ayear1=? AND ayear2=? AND ayearlevel=? AND schoolcode=?";
    $q = $pdo->prepare($sql);
    $q->execute(array($aysem, $ayear1, $ayear2, $ayearlevel, $schoolcode));
	 $rowCount = $q->rowCount();
		if($rowCount>0){
			echo "Acad Year was already added";	
		}else{
			$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			$sql = "INSERT INTO aytbl(aysem, ayear1, ayear2, ayearlevel, schoolcode) VALUES (?,?,?,?,?)";
			$q = $pdo->prepare($sql);
			$isSuccess = $q->execute(array($aysem, $ayear1, $ayear2, $ayearlevel, $schoolcode));

			if($isSuccess){
				echo  "Acad Year has been added";		
			}else{
				echo "An error occured.";
			}	
		}


?>