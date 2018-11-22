<?php
/**
 * Created by PhpStorm.
 * User: szulu
 * Date: 2018/11/08
 * Time: 12:59
 */
include "config.php";

if (isset($_GET['current']) && isset($_POST['generate'])) {
    $date = date("_d_m_y_h:i:s:A");
    $parkinglot = $_GET['current'];
    $message=$parkinglot;
    $filename = $parkinglot . $date;
    //chmod('/home/dione/ParkingSpaceApp/ImageCropper', 0777);
    $command = 'python3 /home/dione/ImageCropper/Generate.py ' . $parkinglot . ' ' . $filename;//. $parkinglot;
    ob_start();
    passthru($command);
    $output = ob_get_clean();
    $zipname = "/home/dione/public_html/php/" . $filename . ".zip";
    $outputtag = 'Output from Image Cropper:<br>' . $output;

    if (file_exists($zipname)) {
//echo "File exists";
        $message = 'File exists';
        header('Content-Description: File Transfer');
        header("Content-Type: application/zip");
        header('Content-Disposition: attachment; filename="' . basename($zipname) . '"');
        header('Expires: 0');
        header('Cache-Control: must-revalidate');
        header('Pragma: public');
        header('Content-Length: ' . filesize($zipname));
        readfile($zipname);
    } else {
       // $message = 'Failed to generate files';

    }
}

