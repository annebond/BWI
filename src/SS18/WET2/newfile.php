<?php
$meinXML = simplexml_load_file("new.xml");
if (count($_POST)>0){
    //echo "hallo";
    //var_dump($_POST);

    $meinstudium = $meinXML->addchild("studium");
    $meinstudium->addAttribute("id",$_POST["ID"]);
        $meintitel = $meinstudium->addchild("titel",$_POST["Titel"]);
        $meintyp = $meinstudium->addchild("typ",$_POST["Typ"]);
        $meinerichtung = $meinstudium->addchild("richtung",$_POST["Richtung"]);
        $meinestudenten = $meinstudium->addchild("studenten",$_POST["Anzahl"]);
        $meinesemester = $meinstudium->addchild("semester",$_POST["Semester"]);
        $meineleitung = $meinstudium->addchild("leitung");
            $leitungN = $meineleitung->addchild("nachname",$_POST["LeitungN"]); 
            $leitungV = $meineleitung->addchild("vorname",$_POST["LeitungV"]);
    $meinXML->asXML("new.xml");
  

//     <studium id="1234">
//         <titel>BIC</titel>
//         <typ>Bachelor</typ>
//         <richtung>technik/wirtschaft</richtung>
//         <studenten>40</studenten>
//         <semester>6</semester>
//         <leitung>
//             <vorname>Martina</vorname>
//             <nachname>Musterfrau</nachname>
//         </leitung>
//     </studium>
}
?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Bootstrap 101 Template</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    
	<div class="page-header">
  		<h1>BWI WET2 XML Parsing </h1>
	</div>
    <div class="row">
  		<div class="col-md-7">
            <form method="post" action="./newfile.php">
				ID <input type ="text" name="ID" placeholder="ID"> </br>
				Titel <input type ="text" name="Titel" placeholder="Titel"></br>
				Typ 
                    <select name="Typ">
                      <option value="bachelor">Bachelor</option>
                      <option value="master">Master</option>
                    </select> </br>
				Richtung 
                    <select name="Richtung">
                      <option value="technik/wirtschaft">technik/wirtschaft</option>
                      <option value="master">Master</option>
                    </select> </br>    
				Anzahl Studenten <input type ="text" name="Anzahl" placeholder="Anzahl Studenten"> </br>                   
				Anzahl Semester <input type ="text" name="Semester" placeholder="Anzahl Semester"> </br>				
				Leitung Nachname <input type ="text" name="LeitungN" placeholder="Nachname"> </br>				
				Leitung Vorname <input type ="text" name="LeitungV" placeholder="Vorname"> </br>
				<input type ="submit" value="Speichern"> </br>									
            </form>  			
  		</div>
  		<div class="col-md-3">
  		<?php
            //var_dump($meinXML);
            $var = $meinXML->studium[0]->titel;
            foreach ($meinXML->studium as $element) {
                echo "<h2>";
                    echo $element->titel;
                echo "</h2>";
                echo $element->typ;
                echo "</br>";
                echo $element->richtung;
                echo "</br>";
                echo $element->studenten;
                echo "</br>";
                echo $element->semester;
                echo "</br>";
                echo $element->leitung->vorname;
                echo "</br>";
                echo $element->leitung->nachname;
                echo "</br>";  
            };
        ?>
        </div>
	</div>
    



    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
  </body>
</html>

