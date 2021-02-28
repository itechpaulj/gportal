<?php
require './gportal_android_database/database.php';
$pdo = Database::connect();
$response = array();

$cardid = $_GET["userid"];
$ayearlevel = $_GET["ayearlevel"];
$aysem = $_GET["aysem"];

$data = array();

	
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	$sql = "
			SELECT * from gradestbl 
			LEFT JOIN subjecttbl ON gradestbl.subjectid=subjecttbl.subjectid 
			RIGHT JOIN aytbl ON subjecttbl.ayid=aytbl.ayid
			INNER JOIN enrollcodetbl ON gradestbl.teacherscode=enrollcodetbl.teacherscode 
			where userid=(SELECT userid from usertbl where cardid=?) 
			AND (select status from teacherscodetbl where gradestbl.teacherscode=teacherscodetbl.teacherscode)='active'
			AND aytbl.ayearlevel=? 
			AND aytbl.aysem=?
			AND enrollcodetbl.cardid=?
			ORDER BY subjecttbl.subjecttitle 
		";
    $q = $pdo->prepare($sql);
    $q->execute(array($cardid, $ayearlevel,$aysem, $cardid));
	 $rowCount = $q->rowCount();
        if($rowCount>0){
            while($grades = $q->FETCH(PDO::FETCH_ASSOC)){
                array_push($data,$grades);
            }
        }
        else{
            array_push($data,"No data");
            array_push($data,$cardid);
            array_push($data,$ayearlevel);
            array_push($data,$aysem);
        }
	echo json_encode($data);
        

    

?>