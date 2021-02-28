<?php 

require './gportal_android_database/database.php';
$pdo = Database::connect();
date_default_timezone_set('Asia/Manila');
$date = date("Y-m-d");
$schoolcode=@$_GET['schoolcode'];

 
//**** where schoolcode="66527c19b9"
	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "select *,
			(select programcode from programtbl where programid=sectiontbl.programid) as programcode
			from sectiontbl where schoolcode=? ORDER BY sectioncode";
    $q = $pdo->prepare($sql);
    $q->execute(array($schoolcode));
	 $rowCount = $q->rowCount();
        if($rowCount>0){
            $data = array();
            while($sections = $q->FETCH(PDO::FETCH_ASSOC)){
                array_push($data,$sections);
            }
        }
        else{
            array_push($data,"No data");
        }
	echo json_encode($data);
				


?>