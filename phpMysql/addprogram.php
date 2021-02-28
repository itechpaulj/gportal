<?php 

require './gportal_android_database/database.php';
$pdo = Database::connect();
date_default_timezone_set('Asia/Manila');
$date = date("Y-m-d");
$programcode=@$_GET['programcode'];
$programname=@$_GET['programname'];
$major=@$_GET['major'];
$collegeid=@$_GET['collegeid'];
$schoolcode=@$_GET['schoolcode'];


$row=0;
//**** where schoolcode="66527c19b9"

	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "SELECT * FROM programtbl where programcode=? AND schoolcode=?";
    $q = $pdo->prepare($sql);
    $q->execute(array($programcode, $schoolcode));
	 $rowCount = $q->rowCount();
		if($rowCount>0){
			echo "Program was already added";	
		}else{		
			$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			$sql = "INSERT INTO programtbl(programcode, programname, major, collegeid, schoolcode) VALUES (?,?,?,?,?)";
			$q = $pdo->prepare($sql);
			$isSuccess = $q->execute(array($programcode, $programname, $major, $collegeid, $schoolcode));

			if($isSuccess){
				echo  "Program has been added";		
			}else{
				echo "An error occured.";
			}	
		}


?>