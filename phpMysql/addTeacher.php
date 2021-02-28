<?php
// echo "success";
// echo "<br>".@$_POST["fname"];
// echo "<br>".@$_POST["lname"];
// echo "<br>".@$_POST["mname"];
// echo "<br>".@$_POST["empTeacher"];
// echo "<br>".@$_POST["spinner"];
// echo "<br>".@$_POST["schoolcode"];
// echo "<br>".@$_POST["image"];
// echo "<br>".@$_POST["gender"];

require './gportal_android_database/database.php';
$pdo = Database::connect();


$fname = @$_POST["fname"];
$lname = @$_POST["lname"];
$mname = @$_POST["mname"];
$empTeacher = @$_POST["empTeacher"];
$spinner = @$_POST["spinner"];
$image = @$_POST['image'];
$schoolcoded = @$_POST["schoolcode"];
$gender = @$_POST["gender"];
//$address = @$_POST["address"];
$password = @$_POST["password"];
$random = @$_POST["random"];
$target_dir = 'upload/profileteacher';
     if(!empty($fname && $lname && $empTeacher && $spinner && $image && $schoolcoded && $gender && $password && $random ))
     {
         //echo $spinner;

        if($spinner=="[College Code]"){
            echo "Please Select College Code";
        }
        else{
            $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $sql = "SELECT * FROM collegetbl WHERE collegecode=? AND schoolcode = ?";
            $q = $pdo->prepare($sql);
            $q->execute(array($spinner, $schoolcoded));
            $gportalTeacher = $q->fetch(PDO::FETCH_ASSOC);
            $gteacherid = $gportalTeacher["collegeid"]; // college id - {collegename}
            //echo $gportalTeacher["collegeid"];
            //$target_dir = $target_dir."/".rand()."_".time().".jpeg";
			$target_dir = $random;
            //exist email
            $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $sqladdteacher = "SELECT * FROM usertbl WHERE cardid=?";
            $qaddteacher = $pdo->prepare($sqladdteacher);
            $qaddteacher->execute(array($empTeacher));
            @$gportaladdTeacher = $qaddteacher->fetch(PDO::FETCH_ASSOC);
            if(@$gportaladdTeacher["cardid"]==$empTeacher){
                echo 'Employee ID already exist!';
            }
            else{
                $sql = "INSERT INTO usertbl(cardid,fname,lname,mname,gender,access,userpassword,collegeid,schoolcode,status,photo) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                $q = $pdo->prepare($sql);

                if($q->execute(array($empTeacher,$fname,$lname,$mname,$gender,'Teacher',$password,$gteacherid,$schoolcoded,'active',$target_dir)))
                {
                    file_put_contents($target_dir,base64_decode($image));
                    //echo 'Success';
					$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

                    $sql = "SELECT * FROM usertbl WHERE cardid=?";

                    $q = $pdo->prepare($sql);

                    $q->execute(array($empTeacher));
                    @$login2 = $q->FETCH(PDO::FETCH_ASSOC);
                    echo "Teacher".'-'.$schoolcoded;
		
                }
                else{
                    echo 'An error occured';
                }
            }
                
        }
     }
     else{
         echo "Please Fill in the form";
     }

?>