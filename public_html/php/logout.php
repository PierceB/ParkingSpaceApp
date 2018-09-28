<?php
/**
 * Created by PhpStorm.
 * User: szulu
 * Date: 2018/09/25
 * Time: 12:54
 */
   session_start();

   if(session_destroy()) {
       header("Location: login.php");
   }
?>