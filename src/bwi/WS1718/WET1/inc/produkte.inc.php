<h2>Unsere Produkte</h2>
<?php 

echo "<div class=\"container\">";
echo "<div class='row'>";
foreach ($produkte as $produkt) {
    echo "<div class='produkt col-md-4'>";
        echo "<h3>";        
        echo $produkt["Name"];
        echo "</h3>";
        echo "<img src=\"pics/" . $produkt['ID'] . ".jpg\"/>";
        echo "<div class='produktbeschreibung'>";
        echo $produkt['Desc'];
        echo "</div>";
        
        echo "<div>";
        switch ($produkt["Sterne"]) {
                case $produkt["Sterne"] == 4;
                    echo "****";
                    break;
                case $produkt["Sterne"] == 3;
                    echo "***";
                    break;
                case $produkt["Sterne"] == 2;
                    //echo "**";
                    echo "<span class='glyphicon glyphicon-align-left'</span>";
                    echo "<span class='glyphicon glyphicon-align-left'</span>";
                    break;
            };
        echo "</div>";
        echo "<div class='produktpreis'>";
            echo "&euro; " . $produkt["Preis"];
        echo "</div>";
        echo "<div class='InDenWarenkorb'>";
            echo "<a class=\"btn btn-primary\" href=\"index.php?preis=" . $produkt['Preis'] . "\" role=\"button\">In den Warenkorb</a>";
        echo "</div>";
    echo "</div>";
  
}
echo "</div>";
echo "</div>";
?>