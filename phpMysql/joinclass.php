<?php
require './gportal_android_database/database.php';
$pdo = Database::connect();
date_default_timezone_set('Asia/Manila');

$date = date("Y-m-d");
$cardid = $_GET["userid"];
$teacherscode = $_GET["teacherscode"];
$subjectid="";
$subjecttitle="";
$userid="";
$grade="0.00";
$note="No note found";
	
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	$sql = "SELECT *, 
	(select subjecttitle from subjecttbl where subjecttbl.subjectid=teacherscodetbl.subjectid) as subjecttitle
	FROM teacherscodetbl where teacherscode=?";
    $q = $pdo->prepare($sql);
    $q->execute(array($teacherscode));
	$teacherscodetbl = $q->fetch(PDO::FETCH_ASSOC);
	$subjectid=$teacherscodetbl['subjectid'];
	$subjecttitle=$teacherscodetbl['subjecttitle'];
	 $rowCount = $q->rowCount();
		if($rowCount>0){
			$sql = "SELECT * FROM enrollcodetbl where cardid=? AND teacherscode=?";
			$q = $pdo->prepare($sql);
			$q->execute(array($cardid, $teacherscode));
			 $rowCount = $q->rowCount();
				if($rowCount>0){
					echo "You were already enrolled.";	
				}else{
					$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
					$sql = "INSERT INTO enrollcodetbl(cardid, teacherscode, date, status) VALUES (?,?,?,?)";
					$q = $pdo->prepare($sql);
					$isSuccess = $q->execute(array($cardid, $teacherscode, $date, "active"));

					if($isSuccess){						
						$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
						
						$sql = "SELECT userid FROM usertbl where cardid=?";
						$q = $pdo->prepare($sql);
						$q->execute(array($cardid));
						$usertbl = $q->fetch(PDO::FETCH_ASSOC);
						$userid=$usertbl['userid'];
						
						$sql = "INSERT INTO gradestbl(subjectid, userid, grade, date, note, teacherscode) VALUES (?,?,?,?,?, ?)";
						$q = $pdo->prepare($sql);
						$isSuccess = $q->execute(array($subjectid, $userid, $grade, $date, $note, $teacherscode));
							if($isSuccess){
								echo  "You have joined in " .$subjecttitle;	
							}else{
								echo "An error occured while initiating grade";
							}
							
					}else{
						echo "An error occured while enrolling to class";
					}	
				}
		}else{
			echo "Teacherscode not found!";
		}
        
  

?>