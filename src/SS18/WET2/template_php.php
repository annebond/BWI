<?php
$db = new mysqli('hostname','username','pwd','dbname');
$db = new mysqli ("localhost","bwiuser","bwipasswort","bwi_bb_userverwaltung");
$db = mysqli_connect(„Hostname“,“User“,“Passwort“);

// mysqli_connect_errno()

$query = "SELECT * FROM users";
$result = $db->query($query); // oder: mysqli_query(„QUERY“);
//	Fetches a result row as an associative array
$zeile1 = $result->fetch_assoc();


while($z= $result->fetch_object()){
    echo $z->Vorname;
}


$sql = 'INSERT INTO `tabelle`
 (`id`,`kategorie`,`produktname`,`preis`)
 VALUES (?, ?, ?, ?)';
$eintrag->bind_param('issd', 27,'Werkzeug','Schraubenzieher','39.99' );


$sql = 'INSERT INTO `tabelle` (`vorname`, `nachname`) VALUES (?, ?)';
$eintrag = $db->prepare($sql);
// Variablen an die Anweisung binden
$eintrag->bind_param('ss',$vorname,$nachname);
// mehrere Einträge hintereinander durch einfaches Aufrufen von execute
// Erster Eintrag
$vorname = 'Max'; $nachname = 'Mustermann'; $eintrag->execute();
// Zweiter Eintrag
$vorname = 'Fritz'; $nachname = 'Fischer'; $eintrag->execute(); 


$db->close();
?>