<?php 

	if($_SERVER['REQUEST_METHOD']=='GET'){
		
		$orgid  = $_GET['orgid'];
		
		require_once('dbConnect.php');
		
		$sql = "SELECT * FROM organisation where orgid ='".$orgid."'";
		
		$r = mysqli_query($con,$sql);
		
		$res = mysqli_fetch_array($r);
		
		$result = array();
		
	
		
		array_push($result,array(
 			//"orgid"=>$res['orgid'],
			"photo"=>$res['photo'],
			"orgaddress"=>$res['orgaddress'],
			"orgphone"=>$res['orgphone']	
			)
		);
		
		
		echo json_encode(array("result"=>$result));
		
		mysqli_close($con);
		
	}
?>