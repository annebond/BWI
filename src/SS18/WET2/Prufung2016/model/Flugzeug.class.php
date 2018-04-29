<?php
class Flugzeug {
    private $ID = null;
    private $Bezeichnung = null;
    private $Flugstunden = null;
    private $Fluglinie = null;
    
    function __construct($ID, $Bezeichnung, $Flugstunden, $Fluglinie) {
        $this->ID = $ID;
        $this->Bezeichnung = $Bezeichnung;
        $this->Fluglinie = $Fluglinie;
        $this->Flugstunden = $Flugstunden;
    }
    
    function __set($name, $value) {
        $this->$name = $value; 
    }
}

/*
$test = new Flugzeug(10, 'Bezeichnung', 20, 'Linie');
$test->ID = 30;
$test->Bezeichnung = "Bezeichnung2";
var_dump($test);
*/
?>

