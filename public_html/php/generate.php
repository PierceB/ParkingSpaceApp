
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
    //$parkinglot = 'wits_msl';
    //chmod('/home/dione/ParkingSpaceApp/ImageCropper', 0777);
    $command = 'python3 /home/dione/ImageCropper/Generate.py ' . $parkinglot . ' ' . $filename. ' 2>&1';//. $parkinglot;
    ob_start();
    passthru($command);
    $output = ob_get_clean();
    $zipname =   $filename . ".zip";
    $outputtag = 'Output from Image Cropper:<br>' . $output. ' <br> ' . $command;

    $query = "select * from IMAGES where LOT_ID = '$parkinglot' and IMAGE_CROPPED=0";
    $result = mysqli_query($dbc, $query);
    if ($row = mysqli_fetch_array($result)){
        $path = $row['IMAGE_NAME'];
    }else{
        $path = 'No Images';
    }

    if (file_exists($zipname)) {
//echo "File exists";
        $query = "update IMAGES set IMAGE_CROPPED=1 where LOT_ID = '$parkinglot' and IMAGE_NAME='$path'";
        $result = mysqli_query($dbc, $query);
        $temp = basename($zipname) ;
        $message = 'File exists';
        header('Content-Description: File Transfer');
        header("Content-Type: application/zip");
        header('Content-Disposition: attachment; filename='.$temp);
        header('Expires: 0');
        header('Cache-Control: must-revalidate');
        header('Pragma: public');
        header('Content-Length: ' . filesize($zipname));
        ob_clean();
        flush();
        readfile($zipname);
        $message = 'Files generated';
    } else {
        $message = 'Failed to generate files';
    }
}
?>
