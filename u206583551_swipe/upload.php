<?php
error_reporting(0);
include("dbConnect.php");

// array for JSON response
$response = array();

if( !(empty($_POST['orgname'])))
{

 
 $image = $_POST['image'];
$orgname=$_POST['orgname'];
 
  
 $sql ="SELECT orgid FROM Organisation where Orgname like '$orgname';";
 
 $res = mysqli_query($con,$sql);
 
 $id = 0;
 
 while($row = mysqli_fetch_array($res)){
 $id = $row['orgid'];
 }
 
 $path = "uploads/$id.png";
 
 $actualpath = "http://192.168.10.100/u206583551_swipe/$path";
 
 $sql = "INSERT INTO images (orgid,image,orgname) VALUES ('$id','$actualpath','$orgname');";
 
 if(mysqli_query($con,$sql)){
 file_put_contents($path,base64_decode($image));
 $response["success"] = 1;
 }
 
 mysqli_close($con);
 }else{
 $response["success"] = 0;
 }