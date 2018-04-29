<?php

class DB{

	//private $user = "USER";
	//private $pwd = "PASSWORD";
	//private $host = "infdb.technikum-wien.at:1521/o10";
	//private $conn = null;
	
    //holds db connection
    private $db = null;
	
	
	function doConnect() {
		//$this->conn = oci_connect($this->user,$this->pwd,$this->host);
	    $this->db = new mysqli ("localhost","bwiuser","bwipasswort","bwi_bb_userverwaltung");
	}	
	
	function auslesen() {
	    $arr = array();
	    $this->doConnect();
	    $query = "Select flugzeugid as fid,
						flugstunden_letzte_wartung as flw,
						flugzeugtyp.bezeichnung as ftbez,
						fluglinie.bezeichnung as flbez
						from flugzeug
						join flugzeugtyp using(flugzeugtypid)
						join fluglinie using(fluglinieid)";
	    $result = $this->db->query($query);
	    while ($flugzeug = $result->fetch_object()) {
	        $arr[] = $flugzeug;
	    }
	    return $arr;
	    
	    //return ;
	}
	
}

/*
$test = new DB();
$ergebnis = $test->auslesen();
var_dump($ergebnis);
*/
?>