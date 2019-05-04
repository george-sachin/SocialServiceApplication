<?php
include("dbConnect.php");
$result = array();
$orgname=$_POST['orgname'];
$url  = array();
$sql = "select * from images where orgname like '$orgname';";
$res = mysqli_query($con,$sql);
while($row = mysqli_fetch_array($res)){
$id=$row['orgid'];


$url = http://192.168.0.108/u206583551_swipe/uploads/$id.png"; 

array_push($result,array("url"=>$url));
}
echo json_encode(array("result"=>$result));
mysqli_close($con);
?>