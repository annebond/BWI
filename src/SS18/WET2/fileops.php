<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>FileOps</title>
</head>

<body>

<h2>Formular zum Dateiupload</h2>
<form action="" method="post" enctype="multipart/form-data">
    <input type="file" name="myPicture" />
    <input type="submit" name="submit" value="Upload!" />
</form>

<div>
<h2>Gallery</h2>
<?php

//hochgeladene datei entgegennehmen und in verzeichnis pics verschieben
if(isset($_FILES['myPicture'])){
	var_dump($_FILES['myPicture']);
	move_uploaded_file($_FILES['myPicture']['tmp_name'],"pics/".uniqid().".jpg");
}

//wenn der parameter 'deleteFile' gesetzt ist, wird das File gelöscht
if(isset($_GET['deleteFile'])){
	if(file_exists($_GET['deleteFile'])){
		unlink($_GET['deleteFile']);
	}
}

//folder / verzeichnis erstellen, dank true werden test und level auch erstellt, falls noch nicht vorhanden
//mkdir("test/level/folder",0777,true);

//verzeichnis öffnen
$handle = opendir("pics");

while($myFile = readdir($handle)){
	//nur dateien abfragen, "." und ".." sind verweise auf verzeichnisse
    if($myFile != "." && $myFile != ".."){
		echo "<p><img src='pics/$myFile' width='100' height='100' /><a href='fileops.php?deleteFile=pics/$myFile'>Delete</a></p>";
	}
	
}

?>

</div>



</body>
</html>