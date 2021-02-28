<?php
require './gportal_android_database/database.php';
$pdo = Database::connect();
date_default_timezone_set('Asia/Manila');

//$name = $_POST["name"];
$cardid  = $_POST["cardid"];
$password = $_POST["password"];

// echo $name;
// echo $cardid;


if(!empty($cardid && $password)){
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $sql = "SELECT * FROM usertbl WHERE cardid=? AND userpassword=?";

    $q = $pdo->prepare($sql);

    $q->execute(array($cardid,$password));
    @$login2 = $q->FETCH(PDO::FETCH_ASSOC);

    if(@$login2["cardid"]==$cardid && $login2["userpassword"]==$password && @$login2["access"]=="School" ){
        echo 'School';
    }	
    elseif(@$login2["cardid"]==$cardid && $login2["userpassword"]==$password && @$login2["access"]=="Teacher"){		
        echo "Teacher";	
    }	
    elseif(@$login2["cardid"]==$cardid && $login2["userpassword"]==$password && @$login2["access"]=="Student"){		
        echo "Student";	
    }
    else{
        echo 'Failed';
    }
}
else{
    echo 'Please input your password!';
}





 




?>