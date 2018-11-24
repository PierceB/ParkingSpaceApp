<?php
/**
 * Created by PhpStorm.
 * User: szulu
 * Date: 2018/11/24
 * Time: 10:07
 */
include "config.php";
if (isset($_GET['parkinglot'])){
    $parkinglot = mysqli_real_escape_string($dbc, $_GET['parkinglot']);
    $query = "select * from PARKING_SPACE where LOT_ID='$parkinglot'";
    $result = mysqli_query($dbc, $query);

    $coordinates = array();
    while ($row = mysqli_fetch_array($result)){
        $temp = array();
        $temp['coord1'] = $row['PARK_COORD1'];
        $temp['coord2'] = $row['PARK_COORD2'];
        $temp['coord3'] = $row['PARK_COORD3'];
        $temp['coord4'] = $row['PARK_COORD4'];
        $temp['is_open'] = $row['PARK_IS_OPEN'];
        $parkID = $row['PARK_ID'];
        $coordinates[$parkID] = $temp;
    }
    echo json_encode($coordinates, JSON_FORCE_OBJECT);
}