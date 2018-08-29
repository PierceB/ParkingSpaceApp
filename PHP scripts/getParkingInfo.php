<?php
//Returns the entire parking_lot info database.


require_once('../connect.php'); //connect to db

$query = "SELECT * from PARKING_LOT" ; //build query
$result = mysqli_query($dbc,$query);  // run query

$rows = $result->fetch_all(MYSQLI_ASSOC); //fetch all rows
header('Content-Type: application/json'); 
echo json_encode($rows);             //encode php array as json
mysqli_close($dbc);		     //close the connection	

?>	
