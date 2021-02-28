<?php
require './gportal_android_database/database.php';
$pdo = Database::connect();

    $user = @$_POST["user"];
    $pass = @$_POST["pass"];

    //echo 'success';

    //first find the {access} in usertbl
    // then get given value such as {access=="Teacher"} something like that
    // the $_POST user for the input compare {$_POST["user"]==user["cardid"]}
    // then get the echo or print result to transfer other pages
    
    if(!empty($user && $pass)){
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $sql = "SELECT * FROM usertbl WHERE cardid=? AND userpassword = ?";
        $q = $pdo->prepare($sql);
        $q->execute(array($user, $pass));
		$rowCount = $q->rowCount();
        if($rowCount>0){
			@$gportalLoggedin = $q->fetch(PDO::FETCH_ASSOC);

			if(@$gportalLoggedin["access"] == "School" && @$gportalLoggedin["cardid"] == $user ){
				echo 'School-'.$gportalLoggedin['schoolcode'];
			}   
			elseif(@$gportalLoggedin["access"] == "Teacher" && @$gportalLoggedin["cardid"] == $user){
				echo 'Teacher-'.$gportalLoggedin['schoolcode'];
			}
			elseif(@$gportalLoggedin["access"] == "Student" && @$gportalLoggedin["cardid"] == $user){
				echo 'Student';
			}
			else{
				echo 'Invalid credentials';
			}
		}else{
			echo 'Invalid credentials';
		}
        
    }
    else{
        echo 'Please supply email and password';
    }

?>