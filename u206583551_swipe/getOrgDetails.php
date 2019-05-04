<?php 

	if($_SERVER['REQUEST_METHOD']=='GET'){
		
		$orgname = $_GET['orgname'];
		
		require_once('dbConnect.php');
		
		$sql = "SELECT * FROM organisation ,organisations where organisation.orgname ='".$orgname."' AND organisation.orgname=organisations.name";
		
		$r = mysqli_query($con,$sql);
		
		$res = mysqli_fetch_array($r);
		
		$result = array();
		
	
		
		array_push($result,array(
 			"email"=>$res['email'],
			"orgname"=>$res['orgname'],
			"orgaddress"=>$res['orgaddress'],
			"orgphone"=>$res['orgphone'],
"photo"=>$res['photo']			
			)
		);
		
		
		echo json_encode(array("result"=>$result));
		
		mysqli_close($con);
		
	}
?>