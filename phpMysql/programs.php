<?php 

require './gportal_android_database/database.php';
$pdo = Database::connect();
date_default_timezone_set('Asia/Manila');
$date = date("Y-m-d");
$schoolcode=@$_GET['schoolcode'];

//get number of programs 
//select count(collegecode) as numofcolleges from collegetbl where schoolcode="66527c19b9"
	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "select *, (select collegecode from collegetbl where collegeid=programtbl.collegeid) as collegecode, (select collegename from collegetbl where collegeid=programtbl.collegeid) as collegename  from programtbl where schoolcode=? ORDER BY programcode";
    $q = $pdo->prepare($sql);
    $q->execute(array($schoolcode));
	 $rowCount = $q->rowCount();
        if($rowCount>0){
            $data = array();
            while($programs = $q->FETCH(PDO::FETCH_ASSOC)){
                array_push($data,$programs);
            }
        }
        else{
            array_push($data,"No data");
        }
	echo json_encode($data);
				


?>