<?php
require './gportal_android_database/database.php';
$pdo = Database::connect();
$response = array();

@$spinner = $_POST["schoolcoded"];

       $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $sqlGetFrom = 'SELECT * FROM collegetbl WHERE schoolcode=?';
        $sqlFrom = $pdo->prepare($sqlGetFrom);

            $sqlFrom->execute(array($spinner));
            $rowCount = $sqlFrom->rowCount();
            if($rowCount>0){
                
                $response["success"] = 1;
                $data = array();
                while($rowFrom = $sqlFrom->FETCH(PDO::FETCH_ASSOC)){
                    array_push($data,$rowFrom);
                }
                $response["data"] = $data; 
                echo json_encode($response);
            }
            else{
                echo "FAILED";
                // $response["success"] = 0;
                // $response["message"] = "No data";
                // echo json_encode($response);
            }   
             
?>