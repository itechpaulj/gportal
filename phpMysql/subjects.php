<?php 

require './gportal_android_database/database.php';
$pdo = Database::connect();
date_default_timezone_set('Asia/Manila');
$date = date("Y-m-d");
$schoolcode=@$_GET['schoolcode'];

 
//**** where schoolcode="66527c19b9"
	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "select *, 
			(select programcode from programtbl where programid=subjecttbl.programid) as programcode, 
			(select programname from programtbl where programid=subjecttbl.programid) as programname,
			(select concat(ayear1, '-', ayear2, ' ', IF(ayearlevel=1, '1st Year', IF(ayearlevel=2, '2nd Year', IF(ayearlevel=3, '3rd Year', IF(ayearlevel=4, '4th Year', '')))), ' ', IF(aysem=1, '1st SEM', IF(aysem=2, '2nd SEM', ''))) as ay from aytbl where ayid=subjecttbl.ayid) as ay
			from subjecttbl where schoolcode=? ORDER BY subjectcode";
    $q = $pdo->prepare($sql);
    $q->execute(array($schoolcode));
	 $rowCount = $q->rowCount();
        if($rowCount>0){
            $data = array();
            while($subjects = $q->FETCH(PDO::FETCH_ASSOC)){
                array_push($data,$subjects);
            }
        }
        else{
            array_push($data,"No data");
        }
	echo json_encode($data);
				


?>