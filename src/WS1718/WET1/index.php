<?php
session_start();
include './inc/daten.inc.php';


if (!isset($_SESSION["gesamtpreis"])){
    $_SESSION["gesamtpreis"] = 0;
}

if (isset($_GET["preis"])){
    if (is_numeric($_GET["preis"])){
        $_SESSION["gesamtpreis"] += $_GET["preis"];
    };
};


?>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Title der Seite</title>
		<!-- Bootstrap CSS -->
		<!-- Achtung BETA Version, funktioniert mit 3.3.7 nicht -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
        <link rel="stylesheet" href="css/myStyle.css">
	</head>
	<body>
	<!-- Content der Seite -->
	
		<div class="container">
        	 <nav class="navbar navbar-expand-lg navbar-light bg-light">
       			<div class="collapse navbar-collapse" id="navbarSupportedContent">
        			<ul class="navbar-nav mr-auto">
    					<li class="nav-item">
            				<a class="nav-link active" href="index.php">Home</a>
          				</li>
          				<li class="nav-item">
            				<a class="nav-link" href="#">Warenkorb</a>
          				</li>
          				<li class="nav-item">
          					<?php 
          					echo "<button type='button' class='btn btn-danger' style = 'font-weight: bold'>" . $_SESSION["gesamtpreis"] . "&euro;" . "</button>";
          					?>
          				</li>
    				</ul>
        			<form class="form-inline my-2 my-lg-0" method="post" action="index.php">
        				<label for="suche"></label>
          				<input class="form-control mr-sm-2" name="suche" id="suche" type="search" placeholder="ProduktID" aria-label="Search">
          				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Suche</button>
        			</form>
      			</div>
    		</nav>                     
    	</div>	
		<div class="container">
            <div class="jumbotron">
                <h1>Smart Home Online Store</h1>
            </div>
            <?php
                include './inc/produkte.inc.php';
            ?>
            <?php
                //include './inc/produkte.inc.php';
                //var_dump($_POST['suche']);
//                 if (isset($_POST["suche"])){
//                     foreach ($produkte as $produkt) {
//                         if ($produkt["ID"] == $_POST["suche"]) {
//                             echo "<div class='produkt col-md-4'>";
//                             echo "<h3>";
//                             echo $produkt["Name"];
//                             echo "</h3>";
//                             echo "<img src=\"pics/" . $produkt['ID'] . ".jpg\"/>";
//                             echo "<div class='produktbeschreibung'>";
//                             echo $produkt['Desc'];
//                             echo "</div>";
//                             echo "<div>";
//                                 switch ($produkt["Sterne"]) {
//                                     case $produkt["Sterne"] == 4;
//                                     echo "****";
//                                     break;
//                                     case $produkt["Sterne"] == 3;
//                                     echo "***";
//                                     break;
//                                     case $produkt["Sterne"] == 2;
//                                     echo "**";
//                                     break;
//                                 };
//                             echo "</div>";
//                             echo "<div class='produktpreis'>";
//                             echo "&euro; " . $produkt["Preis"];
//                             echo "</div>";
//                             echo "<div class='InDenWarenkorb' >";
//                             echo "<a class=\"btn btn-primary\" href=\"index.php?preis=" . $produkt['Preis'] . "\" role=\"button\">In den Warenkorb</a>";
//                             echo "</div>";
                            
//                             break;
//                         }
//                         //else {
//                           //  echo "<div class='alert alert-danger' role='alert'>";
//                             //    echo "ProduktID: " . $_POST["suche"] . " nicht vorhanden";
//                               //  break;
//                             //echo "</div>";
//                         //};
//                     };
//                 }
//                 else {
//                     include './inc/produkte.inc.php';
//                 };
            ?>
        </div>
        
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
	</body>
</html>