<?php 

require './gportal_android_database/database.php';
$pdo = Database::connect();
date_default_timezone_set('Asia/Manila');
$date = date("Y-m-d");
$schoolcode=@$_GET['schoolcode'];
$data = array();

//get number of colleges 
//select count(collegecode) as numofcolleges from collegetbl where schoolcode="66527c19b9"
	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "select * from collegetbl where schoolcode=? ORDER BY collegecode";
    $q = $pdo->prepare($sql);
    $q->execute(array($schoolcode));
	$rowCount = $q->rowCount();
        if($rowCount>0){
            while($colleges = $q->FETCH(PDO::FETCH_ASSOC)){
                array_push($data,$colleges);
            }
        }
        else{
            array_push($data,"No data");
        }
	echo json_encode($data);
				


?>