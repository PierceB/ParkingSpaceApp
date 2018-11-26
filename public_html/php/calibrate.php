<?php
/**
 * Created by PhpStorm.
 * User: szulu
 * Date: 2018/11/26
 * Time: 14:31
 */
include "config.php";
if (isset($_GET['current'])){
    $parkinglot = mysqli_real_escape_string($dbc, $_GET['current']);
    if (isset($_POST['coordinates'])){
        $coord1 = mysqli_real_escape_string($dbc, $_POST['coord1']);
        $coord2 = mysqli_real_escape_string($dbc, $_POST['coord2']);
        $coord3 = mysqli_real_escape_string($dbc, $_POST['coord3']);
        $coord4 = mysqli_real_escape_string($dbc, $_POST['coord4']);
        $parkinspace = mysqli_real_escape_string($dbc, $_POST['parkingspace']);

        $query = "update PARKING_SPACE set PARK_TOP_COORD1='$coord1', PARK_TOP_COORD2='$coord2', PARK_TOP_COORD3='$coord3', PARK_TOP_COORD4='$coord4' where PARK_ID='$parkinspace'";
        if ($result = mysqli_query($dbc, $query)){
            $message = 'Coordinates submitted successfully';
        } else{
            $message = 'Error: ' . mysqli_error($dbc);
        }

    }
}