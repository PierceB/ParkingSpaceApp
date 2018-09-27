<?php
/**
 * Created by PhpStorm.
 * User: szulu
 * Date: 2018/09/25
 * Time: 09:40
 */
    include ("config.php");
    session_start();
	$echo("dfsdsfsd sdf sdf");
    if($_SERVER["REQUEST_METHOD"] == "POST"){
        $username = mysqli_real_escape_string($dbc, $_POST['login_username']);
        $password = mysqli_real_escape_string($dbc, $_POST['login_password']);

        $sql = "select ACCOUNT_ID from ACCOUNTS where ACCOUNT_USERNAME='$username'";
        $result = mysqli_query($dbc, $sql);
        $row = mysqli_fetch_array($result, MYSQLI_ASSOC);
        $active = $row['active'];

        $count = mysqli_num_rows($result);

        if ($count == 1){
            mysqli_close($dbc);
            $dbc = @mysqli_connect(DB_NAME, $username, $password, DB_NAME) OR die("Incorrect password");
            session_register($username);

        }
    }
?>
