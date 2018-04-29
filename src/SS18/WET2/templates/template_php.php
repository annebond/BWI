<?php

//XML
$meinXML = simpleXML_load_file('Pfad/zur/Datei.xml');
$meinXML->student[1]->vorname;

foreach ($meinXML->student as $element){
    //Ausgabe des Vornamens jedes Elements
    echo $element->vorname;
}
foreach (array_expression as $key => $value) {
    // in dieser form wird zusätzlich der schlüssel des elementes in $key gespeichert
}

//änderungen in XML-DAtei speichern, umkehrfunktion von simpleXML
$meinXML->asXML('xmldatei.xml');


//DB
$db = new mysqli('hostname','username','pwd','dbname');
$db = new mysqli ("localhost","bwiuser","bwipasswort","bwi_bb_userverwaltung");
$db = mysqli_connect("Hostname","User","Passwort");

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

// KLASSEN mit Konstruktor
class Auto {
    public $farbe = "";
    
    function __construct(){
        $this->farbe = "rot";
    }
    
    public function fahren(){
    }
    //konstruktor
    
    //konstruktor mit parametern (achtung kein überladen in PHP!)
    /*
     function __construct($farbe){
     $this->farbe = $farbe;
     }
     */
}

//vererbung
class Mazda extends Auto {
    public function fahren(){
        parent::fahren();
        //oder
        Auto::fahren();
        echo "Ich fahre";
    }
}

$myCar = new Auto();
echo $myCar->farbe;

$myCar2 = new Mazda();
$myCar2->fahren();
?>