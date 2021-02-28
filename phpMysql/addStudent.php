<?php
// echo "success";
// echo "<br>".@$_POST["fname"];
// echo "<br>".@$_POST["lname"];
// echo "<br>".@$_POST["mname"];
// echo "<br>".@$_POST["schoolid"];
//echo "<br>".@$_POST["image"];
// echo "<br>".@$_POST["schoolcoded"];
// echo "<br>".@$_POST["gender"];
// echo "<br>".@$_POST["password"];

// echo "<br>".@$_POST["collegecode"];
// echo "<br>".@$_POST["programcode"];
// echo "<br>".@$_POST["sectioncode"];


require './gportal_android_database/database.php';
$pdo = Database::connect();

$length = 4;    
$viewcode = substr(str_shuffle('0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ'),1,$length);

$fname = @$_POST["fname"];
$lname = @$_POST["lname"];
$mname = @$_POST["mname"];
$schoolid = @$_POST["schoolid"];
$image = @$_POST['image'];
$schoolcoded = @$_POST["schoolcoded"];
$gender = @$_POST["gender"];
$password = $_POST["password"];
$random = @$_POST["random"];


$collegecode = @$_POST["collegecode"];
$programcode = @$_POST["programcode"];
$sectioncode = @$_POST["sectioncode"];

$target_dir = 'upload/profilestudent';
     if(!empty($fname && $lname && $mname && $schoolid && $image && $schoolcoded && $gender && $password && $collegecode && $programcode && $sectioncode && $random))
     {
         //echo $spinner;
        if($collegecode==="[College Code]"){
            echo "Please Select College Code";
        }
        elseif($programcode==="[Program Code]"){
            echo "Please Select Program Code";
        }
        elseif($sectioncode==="[Section Code]"){
            echo "Please Select Section Code";
        }
        else{
            //college tbl
            $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $sqlctbl = "SELECT * FROM collegetbl WHERE collegecode=? AND schoolcode = ?";
            $qctbl = $pdo->prepare($sqlctbl);
            $qctbl->execute(array($collegecode, $schoolcoded));
            $gportalstudentctbl = $qctbl->fetch(PDO::FETCH_ASSOC);
            $gstudentidctbl = $gportalstudentctbl["collegeid"]; // college id - {collegename}
            //echo $gstudentidctbl."<br>";
            //echo $schoolcoded;
            //college tbl

            //---

            // //program tbl
            $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $sqlptbl = "SELECT * FROM programtbl WHERE programcode=? AND schoolcode = ?";
            $qptbl = $pdo->prepare($sqlptbl);
            $qptbl->execute(array($programcode, $schoolcoded));
            $gportalstudentptbl = $qptbl->fetch(PDO::FETCH_ASSOC);
            $gstudentidptbl = $gportalstudentptbl["programid"]; // program id - {programname}
            //echo $gstudentidptbl;
            // //program tbl

            // //---
            // //section tbl
            $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $sqlstbl = "SELECT * FROM sectiontbl WHERE sectioncode=? AND schoolcode = ?";
            $qstbl = $pdo->prepare($sqlstbl);
            $qstbl->execute(array($sectioncode, $schoolcoded));
            $gportalstudentstbl = $qstbl->fetch(PDO::FETCH_ASSOC);
            $gstudentidstbl = $gportalstudentstbl["sectionid"]; // section id - {sectionname}
           // echo $gstudentidstbl;
            // //section tbl

            
            $target_dir = $random;

            //exist email
            $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $sqladdstudent = "SELECT * FROM usertbl WHERE cardid=?";
            $qaddstudent = $pdo->prepare($sqladdstudent);
            $qaddstudent->execute(array($schoolid));
            $gportaladdstudent = $qaddstudent->fetch(PDO::FETCH_ASSOC);
            //echo $gportaladdstudent["cardid"];

            if(@$gportaladdstudent["cardid"]==$schoolid){
                echo 'School ID has been already taken!';
            }
            else{
                $sql = "INSERT INTO usertbl(cardid,fname,lname,mname,gender,access,userpassword,collegeid,programid,sectionid,schoolcode,status,photo,viewcode) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?, ?)";
                $q = $pdo->prepare($sql);
                $q->execute(array($schoolid,$fname,$lname,$mname,$gender,'Student',$password,$gstudentidctbl,$gstudentidptbl,$gstudentidstbl,$schoolcoded,'active',$target_dir, 'ST'.$viewcode)); 
                    file_put_contents($target_dir,base64_decode($image));
                    echo $target_dir;
            }

       }
     }else{
        echo "Please supply all the fields";
    }

?>