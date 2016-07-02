<?php
require_once("config.php");
//error handling
function customError($errno, $errstr){
	echo "<center><h1>Database Error Established </h1><br/>";
	echo "</center>";
	//echo $errno;
	exit();
}
set_error_handler("customError",E_USER_WARNING);
set_error_handler("customError",E_WARNING);

mysql_connect(DB_SERVER,DB_USERNAME,DB_PASSWORD);
if(!mysql_select_db("Yomari")){
	trigger_error("",E_USER_WARNING);
}
	

?>