<?php
/**
 * Created by PhpStorm.
 * User: szulu
 * Date: 2018/09/25
 * Time: 12:46
 */
   include('session.php');
?>
<html>

   <head>
      <title>Welcome </title>
   </head>

   <body>
      <h1>Welcome <?php echo $login_session; ?></h1>
      <h2><a href = "php/logout.php">Sign Out</a></h2>
   </body>

</html>