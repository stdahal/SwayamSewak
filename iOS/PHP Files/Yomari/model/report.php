<?php
	$host='localhost';
	$user='root';
	$password='';
	
	$connection = mysql_connect($host,$user,$password);
	


	
	$id = "1";
	
	if(!$connection){
		die('Connection Failed');
	}
	else{
		$dbconnect = @mysql_select_db('Yomari', $connection);
		
		if(!$dbconnect){
			die('Could not connect to Database');
		}

		
		else{
				$query="SELECT Num_Of_Reports, status from Volunteer_Detail where  Id='$id'";
				$result=mysql_query($query, $connection) or die(mysql_error());

				$Num_Of_Reports=0;
						while ($row=mysql_fetch_array($result)){
					 	$Num_Of_Reports=$row['Num_Of_Reports']+1;
					 }

					 if ($Num_Of_Reports==0){
					 	echo "No records found for this id. Try again in a while";
					 }else if($Num_Of_Reports==10){
					 	$sql = "UPDATE Volunteer_Detail SET Num_Of_Reports='$Num_Of_Reports', status='Deactive' WHERE Id='$id'";
					 }else if($Num_Of_Reports<10){
					 	$sql = "UPDATE Volunteer_Detail SET Num_Of_Reports='$Num_Of_Reports' WHERE Id='$id'";
					 }else if($Num_Of_Reports>10){
					 	$sql = "UPDATE Volunteer_Detail SET status='Deactive' WHERE Id='$id'";
					 }
					 if(mysql_query($sql,$connection)){
						 echo "done";
					 }else{
					 	echo "There was a problem while processing your report. Try again in a while";
					 } 
					 mysql_close($connection);
						}
	}
?>

