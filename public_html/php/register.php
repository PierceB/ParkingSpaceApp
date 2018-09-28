<?php
//Register Accounts
//On the php form the button which calls this php file must be called register (if its not rename the line below to whatever its called).
    include("session.php");

	if(isset($_POST['register_submit'])){
		$username = $_POST['reg_username'];
		$password = $_POST['reg_password'];
		$email = $_POST['reg_email'];
		$re_password = $_POST['reg_confirm-password'];
		$fname = $_POST['reg_fname'];
		$surname =$_POST['reg_surname'];

		if ($password == $re_password){
			$sql = "select ACCOUNT_USERNAME from ACCOUNTS where ACCOUNT_USERNAME = $username or ACCOUNT_EMAIL = $email";
			$result = mysqli_query($dbc, $sql);
            $row = mysqli_fetch_array($result, MYSQLI_ASSOC);

            $count = mysqli_num_rows($result);
            if($count > 0){
				echo '<script language="javascript">';
				echo 'alert("Username or email already exists")';
				echo '</script>';
            }else{
                $sql = "create user '$username'@'%' identified by '$password'";
                $result = mysqli_query($dbc, $sql);
                if ($result){
                	$sql = "grant all privileges on APSAPP_DB.* to '$username'@'%' with grant privileges";
                	$result = mysqli_query($dbc, $sql);
                	if($result){
                		$sql = "insert into ACCOUNTS (ACCOUNT_USERNAME, ACCOUNT_EMAIL, ACCOUNT_FNAME, ACCOUNT_SURNAME) value ('$username', '$email', '$fname', '$surname')";
                		$result = mysqli_query($dbc, $sql);
                		$sql = "select * from ACCOUNTS where ACCOUNT_USERNAME = $username";
                		$result = mysqli_query($dbc, $sql);
                		$count = mysqli_num_rows($result);
                		if($count == 1){
                            echo '<script language="javascript">';
                            echo 'alert("Account successfully created")';
                            echo '</script>';
                            header("location: ");
                        }
					}
				} else{
                    echo '<script language="javascript">';
                    echo 'alert("Could not create user account")';
                    echo '</script>';
				}
            }
		}
	}
?> 


