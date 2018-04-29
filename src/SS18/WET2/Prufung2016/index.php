<?php

include 'model/Flugzeug.class.php';
include 'utility/DB.class.php';

?>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Web-DB-Modulpruefung BB 2016</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
	<!-- Custom CSS -->
	<link rel="stylesheet" href="res/css/flugzeugstyle.css">
	
	 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	
	 <script language="JavaScript">
 			function flugzeugclick(id)
 			{
 				//JS-Methode mit AJAX-POST-Request ...
				$.post('ajax/wartung.php', {'id': id})
				.done(function( data ) {
				    $('#wartung').html(data);
				  });
 			}
	</script> 
	
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  
	<div class="container">
	
		<header class="page-header">
			<h1>Wartung Flugzeuge</h1>
		</header>
		
		<div class="col-md-3">
			<nav>
				<?php
				include 'inc/navi.php';
				?>
			</nav>
		</div>
		
		<div class="col-md-6">
			<main>
				<?php
				include 'inc/flugzeuge.php';
				?>
			</main>
		</div>
		
		<div class="col-md-3">
			<div id="wartung">
				
			</div>
		</div>
		
		
		
	</div>
	
  </body>
</html>