<?php
error_reporting(0);
include("dbConnect.php");

// array for JSON response
$response = array();

if( !(empty($_POST['orgid']))){
    $orgid=$_POST['orgid'];
	$orgname=$_POST['orgname'];
	$orgphone=$_POST['orgphone'];
	$orgaddress=$_POST['orgaddress'];
	$category=$_POST['category'];
	
	$sql = "SELECT * FROM organisation where orgid='$orgid'";
		
		$r = mysqli_query($con,$sql);
		if($r->num_rows>0)
		{$result=mysqli_query($con,"UPDATE organisation SET orgaddress='$orgaddress',orgphone='$orgphone',category='$category' WHERE orgid='$orgid'");}
	else
    {$result = mysqli_query($con,"INSERT INTO organisation(orgid,orgname,orgaddress,orgphone,category) VALUES('$orgid','$orgname','$orgaddress','$orgphone','$category')"); }   

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

