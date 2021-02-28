<?php 
//echo "success";
require './gportal_android_database/database.php';
$pdo = Database::connect();
date_default_timezone_set('Asia/Manila');

$cardid = @$_GET["cardid"];
//echo $cardid;

$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
$sqlGetFrom = 'SELECT * FROM usertbl WHERE cardid=?';
$sqlFrom = $pdo->prepare($sqlGetFrom);

    $sqlFrom->execute(array($cardid));
    $rowCount = $sqlFrom->rowCount();
    if($rowCount>0){
        $data = array();
        while($rowFrom = $sqlFrom->FETCH(PDO::FETCH_ASSOC)){
            array_push($data,$rowFrom);
        }
        echo json_encode($data);
    }
    else{
        echo "FAILED";
        // $response["success"] = 0;
        // $response["message"] = "No data";
        // echo json_encode($response);
    } 
?>