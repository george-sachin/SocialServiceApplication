<?php 

	if($_SERVER['REQUEST_METHOD']=='GET'){
		
		$orgid  = $_GET['orgid'];
		
		require_once('dbConnect.php');
		
		$sql = "SELECT users.name, request.requestid,request.info FROM request, users where request.orgid ='".$orgid."' AND request.orgaccept='Pending' AND request.uid=users.unique_id";
		
		$r = mysqli_query($con,$sql);
		
		//$res = mysqli_fetch_array($r);
		
		$result = array();
		
	if($r->num_rows>0)
		{
		while($row=$r->fetch_assoc())
		{
		
		array_push($result,array(
 			
			"requestid"=>$row['requestid'],
                        "name"=>$row['name'],
						"info"=>$row['info']
			//"orgaddress"=>$res['orgaddress'],
			//"orgphone"=>$res['orgphone']	
			)
		);
		}}
		
		echo json_encode($result);
		
		mysqli_close($con);
		
	}
?>