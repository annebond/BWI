<?php
$produkte = array(
                array("ID" => "001", "Name" => "Echo Plus",       "Preis" => 149.99, "Sterne" => 4, "Desc" => "Wir stellen vor: Echo Plus - Mit integriertem Smart Home-Hub (schwarz) - inklusive Philips Hue White E27 LED Lampe"),
                array("ID" => "002", "Name" => "Amazon Echo",     "Preis" => 99.99,  "Sterne" => 3, "Desc" => "Das neue Amazon Echo (2. Generation), Anthrazit Stoff"),
                array("ID" => "003", "Name" => "Amazon Echo Dot", "Preis" => 54.99,  "Sterne" => 2, "Desc" => "Amazon Echo Dot (2. Generation), Schwarz"),
                array("ID" => "004", "Name" => "Fire TV Stick",   "Preis" => 39.99,  "Sterne" => 4, "Desc" => "Fire TV Stick mit Alexa-Sprachfernbedienung"),
                array("ID" => "005", "Name" => "Fire TV 4K",      "Preis" => 79.99,  "Sterne" => 3, "Desc" => "Das neue Fire TV mit 4K Ultra HD und Alexa-Sprachfernbedienung (Version 2017, Anh�ngerform)"),
                array("ID" => "006", "Name" => "TR�DFRI",         "Preis" => 29.99,  "Sterne" => 2, "Desc" => "Das praktische Set aus TR�DFRI Fernbedienung und einer E27-LED-Leuchtquelle (gro�er Sockel) mit Wei�spektrum macht den Einstieg leicht."),
            );

if (isset($_POST["suche"])){
    foreach ($produkte as $produkt) {
        if ($produkt["ID"] == $_POST["suche"]) {
            $produkte = array($produkt);
            break;
        }
    }
}


?>