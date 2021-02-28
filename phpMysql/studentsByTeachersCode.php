<?php 

require './gportal_android_database/database.php';
$pdo = Database::connect();
date_default_timezone_set('Asia/Manila');
$date = date("Y-m-d");
$teacherscode=@$_GET['teacherscode'];
$data = array();

//get number of colleges 
//select count(collegecode) as numofcolleges from collegetbl where schoolcode="66527c19b9"
	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "select *, 
	(select concat(usertbl.lname, ', ' ,usertbl.fname ,' ', usertbl.mname) as name from usertbl where usertbl.userid=gradestbl.userid) as name, 
	(select cardid from usertbl where usertbl.userid=gradestbl.userid) as cardid,
	(select subjecttitle from subjecttbl where subjecttbl.subjectid=gradestbl.subjectid) as subjecttitle
	from gradestbl where teacherscode=? 
	ORDER BY name";
    $q = $pdo->prepare($sql);
    $q->execute(array($teacherscode));
	$rowCount = $q->rowCount();
        if($rowCount>0){
            while($students = $q->FETCH(PDO::FETCH_ASSOC)){
                array_push($data,$students);
            }
        }
        else{
            array_push($data,"No data");
        }
	echo json_encode($data);
				


?>