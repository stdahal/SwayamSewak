<?php
 if($_SERVER['REQUEST_METHOD']=='POST'){
	 $post_id = $_POST['post_id'];
	// $post_id = "1";
	 require_once('db_connect.php');

	 $sqlSelect="SELECT num_of_reports, status from volunteer_detail WHERE post_id='$post_id'";
	 $result=mysqli_query($con, $sqlSelect);

	 $num_of_reports=0;
	 while ($row=mysqli_fetch_array($result)){
	 	$num_of_reports=$row['num_of_reports']+1;
	 }

	 if ($num_of_reports==0){
	 	echo "No records found for this id. Try again in a while";
	 }else if($num_of_reports==10){
	 	$sql = "UPDATE volunteer_detail SET num_of_reports='$num_of_reports', status='Deactive' WHERE post_id='$post_id'";
	 }else if($num_of_reports<10){
	 	$sql = "UPDATE volunteer_detail SET num_of_reports='$num_of_reports' WHERE post_id='$post_id'";
	 }else if($num_of_reports>10){
	 	$sql = "UPDATE volunteer_detail SET status='Deactive' WHERE post_id='$post_id'";
	 }
	 if(mysqli_query($con,$sql)){
		 echo "done";
	 }else{
	 	echo "There was a problem while processing your report. Try again in a while";
	 } 
	 mysqli_close($con);
}else{
 	echo "Error";
}
 ?>