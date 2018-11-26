<?php
/**
 * Created by PhpStorm.
 * User: szulu
 * Date: 2018/11/24
 * Time: 10:49
 */
include 'config.php';

if (isset($_GET['parkinglot']) && isset($_GET['action'])){

    $action = mysqli_real_escape_string($dbc, $_GET['action']);
    $parkinglot = mysqli_real_escape_string($dbc, $_GET['parkinglot']);

    if ($action == 'crop'){
        $query = "select * from IMAGES where LOT_ID = '$parkinglot' and IMAGE_CROPPED=0";
        $result = mysqli_query($dbc, $query);
        if ($row = mysqli_fetch_array($result)){
            echo $row['IMAGE_NAME'];
        }else{
            echo 'No Images';
        }
    }elseif ($action == 'classify'){
        $query = "select * from IMAGES where LOT_ID='$parkinglot' and IMAGE_CROPPED=1 and IMAGE_RECENT=1";
        $result = mysqli_query($dbc, $query);
        if ($row = mysqli_fetch_array($result)){
            echo $row['IMAGE_NAME'];
        }else{
            echo 'No Images';
        }
    }elseif ($action == 'calibrate'){
        $query = "select * from IMAGES where LOT_ID='$parkinglot' and IMAGE_TOPVIEW=1";
        $result = mysqli_query($dbc, $query);
        if ($row = mysqli_fetch_array($result)){
            echo $row['IMAGE_NAME'];
        }else{
            echo 'No Image';
        }
    }
}