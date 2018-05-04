<?php
class Flugzeug {
    private $ID = null;
    private $Bezeichnung = null;
    private $Flugstunden = null;
    private $Fluglinie = null;
    private $Wartung = null;
    
    function __construct($ID, $Bezeichnung, $Flugstunden, $Fluglinie, $Wartung) {
        $this->ID = $ID;
        $this->Bezeichnung = $Bezeichnung;
        $this->Fluglinie = $Fluglinie;
        $this->Flugstunden = $Flugstunden;
        $this->Wartung = $Wartung;
    }
       
    public function __get($property) {
        if (property_exists($this, $property)) {
            return $this->$property;
        }
    }	
}

?>