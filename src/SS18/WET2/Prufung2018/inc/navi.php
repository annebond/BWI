<?php

$navi1 = "Vorgemerkte_Flugzeuge";
$navi2 = "Gewartete_Flugzeuge";
$navi22 = "vorgemerkt";
$navi11 = "gewartet";

    
echo '<ul class="nav nav-pills nav-stacked">';
    echo '<li role="presentation"';
        if(isset($_GET['navi'])) {
            if ($_GET['navi'] == $navi11) {
            
                echo 'class="active"';
            }
        }
    echo '>';
    echo '<a href = "index.php?navi=';
        echo $navi11;
        echo '">';
        echo $navi1;
    echo '</a></li>';
    
    
    echo '<li role="presentation"';
    if(isset($_GET['navi'])) {
        if ($_GET['navi'] == $navi22) {
            
            echo 'class="active"';
        }
    }
    echo '>';
    echo '<a href = "index.php?navi=';
    echo $navi22;
    echo '">';
    echo $navi2;
    echo '</a></li>';

echo '</ul>'


?>