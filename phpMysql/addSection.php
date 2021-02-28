<?php
//echo 'success';
require './gportal_android_database/database.php';
$pdo = Database::connect();
date_default_timezone_set('Asia/Manila');

$programid = @$_GET["programid"];
$inputSection = @$_GET["inputSection"];
$schoolCode = @$_GET["schoolcode"];

// echo $programid."<br>";
// echo $inputSection."<br>";
// echo $schoolCode."<br>";

if(!empty($programid && $inputSection && $schoolCode  )){
    $sql = "INSERT INTO `sectiontbl`(`sectioncode`, `programid`, `schoolcode`) VALUES (?,?,?)";
    $q = $pdo->prepare($sql);
    if( $q->execute(array($inputSection,$programid,$schoolCode)) )
    {
        echo "Success";
    }
}    else{        echo "Please Input Section";    }


?>