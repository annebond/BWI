<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
     <script language="JavaScript">
 			function flugzeugclick(id)
 			{
 				alert(id + "gecklickt");
 			}
	</script> 
  </head>
  <body>
    <h1>Hello, world!</h1>
    
    
    
<?php
    //function opens a new connection to the MySQL server -> mysqli_connect(host,username,password,dbname,port,socket);
	$dbobjekt = new mysqli ("localhost","bwiuser","bwipasswort","bwi_bb_userverwaltung");
	
	$query = "SELECT * FROM users";
    $result = $dbobjekt->query($query);
    
    // return the number of rows in a result set
    $anzahl = $result->num_rows;
    echo "Anzahl der gefundenen Eintr�ge:".$anzahl;
	
    //	Fetches a result row as an associative array
	$zeile1 = $result->fetch_assoc();
	var_dump($zeile1);
	
	/* Fetches a result row as an associative, a numeric array, or both 
	 * mysqli_fetch_array(result,resulttype);
	 * resulttype: MYSQLI_ASSOC, MYSQLI_NUM, MYSQLI_BOTH */
	$zeile1 = $result->fetch_array();
	var_dump($zeile1);
	//Echo "User ID_test: ".$zeile1['user_id'];
	//Echo $zeile1[1];

	// 	Returns the current row of a result set, as an object
	$zeile1 = $result->fetch_object();
	var_dump($zeile1);
	
	
	while($zeile1 = $result->fetch_assoc()){
		Echo "User ID: ".$zeile1['user_id'];
	};
	

	//var_dump($result);
	
?>
    

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>