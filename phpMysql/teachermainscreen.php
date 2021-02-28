<?php

require './gportal_android_database/database.php';

$pdo = Database::connect();

date_default_timezone_set('Asia/Manila');



//echo "success";

//echo $_GET["cardid"];

$cardid = @$_GET["cardid"];



        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        $sqlteacher = "SELECT cardid,userid FROM usertbl WHERE cardid=?";

        $qteacher = $pdo->prepare($sqlteacher);

        $qteacher->execute(array($cardid));

        $cardidFound = $qteacher->fetch(PDO::FETCH_ASSOC);

        //echo $cardid["cardid"];        

        if($cardidFound["cardid"]==$cardid){

             //echo $cardidFound["cardid"];

            $sqlFrom = 'SELECT *, 

            (SELECT collegecode FROM collegetbl WHERE teacherscodetbl.collegeid=collegetbl.collegeid) AS collegecode, 

            (SELECT programcode FROM programtbl WHERE teacherscodetbl.programid=programtbl.programid) AS programcode, 

            (SELECT sectioncode FROM sectiontbl WHERE teacherscodetbl.sectionid=sectiontbl.sectionid) AS sectioncode, 

            (SELECT subjectcode FROM subjecttbl WHERE teacherscodetbl.subjectid=subjecttbl.subjectid) AS subjectcode,
           (SELECT CONCAT(lname,", ",fname," ",mname,".") as fullnane FROM usertbl WHERE teacherscodetbl.userid=usertbl.userid) 
AS fullname,
            (SELECT access FROM usertbl WHERE teacherscodetbl.userid=usertbl.userid) AS access,
            (SELECT photo FROM usertbl WHERE teacherscodetbl.userid=usertbl.userid) AS photo,
            (SELECT cardid FROM usertbl WHERE teacherscodetbl.userid=usertbl.userid) AS cardid,

            (SELECT 

                IF(ayearlevel=1,"1st",

                IF(ayearlevel=2,"2nd",

                IF(ayearlevel=3,"3rd",

                IF(ayearlevel=4,"4th",

                "")))) 

             AS ayearlevel FROM aytbl WHERE teacherscodetbl.ayid=aytbl.ayid)

                AS ayearlevel,

            (SELECT IF(aysem=1,"1st",

                       IF(aysem=2,"2nd",

                          ""))

            AS aysem FROM aytbl WHERE teacherscodetbl.ayid=aytbl.ayid) 

                AS aysem,

            (SELECT ayear1 FROM aytbl WHERE teacherscodetbl.ayid=aytbl.ayid) AS ayear1,

            (SELECT ayear2 FROM aytbl WHERE teacherscodetbl.ayid=aytbl.ayid) AS ayear2,

            (SELECT programname FROM programtbl WHERE teacherscodetbl.programid=programtbl.programid) AS programname,

            (SELECT collegename FROM collegetbl WHERE teacherscodetbl.collegeid=collegetbl.collegeid) AS collegename,

            (SELECT subjecttitle FROM subjecttbl WHERE teacherscodetbl.subjectid=subjecttbl.subjectid) AS subjecttitle

            FROM teacherscodetbl WHERE teacherscodetbl.userid=? AND teacherscodetbl.status="active" 
			ORDER BY subjecttitle';

            $sqlFrom = $pdo->prepare($sqlFrom);

            $sqlFrom->execute(array($cardidFound["userid"]));

            $rowCount = $sqlFrom->rowCount();

            if($rowCount>0){

                $data = array();

                while($rowFrom = $sqlFrom->FETCH(PDO::FETCH_ASSOC)){

                    array_push($data,$rowFrom);

                }

                echo json_encode($data);

            }



        }

         else{

             echo "FAILED";

        }



    // if(@$_GET["valid"]=="true"){

    //     $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    //     $sqlGetFrom = 'SELECT *, (SELECT subjectcode FROM subjecttbl WHERE teacherscodetbl.subjectid=subjecttbl.subjectid) as subjectcode, (SELECT sectioncode FROM sectiontbl WHERE teacherscodetbl.sectionid=sectiontbl.sectionid) as sectioncode, (SELECT collegecode from collegetbl where teacherscodetbl.collegeid=collegetbl.collegeid) as collegecode, (select programcode from programtbl where teacherscodetbl.programid=programtbl.programid) as programcode, (SELECT concat(ayear1, " - ", ayear2) as ay from aytbl where teacherscodetbl.ayid=aytbl.ayid) as ay FROM teacherscodetbl where teacherscode="te234ths" AND status="active"';

    //     $sqlFrom = $pdo->prepare($sqlGetFrom);



    //         $sqlFrom->execute();

    //         $rowCount = $sqlFrom->rowCount();

    //         if($rowCount>0){

    //             $data = array();

    //             while($rowFrom = $sqlFrom->FETCH(PDO::FETCH_ASSOC)){

    //                 array_push($data,$rowFrom);

    //             }

    //             echo json_encode($data);

    //         }

    // } 

    // else{

    //     echo "No data";

    // } 



?>