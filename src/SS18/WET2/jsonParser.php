<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>JsonParser</title>
  </head>
  <body>
    <div class="jumbotron">
  		<h1 class="display-4">JSON Parsing mit PHP</h1>
  	</div>
  	<table class="table">
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">Name</th>
              <th scope="col">Uhrzeit</th>
              <th scope="col">Blutdruck Disatolisch</th>
              <th scope="col">Blutdruck Systolisch</th>
              <th scope="col">Puls</th>
            </tr>
          </thead>
          <tbody>
<?php
    $jsonstring = file_get_contents("http://localhost:8080/rest/items/vital_data/history");
    $myarray = json_decode($jsonstring);
    
    //var_dump($myarray);
    foreach($myarray as $eintrag) {
        
        echo "<tr>";
            echo "<td>";
            echo $eintrag->id;
            echo "</td>";
            echo "<td>";
            echo $eintrag->name;
            echo "</td>";
            echo "<td>";
            echo $eintrag->timestamp;
            echo "</td>";
            echo "<td>";
            echo $eintrag->diastolic_pressure;
            echo "</td>";
            echo "<td>";
            echo $eintrag->systolic_pressure;
            echo "</td>";
            echo "<td>";
            echo $eintrag->heart_rate;
            echo "</td>";
        echo "</tr>";
    }
?>          
    
    
    

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  </body>
</html>
