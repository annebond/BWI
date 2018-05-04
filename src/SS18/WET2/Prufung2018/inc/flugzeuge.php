<?php

$war = $_GET['navi'];

echo '<h2> Wartungsstatus:';
echo $war;
echo '</h2>';

$test = new DB();
$ergebnis = $test->auslesen($war);
//var_dump($ergebnis);

foreach ($ergebnis as $objekt) {
    echo '<div class="flugzeug">';
    echo $objekt->fid;
    echo '<br />';
    echo $objekt->ftbez;
    echo '<br />';
    echo $objekt->flw;
    echo '<br />';
    echo $objekt->fli;
    echo '<br />';
    echo '<button type ="button" onclick="flugzeugclick(';
    echo $objekt->fid;
    echo ', ';
    echo $war;
    echo ')">';
    echo $war;
    echo '</button>';
    echo '</div>';
}


?>