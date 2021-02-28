<?php
require './gportal_android_database/database.php';
$pdo = Database::connect();
date_default_timezone_set('Asia/Manila');

//echo "success";
$teacherscodeid = @$_GET["teacherscodeid"];
// echo $teacherscodeid." success";

$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
$sqlteacher = "UPDATE teacherscodetbl SET status=? WHERE teacherscodeid=?";
$qteacher = $pdo->prepare($sqlteacher);
$qteacher->execute(array("deactivate",$teacherscodeid));
$cardidFound_userid = $qteacher->fetch(PDO::FETCH_ASSOC);

echo "Teacherscode has been deleted";
// echo $cardidFound_userid["teacherscodeid"];
//if($cardidFound_userid["teacherscodeid"]){	//echo "Success";//}//else{//	echo "Failed";//}




?>