<?php
	$host='localhost';
	$user='root';
	$password='';
	
	$connection = mysql_connect($host,$user,$password);
	


	$name = $_POST['name'];
	$address=$_POST['address'];
	$number=$_POST['number'];
	$email=$_POST['email'];
	$disAdd=$_POST['disAdd'];
	$disType=$_POST['disType'];
	$volNumber=$_POST['volNumber'];
	$level=$_POST['level'];
	$volDays=$_POST['volNumber'];
	$status='Active';
	$report='0';
	
	
	if(!$connection){
		die('Connection Failed');
	}
	else{
		$dbconnect = @mysql_select_db('Yomari', $connection);
		
		if(!$dbconnect){
			die('Could not connect to Database');
		}

		
		else{
			$query = "INSERT INTO `Yomari`.`Volunteer_Detail` (`Org_name`, `Num_Of_Volunteers`, `Emergency_Level` , `Disaster_Type` , `Org_Address` , `Org_ContactNumber` , `Disaster_Address` , `Org_Email`, `Num_Of_Reports`, `Status`)
				VALUES ('$name','$volNumber','$level','$disType','$address','$number','$disAdd','$email','$report', '$status');";
			mysql_query($query, $connection) or die(mysql_error());



			
			echo 'Successfully added to the database.';
		}
	}
?>

