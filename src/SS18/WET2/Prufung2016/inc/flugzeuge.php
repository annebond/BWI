<?php
//include '../utility/DB.class.php';

$test = new DB();
$ergebnis = $test->auslesen();
//var_dump($ergebnis);

foreach ($ergebnis as $objekt) {
    echo '<div class="flugzeug">';
        echo $objekt->fid;
        echo '<br />';
        echo $objekt->flw;
        echo '<br />';
        echo $objekt->ftbez;
        echo '<br />';
        echo $objekt->flbez;
        echo '<br />';
        echo '<button type ="button" onclick="flugzeugclick(';
            echo $objekt->fid;
            echo ')">Vormerken';
        echo '</button>';
    echo '</div>';
}

?>


