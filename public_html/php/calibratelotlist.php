<?php
/**
 * Created by PhpStorm.
 * User: szulu
 * Date: 2018/11/26
 * Time: 17:28
 */
include ("config.php");
$query = "select * from PARKING_LOTS";
$result = mysqli_query($dbc, $query);
$current = "";

if (isset($_GET['current'])){
    $current = mysqli_real_escape_string($dbc, $_GET['current']);
}

while ($row = mysqli_fetch_array($result)){
    $parkinglot = $row['LOT_ID'];
    if($current == $parkinglot){
        $tag = '<li><a href="/calibrate/index.html?current='.$parkinglot.'">' . $parkinglot . ' (current)</a></li>';
    }else{
        $tag = '<li><a href="/calibrate/index.html?current='.$parkinglot.'">' . $parkinglot . '</a></li>';
    }

    echo $tag;

}