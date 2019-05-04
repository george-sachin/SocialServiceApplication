<?php
error_reporting(0);
include("dbConnect.php");

// array for JSON response
$response = array();

if( !(empty($_POST['requestid'])))
{
    $requestid=$_POST['requestid'];
	$orgaccept=$_POST['orgaccept'];
    $result = mysqli_query($con,"UPDATE request SET orgaccept='$orgaccept' WHERE requestid='$requestid'");    

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


