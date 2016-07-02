<?php
	define ('HOSTNAME','localhost');
	define ('USERNAME','root');
	define ('PASSWORD','');
	define ('DB_NAME','Yomari');

	$con = mysqli_connect(HOSTNAME, USERNAME, PASSWORD, DB_NAME) or die ('Unable to make a connection to the database');

	//For Real Device
	$SERVER_IP="http://192.168.204.50/sewa/";
?>