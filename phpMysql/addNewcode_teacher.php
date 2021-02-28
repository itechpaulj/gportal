<?php
require './gportal_android_database/database.php';
$pdo = Database::connect();
date_default_timezone_set('Asia/Manila');


$length = 4;    
$random = substr(str_shuffle('0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ'),1,$length);

$date = date("Y-m-d");
$status = "active";

// getFirstIndex_spinnerAy, getFirstIndex_spinnerCollege, getFirstIndex_spinnerProgram, getFirstIndex_spinnerSection, getFirstIndex_spinnerSubjects

$ay = @$_POST["getFirstIndex_spinnerAy"];
$collegeCode = @$_POST["getFirstIndex_spinnerCollege"];
$programCode = @$_POST["getFirstIndex_spinnerProgram"];
$sectionCode = @$_POST["getFirstIndex_spinnerSection"];
$subjectCode= @$_POST["getFirstIndex_spinnerSubjects"];
$getTeachercardid = @$_POST["getTeachercardid"];


$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
$sqlteacher = "SELECT cardid,userid FROM usertbl WHERE cardid=?";
$qteacher = $pdo->prepare($sqlteacher);
$qteacher->execute(array($getTeachercardid));
$cardidFound = $qteacher->fetch(PDO::FETCH_ASSOC);

// echo 'TE'.$random."<br>";
// echo $ay."<br>";
// echo $collegeCode."<br>";
// echo $programCode."<br>";
// echo $sectionCode."<br>";
// echo $subjectCode."<br>";
// echo $date."<br>";
// echo $status."<br>";
// echo $getTeachercardid."<br>";
// echo $cardidFound["userid"]."<br>";

 if(!empty($ay && $collegeCode && $programCode && $sectionCode && $subjectCode )){
	 
	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "select * from teacherscodetbl where ayid=? AND collegeid=? AND programid=? AND sectionid=? AND subjectid=? AND userid=?";
    $q = $pdo->prepare($sql);
    $q->execute(array($ay, $collegeCode, $programCode, $sectionCode, $subjectCode, $cardidFound["userid"] ));
	$rowCount = $q->rowCount();
        if($rowCount>0){
				$teacherscode = $q->FETCH(PDO::FETCH_ASSOC);
				if($teacherscode['status']=='deactivate'){
					$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
					$sqlteacher = "UPDATE teacherscodetbl SET status=? WHERE teacherscodeid=?";
					$qteacher = $pdo->prepare($sqlteacher);
					$qteacher->execute(array("active",$teacherscode['teacherscodeid']));
					echo "Success";
				}else{
					echo "Code has been generated already";
				}
				
        }
        else{
				$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
				$sql = "INSERT INTO teacherscodetbl(teacherscode,collegeid,programid,sectionid,subjectid,ayid,userid,date,status) VALUES (?,?,?,?,?,?,?,?,?)";
				$q = $pdo->prepare($sql);
				if($q->execute(array('TE'.$random,$collegeCode,$programCode,$sectionCode,$subjectCode,$ay,$cardidFound["userid"],$date,$status)) ){
					echo "Success";
				}
				else{
					echo "An error occured while generating code";
				}
        }
  
}else{
    echo "Please Select an item in every option";
}

?>