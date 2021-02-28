<?php
require './gportal_android_database/database.php';
$pdo = Database::connect();


$subjectcode = @$_POST["subjectcode"];
$subjecttitle = @$_POST["subjecttitle"];
$programecode = @$_POST["programecode"];
$academicyear = @$_POST["academicyear"];
$schoolcode = @$_POST["schoolcode"];

// echo $subjectcode."<br>";
// echo $subjecttitle."<br>";
// echo $programecode."<br>";
// echo $academicyear."<br>";

if(!empty($subjectcode && $subjecttitle && $programecode && $academicyear )){
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "INSERT INTO subjecttbl(subjectcode,subjecttitle,programid,ayid,schoolcode) VALUES (?,?,?,?,?)";
    $q = $pdo->prepare($sql);
    if($q->execute(array($subjectcode,$subjecttitle,$programecode,$academicyear,$schoolcode)) ){
        echo "Success";
    }
    else{
        echo "Failed";
    }
}
?>