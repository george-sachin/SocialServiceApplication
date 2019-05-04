<?php 

	if($_SERVER['REQUEST_METHOD']=='GET'){
		
		$uid  = $_GET['uid'];
		
		require_once('dbConnect.php');
		
		$sql = "SELECT * FROM request,organisation WHERE uid='".$uid."' AND organisation.orgid=request.orgid ";
		
		$r = mysqli_query($con,$sql);
		
		//$res = mysqli_fetch_array($r);
		
		$result = array();
		if($r->num_rows>0)
		{
		while($row=$r->fetch_assoc())
		{
		array_push($result,array(
 			"orgname"=>$row['orgname'],
			"orgaccept"=>$row['orgaccept']
			
			)
		);
		}
		}
		echo json_encode($result);
		
		mysqli_close($con);
		
	}
?>	