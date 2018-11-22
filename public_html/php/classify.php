<?php
/**
 * Created by PhpStorm.
 * User: szulu
 * Date: 2018/11/15
 * Time: 15:03
 */
include "config.php";
if (isset($_GET['current'])){
    $current = mysqli_real_escape_string($dbc, $_GET['current']);
    if (isset($_POST['classifybay'])) {
        $open = mysqli_real_escape_string($dbc, $_POST['open']);
        $parkingbay = mysqli_real_escape_string($dbc, $_POST['parkingspace']);
    }
    if (isset($_POST['update'])){
        $command = 'python3 /home/dione/ImageCropper/Update.py ' . $current . ' 2>&1';
        ob_start();
        passthru($command);
        $output = ob_get_clean();
        $outputtag = 'Output from Classifier:<br>' . $output;
    }

}