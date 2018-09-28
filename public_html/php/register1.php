<?php
/**
 * Created by PhpStorm.
 * User: szulu
 * Date: 2018/09/28
 * Time: 10:20
 */
    include ("login.php");
    include ("session.php");
    //Form required validation
    foreach ($_POST as $key=>$value){
        if (empty($_POST[$key])){
            $error_msg = "All fields required";
            break;
        }
    }

    //Password matching validation
    if ($_POST['reg_password' != $_POST['reg_confirm-password']]){

    }