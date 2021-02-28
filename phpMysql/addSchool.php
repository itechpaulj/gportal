<?php

require './gportal_android_database/database.php';
$pdo = Database::connect();
date_default_timezone_set('Asia/Manila');
$date = date("Y-m-d");
$schoolname = @$_POST['schoolname'];
$schooladdress= @$_POST['schooladdress'];
$schoolemail = @$_POST['schoolemail'];
$schoolpassword = @$_POST['schoolpassword'];
$image = @$_POST['image'];
$random = @$_POST["random"];
$target_dir = 'upload/logoschool';

$message="";

// if(!file_exists($target_dir)){
//     mkdir($target_dir,0777,true);
// }

if(!empty($image && $schoolname && $schooladdress && $schoolemail && $schoolpassword && $random)){
	// find the email address if already exist
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sqladdschool = "SELECT * FROM usertbl WHERE cardid=?";
    $qaddschool = $pdo->prepare($sqladdschool);
    $qaddschool->execute(array($schoolemail));
    $gportaladdSchool = $qaddschool->fetch(PDO::FETCH_ASSOC);
        if($gportaladdSchool["cardid"]==$schoolemail){
            $message="Email address already exist!";
			//break;
        }else{
            $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $schoolcoded = bin2hex(random_bytes(5)); // note doble string
            //$target_dir = $target_dir."/".rand()."_".time().".jpeg";
            $target_dir = $random;
            file_put_contents($target_dir,base64_decode($image));
            $sql = "INSERT INTO usertbl(cardid,lname,access,userpassword,schoolcode,address,status,photo) VALUES (?,?,?,?,?,?,?,?)";
            $q = $pdo->prepare($sql);
            $q->execute(array($schoolemail,$schoolname,'School',$schoolpassword,$schoolcoded,$schooladdress,'active',$target_dir));
            
            $last_id = $pdo->lastInsertId();         
            $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $sql1 = "INSERT INTO schoolcodetbl(schoolcode,userid,date,status) VALUES (?,?,?,?)";
            $q1= $pdo->prepare($sql1);
            $q1->execute(array($schoolcoded,$last_id,$date,'active'));
			
            $message=$schoolcoded.'-'.$schoolemail;         
                
        }

}
else{
    $message="Missing data detected";
}

echo $message;

?>