<?php

class DB{

	private $user = "USER";
	private $pwd = "PASSWORD";
	private $host = "infdb.technikum-wien.at:1521/o10";
	private $conn = null;
	
	
	function doConnect(){
		$this->conn = oci_connect($this->user,$this->pwd,$this->host);
	}	
	
	
	
	//benötigtes SQL-Statement:
	/*$query = "Select flugzeugid as fid,
						flugstunden_letzte_wartung as flw,
						flugzeugtyp.bezeichnung as ftbez,
						fluglinie.bezeichnung as flbez
						from flugzeug
						join flugzeugtyp using(flugzeugtypid)
						join fluglinie using(fluglinieid)";
	*/
	
}

?>