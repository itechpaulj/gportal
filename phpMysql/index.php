<?php
require './gportal_android_database/database.php';
$pdo = Database::connect();

@$user = $_POST['user'];
@$pass = $_POST['pass'];

if(!empty($user && $pass)){
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $sql = "SELECT * FROM sample WHERE username=? AND password = ?";
        $q = $pdo->prepare($sql);
        $q->execute(array($user, $pass));
        $gportalUser = $q->fetch(PDO::FETCH_ASSOC);
        //echo $gportalUser['username'].' '.$gportalUser['password'];
        if(@$gportalUser['username'] && @$gportalUser['password']){
            echo 'Welcome, ' .gportalUser['username'];
        }
        else{
            echo 'Invalid account';
        }
}
else{
    echo 'Not logged in';
}

?>