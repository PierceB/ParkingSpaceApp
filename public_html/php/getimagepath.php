<?php
/**
 * Created by PhpStorm.
 * User: szulu
 * Date: 2018/11/24
 * Time: 10:49
 */
include 'config.php';

if (isset($_GET['parkinglot'])){
    $parkinglot = mysqli_real_escape_string($dbc, $_GET['parkinglot']);
    $query = "select * from IMAGES where LOT_ID = '$parkinglot' and IMAGE_CROPPED=0";
    $result = mysqli_query($dbc, $query);
    if ($row = mysqli_fetch_array($result)){
        echo $row['IMAGE_NAME'];
    }else{
        echo 'No Images';
    }
}