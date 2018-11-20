<?php
/**
 * Created by PhpStorm.
 * User: szulu
 * Date: 2018/10/10
 * Time: 21:47
 */

    include "config.php";
    //$lotID = ; get name of parking lot in question
if (isset($_GET['current'])){
    $current = mysqli_real_escape_string($dbc, $_GET['current']);
    $sql = "select * from PARKING_LOTS where LOT_ID='$current'";
    $query = mysqli_query($dbc, $sql);

    if($row = mysqli_fetch_array($query)){
        $lotName = $row['LOT_NAME'];
        $lotLocation = $row['LOT_LOCATION'];
        $access = "";
        if($row['LOT_ALL']){
            $access = "All";
        }else {
            if ($row['LOT_FIRST_YEAR'] && $row['LOT_SECOND_YEAR'] && $row['LOT_THIRD_YEAR'] && $row['LOT_FOURTH_YEAR']){
                $access = "Undergraduates";
            } else{
                if ($row['LOT_FIRST_YEAR']) {
                    $access = $access . "First years";
                }
                if ($row['LOT_SECOND_YEAR']) {
                    if ($access == "") {
                        $access = "Second years";
                    } else {
                        $access = $access . ", Second years";
                    }

                }
                if ($row['LOT_THIRD_YEAR']) {
                    if ($access == "") {
                        $access = "Third years";
                    }else{
                        $access = $access . ", Third years";
                    }
                }
                if ($row['LOT_FOURTH_YEAR']) {
                    if ($access == ""){
                        $access = "Fourth years";
                    }else{
                        $access = $access . ", Fourth years";
                    }
                }
            }

            if ($row['LOT_POST_GRAD']){
                if ($access == ""){
                    $access = "Postgraduates";
                }else{
                    $access = $access . ", Postgraduates";
                }
            }

            if ($row['LOT_PART_TIME']){
                if ($access == ""){
                    $access = "Part-time students";
                }else{
                    $access = $access . ", Part-time students";
                }
            }

            if ($row['LOT_STAFF']){
                if ($access == ""){
                    $access = "Staff";
                }else{
                    $access = $access . ", Staff";
                }
            }

            if ($row['LOT_VISITORS']){
                if ($access == ""){
                    $access = "Visitors";
                }else{
                    $access = $access . ", Visitors";
                }
            }
        }
        $heading = '<h1>' . $lotName . '</h1>';
        $location = '<p>Location: ' . $lotLocation . '</p>';
        $acc = '<p>Access: ' . $access . '</p>';
        echo $heading;
        echo $location;
        echo $acc;
    }
}else{
    $message = '<h1>Parking lots</h1>
        <p class=\"lead\">Please pick any of the parking lots on the sidebar to the right.</p>';
    echo $message;
}