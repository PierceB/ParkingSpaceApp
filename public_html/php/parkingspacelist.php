<?php
/**
 * Created by PhpStorm.
 * User: szulu
 * Date: 2018/11/02
 * Time: 11:12
 */
include "config.php";
if (isset($_GET['current'])){
    $current = $_GET['current'];
    $query = "select * from PARKING_SPACE where LOT_ID='$current'";
    $result = mysqli_query($dbc, $query);
    while ($row = mysqli_fetch_array($result)){
        $option = '<option value="' . $row['PARK_ID'] . '">' . $row['PARK_ID'] . '</option>';
        echo $option;
    }
}