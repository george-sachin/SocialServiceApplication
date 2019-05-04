<?php 

	if($_SERVER['REQUEST_METHOD']=='GET'){
		
		//$orgid  = $_GET['orgid'];
		
		require_once('dbConnect.php');
		
		$sql = "SELECT * FROM organisation where category like '%4%'";
		
		$r = mysqli_query($con,$sql);
		
		//$res = mysqli_fetch_array($r);
		
		$result = array();
		if($r->num_rows>0)
		{
		while($row=$r->fetch_assoc())
		{
		array_push($result,array(
 			"orgid"=>$row['orgid'],
			"orgname"=>$row['orgname'],
			"orgaddress"=>$row['orgaddress'],
			"orgphone"=>$row['orgphone'],
            "photo"=>$row['photo']			
			)
		);
		}
		}
		echo json_encode($result);
		
		mysqli_close($con);
		
	}
?>