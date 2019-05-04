<?php
error_reporting(0);
include("dbConnect.php");

// array for JSON response
$response = array();

if( !(empty($_POST['orgid'])))
{
    $orgid=$_POST['orgid'];
	$uid=$_POST['uid'];
	$info=$_POST['info'];
    $result = mysqli_query($con,"INSERT INTO request(requestid,uid,orgid,orgaccept,info) VALUES('','$uid','$orgid','Pending','$info')");    

    if($result>0){
           $response["success"] = 1;
         }    
     else{
           $response["success"] = 0;
         }
     // echoing JSON response
     echo json_encode($response);
}

?>


