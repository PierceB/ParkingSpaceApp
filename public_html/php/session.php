<?php
/**
 * Created by PhpStorm.
 * User: szulu
 * Date: 2018/09/25
 * Time: 12:47
 */
    include('login.php');
    session_start();

    $user_check = $_SESSION['login_user'];

    $ses_sql = mysqli_query($dbc,"select ACCOUNT_USERNAME from ACCOUNTS where ACCOUNT_USERNAME = '$user_check' ");

    $row = mysqli_fetch_array($ses_sql,MYSQLI_ASSOC);

    $login_session = $row['username'];

    if(!isset($_SESSION['login_user'])){
        header("location:login.php");
    }
?>