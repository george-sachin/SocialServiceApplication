<?php

	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		$image = $_POST['image'];
                $orgid = $_POST['orgid'];
		
		require_once('dbConnect.php');
		
		$sql ="SELECT orgid FROM organisation WHERE orgid='$orgid'";
		
		$res = mysqli_query($con,$sql);
		
		$id = 0;
		
		while($row = mysqli_fetch_array($res)){
		$id = $row['orgid'];}
		
		
		$path = "uploads/$orgid.png";
		
		$actualpath = "http://192.168.10.100/u206583551_swipe/$path";
		
		$sql = "UPDATE organisation SET photo='$actualpath' WHERE orgid='$orgid'";
		
		if(mysqli_query($con,$sql)){
			file_put_contents($path,base64_decode($image));
			echo "Successfully Uploaded";
		}
		
		mysqli_close($con);
	}else{
		echo "Error";
	}