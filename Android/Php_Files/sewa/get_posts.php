<?php
if ($_SERVER['REQUEST_METHOD']=='GET'){
	//Now connceting to the database
	require_once('db_connect.php');
	$sql="SELECT * FROM volunteer_detail where Status='Active' ORDER BY Id DESC ,'Id' ";
	$resultSet = mysqli_query($con, $sql);

//creating a new array to send the data from the database in a request
	$postArray = array();
	while ($row = mysqli_fetch_array($resultSet)){
		array_push($postArray,
			array(
				"post_id"=>$row['Id'],
				"org_name"=>$row['Org_Name'],
				"num_of_volunteers"=>$row['Num_Of_Volunteers'],
				"emergency_level"=>$row['Emergency_level'],
				"cause"=>$row['Disaster_Type'],
				"org_address"=>$row['Org_Address'],
				"org_contact"=>$row['Org_ContactNumber'],
				"disaster_location"=>$row['Disaster_Address'],
				"org_email"=>$row['Org_Email'],
				"num_of_reports"=>$row['Num_Of_Reports'],
				"status"=>$row['Status'],
				 )
			 );
			}				
		$serverRes=$postArray;
		//Echo the array in json format so that the data can be parsed by the application if its not null
		if ($serverRes!=null){
			echo json_encode($serverRes);
		}else{
			echo "No posts found";
		}		
}else{
	echo 'Error';
}

?>