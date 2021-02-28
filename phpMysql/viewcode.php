<?php 
require './gportal_android_database/database.php';

$pdo = Database::connect();

date_default_timezone_set('Asia/Manila');

$date = date("Y-m-d");

$viewcode = @$_GET['viewcode'];

$data = array();


	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $sql = "select cardid,viewcode from usertbl where viewcode=?";

    $q = $pdo->prepare($sql);

    $q->execute(array($viewcode));

	$rowCount = $q->rowCount();

        if($rowCount>0){
          $viewcode = $q->FETCH(PDO::FETCH_ASSOC);
          //array_push($data,$viewcode); 
		  echo $viewcode["cardid"];
        }

        else{
			echo "View code not found!";
           // array_push($data,"No data");

        }

	//echo json_encode($data);

				





?>