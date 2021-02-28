<?php 

require './gportal_android_database/database.php';
$pdo = Database::connect();
date_default_timezone_set('Asia/Manila');
$date = date("Y-m-d");
$schoolcode=@$_GET['schoolcode'];

 
//**** where schoolcode="66527c19b9"
	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "select ayid, ayear1, ayear2, IF(ayearlevel=1, '1st year', IF(ayearlevel=2, '2nd year', IF(ayearlevel=3, '3rd year', IF(ayearlevel=4, '4th year', '')))) as ayearlevel, IF(aysem=1, '1st sem', IF(aysem=2, '2nd sem', '')) as aysem
			from aytbl where schoolcode=? ORDER BY ayear1 DESC";
    $q = $pdo->prepare($sql);
    $q->execute(array($schoolcode));
	 $rowCount = $q->rowCount();
        if($rowCount>0){
            $data = array();
            while($ays = $q->FETCH(PDO::FETCH_ASSOC)){
                array_push($data,$ays);
            }
        }
        else{
            array_push($data,"No data");
        }
	echo json_encode($data);
				


?>