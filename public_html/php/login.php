<?php
    /**
     * Created by PhpStorm.
     * User: szulu
     * Date: 2018/09/25
     * Time: 09:40
     */
    include ("config.php");
    session_start();

    if($_SERVER["REQUEST_METHOD"] == "POST"){
        //fetch values entered in form
        $username = mysqli_real_escape_string($dbc, $_POST['login_username']);
        $password = mysqli_real_escape_string($dbc, $_POST['login_password']);

        //search for the account linked to the username
        $sql = "select * from ACCOUNTS where ACCOUNT_USERNAME='$username'";
        $result = mysqli_query($dbc, $sql);
        $row = mysqli_fetch_array($result, MYSQLI_ASSOC);
        $active = $row['active'];

        //counts number of rows
        $count = mysqli_num_rows($result);
        //if account exists then there should be only one row
        if ($count == 1){
            if(mysqli_close($dbc)) {
                //establish connection using user's details
                $dbc = @mysqli_connect(DB_HOST, $username, $password, DB_NAME) OR die('Could not connect to MySQL: ' .//incase it fails
                    mysqli_connect_error());
                if($dbc->ping()){
                    $_SESSION['login_user'] = $username;
                    header("location: ./dashboard/index.html");
                }else{
                    header("location: index.html");
                }
            }
        } else{
            $error = "Your username or password is invalid";
            echo "login failed";
            header("location: index.html");
        }
    }
?>

