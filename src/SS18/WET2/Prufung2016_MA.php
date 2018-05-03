========= WARTUNG ==============
<?php
	session_start();
	//session_destroy();
?>
<h3>Wartungsplan</h3>
<?php

	if(!isset($_SESSION['wartung'])){
		$_SESSION['wartung'] = array();
	}	
	
	if(isset($_POST['flugzeugid'])){
		array_push($_SESSION['wartung'], $_POST['flugzeugid']);			
	}
	
	foreach($_SESSION['wartung'] as $flugzeugid){
		echo "<div class='vorgemerkt'>";
		echo "#";
		echo $flugzeugid;
		echo "</div>";
	}

?>

================FLUGZEUGE =================
<?php

$db = new DB();

$flugzeuge = $db->getAllFlugzeuge();

foreach($flugzeuge as $flugzeug){
	echo "<div class='flugzeug'>#";
	echo $flugzeug->flugzeugid;
	echo "<br />";
	echo $flugzeug->bezeichnung;
	echo "<br />";
	echo $flugzeug->flugstunden;
	echo "<br />";
	echo $flugzeug->fluglinie;
	echo "<br />";
	echo "<button onclick='sendPostRequest($flugzeug->flugzeugid)'>Vormerken</button>";
	echo "</div>";
}

?>

======================  NAVI ==================
<ul class="nav nav-pills nav-stacked">
<?php
	// siehe: http://getbootstrap.com/components/#nav-pills
	$naviObjekt = simplexml_load_file("config/navigation.xml");

	foreach($naviObjekt->menuepunkt as $eintrag){
		$class = '';
		if(isset($_GET['section']) && $_GET['section'] == $eintrag->section){
			$class = 'active';
		}
		
		echo "<li role='presentation' class='$class'>";
		echo "<a href='index.php?section=$eintrag->section'>$eintrag->bezeichnung</a>";
		echo "</li>";
	}
?>

</ul>

======================= PERSONAL ===========================
<?php

$db = new DB();

$personal = $db->getPersonal_16();

var_dump($personal);


?>

===================== Flugzeug CLASS =========================
<?php

class Flugzeug{

	private $flugzeugid = null;
	private $bezeichnung = null;
	private $flugstunden = null;
	private $fluglinie = null;	
	
	function __construct($id, $bez, $st, $fl){
		$this->flugzeugid = $id;
		$this->bezeichnung = $bez;
		$this->flugstunden = $st;
		$this->fluglinie = $fl;
	}
	
	public function __get($property) {
		if (property_exists($this, $property)) {
		  return $this->$property;
		}
	}	
	
}

?>

=================== MITARBEITER CLASS ==========================
<?php

class Mitarbeiter{

	private $personalnr = null;
	private $vorname = null;
	private $nachname = null;
	private $plz = 1200;
	private $adresse = "Höchstädtplatz 6";
	private $bezeichnung = null;
	
	function __construct($pnr, $vn, $nn, $plz, $adr, $bez){
		$this->personalnr = $pnr;
		$this->vorname = $vn;
		$this->nachname = $nn;
		$this->plz = $plz;
		$this->adresse = $adr;
		$this->bezeichnung = $bez;
	}
	
	
}

?>


================== DB Class =====================================

<?php

class DB{

	private $user = "bpohn2";
	private $pwd = "orcldbpwd";
	private $host = "infdb.technikum-wien.at:1521/o10";
	private $conn = null;
	private $flugzeugArray = array();
	
	function doConnect(){
		$this->conn = oci_connect($this->user,$this->pwd,$this->host);
	}		
	
	
	function getAllFlugzeuge(){
		$this->doConnect();
		if($this->conn){
			$query = "Select flugzeugid as fid,
						flugstunden_letzte_wartung as flw,
						flugzeugtyp.bezeichnung as ftbez,
						fluglinie.bezeichnung as flbez
						from flugzeug
						join flugzeugtyp using(flugzeugtypid)
						join fluglinie using(fluglinieid)";
			$statement = oci_parse($this->conn,$query);
			$success = oci_execute($statement);
			
			while($eintrag = oci_fetch_object($statement)){
				$flugzeug = new Flugzeug($eintrag->FID,
											$eintrag->FTBEZ,
											$eintrag->FLW,
											$eintrag->FLBEZ);
				array_push($this->flugzeugArray,$flugzeug);
			}			
			return $this->flugzeugArray;
		}	
	}
	
}

?>