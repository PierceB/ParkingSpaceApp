<?php
/**
 * Created by PhpStorm.
 * User: szulu
 * Date: 2018/11/06
 * Time: 09:04
 */
include "config.php";
if ($_GET['parkingbay']){
    $bay = mysqli_real_escape_string($dbc, $_GET['parkingbay']);
    $query = "select * from PARKING_SPACE where PARK_ID='$bay'";
    $result = mysqli_query($dbc, $query);
    if ($row = mysqli_fetch_array($result)){
        $coordinates = array();
        $coordinates['coord1'] = $row['PARK_COORD1'];
        $coordinates['coord2'] = $row['PARK_COORD2'];
        $coordinates['coord3'] = $row['PARK_COORD3'];
        $coordinates['coord4'] = $row['PARK_COORD4'];
    }
    $json = json_encode($coordinates, JSON_FORCE_OBJECT);
    echo $json;
}