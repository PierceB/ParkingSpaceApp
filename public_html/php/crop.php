<?php
/**
 * Created by PhpStorm.
 * User: szulu
 * Date: 2018/10/17
 * Time: 12:25
 */
include "config.php";

if (isset($_GET['current']) && isset($_POST['coordinates'])){
    $parkingspace = mysqli_real_escape_string($dbc, $_POST['parkingspace']);
    $parkinglot = mysqli_real_escape_string($dbc, $_GET['current']);
    $coord1 = mysqli_real_escape_string($dbc, $_POST['coord1']);
    $coord2 = mysqli_real_escape_string($dbc, $_POST['coord2']);
    $coord3 = mysqli_real_escape_string($dbc, $_POST['coord3']);
    $coord4 = mysqli_real_escape_string($dbc, $_POST['coord4']);
    if ($parkingspace == "new"){
        $query = "select count(*) from PARKING_SPACE where LOT_ID='$parkinglot'";
        $result = mysqli_query($dbc, $query);
        if($row = mysqli_fetch_array($result)){
            $parkID = $parkinglot . $row['count(*)'];
            $query = "insert into PARKING_SPACE(PARK_ID, LOT_ID, PARK_COORD1, PARK_COORD3, PARK_COORD2, PARK_COORD4) values ('$parkID', '$parkinglot', '$coord1', '$coord2', '$coord3', '$coord4')";
            if($result = mysqli_query($dbc, $query)){
                $message = "Coordinates submitted";
            }else{
                $message = "Error: " . mysqli_error($dbc);
            }
        }
    }else{
        $query = "update PARKING_SPACE set PARK_COORD1='$coord1', PARK_COORD2='$coord2', PARK_COORD3='$coord3', PARK_COORD4='$coord4' where PARK_ID='$parkingspace'";
        if ($result = mysqli_query($dbc, $query)){
            $message = "Coordinates updated";
        }else{
            $message = "Error: " . mysqli_error($dbc);
        }
    }
}
if (isset($_GET['current']) && isset($_POST['generate'])){
    $current = mysqli_real_escape_string($dbc, $_GET['current']);
    date_default_timezone_set('Africa/Johannesburg');
    $filename = $current . '_' . date('Y-m-d_H:i:s');
    $command = 'python /home/dione/ImageCropper/Generate.py ' . $current . ' ' . $filename . ' 2>&1';
    ob_start();
    passthru($command);
    $output = ob_get_clean();
    $filenametag = 'Images saved to ' . $filename . ' on the server <br>';
    $outputtag = 'Output from Image Cropper:<br>' . $output;
}