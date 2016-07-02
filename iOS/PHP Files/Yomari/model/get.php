<?php
	$host='localhost';
	$user='root';
	$password='';
	
	$connection = mysql_connect($host,$user,$password);
	
	if(!$connection){
		die('Connection Failed');
	}
	else{
		$dbconnect = @mysql_select_db('Yomari', $connection);
		
		if(!$dbconnect){
			die('Could not connect to Database');
		}
		else{
			$query = 'SELECT * FROM Volunteer_Detail';

			$resultset = mysql_query($query, $connection);
			
			$records= array();

			
			while($r = mysql_fetch_assoc($resultset)){
				$records[] = $r;
			}
			
			echo json_encode($records);
		}
	}
?>
