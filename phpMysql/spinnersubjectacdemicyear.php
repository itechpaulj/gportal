<?php
//echo "success";
require './gportal_android_database/database.php';
$pdo = Database::connect();
$spinner = @$_GET["schoolcode"];
// echo $_GET["schoolcode"];
$response = array();
$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
$sqlPostFrom = 'SELECT CONCAT(ayid," - ", IF(ayearlevel="1", "1st yr", IF(ayearlevel="2", "2nd yr", IF(ayearlevel="3", "3rd yr", 
				IF(ayearlevel="4", "4th yr", "")))),  " ", 
				IF(aysem="1", "1st sem", IF(aysem="2", "2nd sem", "")), " ",
				ayear1,"-",ayear2) AS acyrid FROM aytbl WHERE schoolcode=?';
$sqlFrom = $pdo->prepare($sqlPostFrom);
    $sqlFrom->execute(array($spinner));
    $rowCount = $sqlFrom->rowCount();
    
    if($rowCount>0){
        $response["success"] = 1;
        $data = array();
        while($ayid = $sqlFrom->FETCH(PDO::FETCH_ASSOC))
        {
            array_push($data,$ayid);
        }
        $response["data"] = $data; 
        echo json_encode($response);
    }
    else{
        echo "An error occured";
    }
?>