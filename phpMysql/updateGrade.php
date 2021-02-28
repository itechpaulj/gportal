<?php
require './gportal_android_database/database.php';
$pdo = Database::connect();
date_default_timezone_set('Asia/Manila');

$gradeid = @$_GET["gradeid"];
$grade = @$_GET["grade"];
$note = @$_GET["note"];

$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
$sqlteacher = "UPDATE gradestbl SET grade=?, note=? WHERE gradeid=?";
$qteacher = $pdo->prepare($sqlteacher);
$qteacher->execute(array($grade, $note, $gradeid));

echo "Changes has been saved";
// echo $cardidFound_userid["teacherscodeid"];
//if($cardidFound_userid["teacherscodeid"]){	//echo "Success";//}//else{//	echo "Failed";//}




?>