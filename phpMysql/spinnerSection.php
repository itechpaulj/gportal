<?php
require './gportal_android_database/database.php';
$pdo = Database::connect();
$spinner = @$_GET["schoolcode"];
//echo $spinner;
$response = array();
$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
$sqlPostFrom = 'SELECT programid, programcode, CONCAT(programid," - ", programcode) AS progcodeid FROM programtbl WHERE schoolcode=?';
$sqlFrom = $pdo->prepare($sqlPostFrom);
    $sqlFrom->execute(array($spinner));
    $rowCount = $sqlFrom->rowCount();
    
    if($rowCount>0){
        $response["success"] = 1;
        $data = array();
        while($sectionid = $sqlFrom->FETCH(PDO::FETCH_ASSOC))
        {
            array_push($data,$sectionid);
        }
        $response["data"] = $data; 
        echo json_encode($response);
    }
    else{
        echo "Failed";
    }
//echo 'success';
?>