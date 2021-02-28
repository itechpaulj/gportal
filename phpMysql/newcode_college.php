<?php
require './gportal_android_database/database.php';
$pdo = Database::connect();
date_default_timezone_set('Asia/Manila');

$schoolcode = @$_GET["schoolcode"];
//echo $schoolcode;
$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
$sqlGetFrom = 'SELECT CONCAT(collegeid," - ",collegecode) as co FROM `collegetbl` WHERE schoolcode=? ORDER BY collegename ASC';
$sqlFrom = $pdo->prepare($sqlGetFrom);
    $sqlFrom->execute(array($schoolcode));
    $rowCount = $sqlFrom->rowCount();
    if($rowCount>0){
        $data = array();
        while($rowFrom = $sqlFrom->FETCH(PDO::FETCH_ASSOC)){
            array_push($data,$rowFrom);
        }
        echo json_encode($data);
    }
?>