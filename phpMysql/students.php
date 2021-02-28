<?php 

require './gportal_android_database/database.php';
$pdo = Database::connect();
date_default_timezone_set('Asia/Manila');
$date = date("Y-m-d");
$schoolcode=@$_GET['schoolcode'];

 
//**** where schoolcode="66527c19b9"
	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "select userid, cardid, lname, fname, mname, gender, viewcode, 
			collegeid,
			(select collegecode from collegetbl where collegeid=usertbl.collegeid) as collegecode,
			sectionid,
			(select sectioncode from sectiontbl where sectionid=usertbl.sectionid) as sectioncode,
			programid,
			(select programcode from programtbl where programid=usertbl.programid) as programcode,
			concat('http://jeepcard.net/gportal/', photo) as photo
			from usertbl where schoolcode=? AND access='Student' ORDER BY lname ASC";
    $q = $pdo->prepare($sql);
    $q->execute(array($schoolcode));
	 $rowCount = $q->rowCount();
        if($rowCount>0){
            $data = array();
            while($teachers = $q->FETCH(PDO::FETCH_ASSOC)){
                array_push($data,$teachers);
            }
        }
        else{
            array_push($data,"No data");
        }
	echo json_encode($data);
				


?>