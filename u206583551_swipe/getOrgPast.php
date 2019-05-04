<?php 

	if($_SERVER['REQUEST_METHOD']=='GET'){
		
		$orgid  = $_GET['orgid'];
		
		require_once('dbConnect.php');
		
		$sql = "SELECT users.name, request.info FROM request,users WHERE request.orgid='".$orgid."' AND users.unique_id=request.uid AND request.orgaccept='Accepted'";
		
		$r = mysqli_query($con,$sql);
		
		//$res = mysqli_fetch_array($r);
		
		$result = array();
		if($r->num_rows>0)
		{
		while($row=$r->fetch_assoc())
		{
		array_push($result,array(
 			"name"=>$row['name'],
			"info"=>$row['info']
			
			)
		);
		}
		}
		echo json_encode($result);
		
		mysqli_close($con);
		
	}
?>	