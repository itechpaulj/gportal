<?php

require './gportal_android_database/database.php';

$pdo = Database::connect();

date_default_timezone_set('Asia/Manila');

//echo "success";



$schoolcode = @$_GET["schoolcode"];

$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

$sqlGetFrom = 'SELECT CONCAT(ayid," - ",ayear1," ", ayear2," ", IF(ayearlevel=1,"1st year", IF(ayearlevel=2,"2nd year", IF(ayearlevel=3,"3rd year", IF(ayearlevel=4,"4th year" ,"")))), " ", IF(aysem=1,"1st sem", IF(aysem=2,"2nd sem" ,"")) ) as ayear FROM aytbl WHERE schoolcode=? ';

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