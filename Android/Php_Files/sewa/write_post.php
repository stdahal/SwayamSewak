<?php
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 	
	 $org_name = $_POST['org_name'];
	 $num_of_volunteers = $_POST['num_of_volunteers'];
	 $emergency_level = $_POST['emergency_level'];
	 $cause= $_POST['cause'];
	 $org_address = $_POST['org_address'];
	 $org_contact = $_POST['org_contact'];
	 $disaster_location= $_POST['disaster_location'];
	 $org_email = $_POST['org_email'];

	 // $org_name = "New Nepal";
	 // $num_of_volunteers = "6";
	 // $emergency_level = "High";
	 // $cause= "Flood";
	 // $org_address = "Sundhara, Kathmandu";
	 // $org_contact = "9841564940";
	 // $disaster_location= "Itahari";
	 // $org_email = "sushant.dahal14@gmail.com";

	 require_once('db_connect.php');
	 $sqlReset="ALTER TABLE volunteer_detail AUTO_INCREMENT =1";
	 mysqli_query($con,$sqlReset);
	 $sql = "INSERT INTO volunteer_detail (Org_Name, Num_Of_Volunteers, Emergency_Level, Disaster_Type, Org_Address, Org_ContactNumber, Disaster_Address, Org_Email, Num_Of_Reports, Status) VALUES ('$org_name','$num_of_volunteers','$emergency_level','$cause','$org_address','$org_contact','$disaster_location','$org_email','0','Active')";
	 
	 if(mysqli_query($con,$sql)){
		 echo "done";
	 }else{
	 	echo "Oops!!! Your Post Could Not Be Added.";
	 }	 
	 mysqli_close($con);
}else{
 	echo "Error";
}
 ?>