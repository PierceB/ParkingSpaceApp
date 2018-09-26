<?php
//Register Accounts
//On the php form the button which calls this php file must be called register (if its not rename the line below to whatever its called).

if(isset($_POST['register'])){

	$data_missing = array() ; 
	
//Check all the data fields that are required have values

	//Check email and trim it if it exists
	if(empty($_POST['ACCOUNT_EMAIL'])){
		
		//Add email to the list of missing elements.
		$data_missing[]= 'Email address';	
	
	} else { 
		//trim any whitespaces
		$email = trim($_POST['ACCOUNT_EMAIL']) ;

	}

	//Check isadmin and trim it if it exists
	if(empty($_POST['isadmin'])){
		
		//Add email to the list of missing elements.
		$data_missing[]= 'isadmin';	
	
	} else { 
		//trim any whitespaces
		$isadmin = trim($_POST['isadmin']) ;

	}

	//Check first name and trim it if it exists
	if(empty($_POST['FNAME'])){
		
		//Add email to the list of missing elements.
		$data_missing[]= 'First Name';	
	
	} else { 
		//trim any whitespaces
		$fname = trim($_POST['FNAME']) ;

	}

	//Check Surname and trim it if it exists
	if(empty($_POST['SURNAME'])){
		
		//Add email to the list of missing elements.
		$data_missing[]= 'Surname';	
	
	} else { 
		//trim any whitespaces
		$surname = trim($_POST['SURNAME']) ;

	}


	//Check if any data fields are missing and if not commit
	if(empty($data_missing)){
		//connect using the config.php file
		require_once('../config.php');

	//Build the query
		$query = "INSERT INTO ACCOUNTS (ACCOUNT_ID, 			ACCOUNT_EMAIL, ACCOUNT_IS_ADMIN, ACCOUNT_FNAME, 		ACCOUNT_SURNAME) VALUES (NULL,?, ?, ?, ?)";
	
		$stmt  = mysqli_prepare($dbc, $query);

//bind the variables to the statement to be pushed tot he database

		mysqli_stmt_bind_param($stnt, "dsdss",$email,$isadmin,$fname,$surname) ; 

		$affected_rows = mysqli_stmt_affected_rows($stmt) ;

		if($affected_rows == 1) { 
	//if it successfully updates the database
			echo 'Account registered';

			mysqli_stmt_close($stmt);

			mysqli_close($dbc);

		} else { 
		//If it fails to update the database, print the error.
			echo 'Failed to add account' <br /> ; 
			echo mysqli_error();

			mysqli_stmt_close($stmt);
		
			mysqli_close($dbc); 

		}
	} else { 
		//echo which fields are missing
		echo 'You are missing data, enter the following data <br />'; 

		foreach($data_missing as $missing){

			echo "$missing<br />" 

		}
	}
}

?> 


