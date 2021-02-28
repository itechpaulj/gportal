<?php 

require './gportal_android_database/database.php';
$pdo = Database::connect();
date_default_timezone_set('Asia/Manila');
$date = date("Y-m-d");
$schoolcode=@$_GET['schoolcode'];


//get number of colleges 
//select count(collegecode) as numofcolleges from collegetbl where schoolcode="66527c19b9"
	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "select count(collegecode) as numofcolleges from collegetbl where schoolcode=?";
    $q = $pdo->prepare($sql);
    $q->execute(array($schoolcode));
    $numofcolleges = $q->fetch(PDO::FETCH_ASSOC);

//get number of active teachers
//select count(cardid) as numofteachers from usertbl where schoolcode="66527c19b9" AND access="Teacher"AND status="active"
	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "select count(cardid) as numofteachers from usertbl where schoolcode=? AND access=? AND status=?";
    $q = $pdo->prepare($sql);
    $q->execute(array($schoolcode, "Teacher", "active"));
    $numofteachers = $q->fetch(PDO::FETCH_ASSOC);

//get number of students
//select count(cardid) as numofstudents from usertbl where schoolcode="66527c19b9" AND access="Student"AND status="active";
	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "select count(cardid) as numofstudents from usertbl where schoolcode=? AND access=? AND status=?";
    $q = $pdo->prepare($sql);
    $q->execute(array($schoolcode, "Student", "active"));
    $numofstudents = $q->fetch(PDO::FETCH_ASSOC);
	
//number of programs
//select count(programcode) as numofprograms from programtbl where schoolcode="66527c19b9";
	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "select count(programcode) as numofprograms from programtbl where schoolcode=?";
    $q = $pdo->prepare($sql);
    $q->execute(array($schoolcode));
    $numofprograms = $q->fetch(PDO::FETCH_ASSOC);

//number of subjects
//select count(subjectcode) as numofsubjects from subjecttbl where schoolcode="66527c19b9";
	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "select count(subjectcode) as numofsubjects from subjecttbl where schoolcode=?";
    $q = $pdo->prepare($sql);
    $q->execute(array($schoolcode));
    $numofsubjects = $q->fetch(PDO::FETCH_ASSOC);

//number of sections
//select count(sectioncode) as numofsections from sectiontbl where schoolcode="66527c19b9";
	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "select count(sectioncode) as numofsections from sectiontbl where schoolcode=?";
    $q = $pdo->prepare($sql);
    $q->execute(array($schoolcode));
    $numofsections = $q->fetch(PDO::FETCH_ASSOC);

//number of AY
//select count(ayid) as numofay from aytbl where schoolcode="66527c19b9";
	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "select count(ayid) as numofay from aytbl where schoolcode=?";
    $q = $pdo->prepare($sql);
    $q->execute(array($schoolcode));
    $numofay = $q->fetch(PDO::FETCH_ASSOC);
	
	// $data=array("numofcolleges"=>"'.$numofcolleges.'", "numofteachers"=>$numofteachers,
				// "numofstudents"=>$numofstudents, "numofprograms"=>$numofprograms,
				// "numofsubjects"=>$numofsubjects, "numofsections"=>$numofsections,
				// "numofay"=>$numofay);
	$data=array($numofcolleges, $numofteachers, $numofstudents, $numofprograms, $numofsubjects, $numofsections, $numofay);
	echo json_encode($data);
				


?>