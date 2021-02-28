<?php 

require './gportal_android_database/database.php';
$pdo = Database::connect();
date_default_timezone_set('Asia/Manila');
$date = date("Y-m-d");
$cardid=@$_GET['cardid'];
$data = array();

//get number of colleges 
//select count(collegecode) as numofcolleges from collegetbl where schoolcode="66527c19b9"
	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "SELECT *,  
			(select subjectcode from subjecttbl where subjecttbl.subjectid=(select subjectid from teacherscodetbl where teacherscodetbl.teacherscode=enrollcodetbl.teacherscode)) as subjectcode
			from enrollcodetbl 
			where cardid=?
			AND (select status from teacherscodetbl where teacherscodetbl.teacherscode=enrollcodetbl.teacherscode)='active'
			ORDER BY date DESC ";
    $q = $pdo->prepare($sql);
    $q->execute(array($cardid));
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