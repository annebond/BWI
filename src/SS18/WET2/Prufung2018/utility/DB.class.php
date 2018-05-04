<?php

class DB{

/* 	private $host = "localhost";
        private $user = "";
	private $pwd = "";
        private $dbname = "";
	private $conn = null;
        private $flugzeugArray = array();
	
	function doConnect(){
		$this->conn = new mysqli($this->host,$this->user,$this->pwd, $this->dbname);
	}	 */	
	
	
	
	private $db = null;
	
	
	function doConnect() {
	    $this->db = new mysqli ("localhost","bwiuser","bwipasswort","bwi_ss18_pruef_flugzeug");
	}
	
	function auslesen($wartung) {
	    $arr = array();
	    $this->doConnect();
	    $query = "Select flugzeugid as fid,
						bezeichnung as ftbez,
                        fluglinie as fli,
                        flugstunden as flw,
                        wartungsstatus as flw
						from flugzeuge
                        where wartungsstatus = '$wartung'
                        order by flugstunden";
	    $result = $this->db->query($query);
	    //var_dump($result);
	    while ($flugzeug = $result->fetch_object()) {
	        $arr[] = $flugzeug;
	    }
	    return $arr;
	    
	}
	

}
        
/*
$test = new DB();
$war = 'vorgemerkt';
$ergebnis = $test->auslesen($war);
var_dump($ergebnis);
*/
?>