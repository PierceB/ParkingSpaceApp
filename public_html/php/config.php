<?php
//Not sure on DB_HOST
//

// Opens a connection to the database 
// It should be saved outside of the main web documents folder
// and imported when needed

/*
The account used by this method needs permissions to SELECT, UPDATE , INSERT, and possibly delete 
User credentials: 
Username : connect
Password : connectpw

*/

// Defined as constants so that they can't be changed
DEFINE ('DB_USER', 'connect');
DEFINE ('DB_PASSWORD', 'connectpw');
DEFINE ('DB_HOST', 'dione.ms.wits.ac.za');
DEFINE ('DB_NAME', 'APSAPP_DB');

// $dbc will contain a resource link to the database
// @ keeps the error from showing in the browser

$dbc = @mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME)   //try open the connection to the database
OR die('Could not connect to MySQL: ' .                          //incase it fails
mysqli_connect_error());
?>
