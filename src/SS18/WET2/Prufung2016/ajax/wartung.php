<?php

session_start();

$_SESSION['flugzeugids'][] =  $_POST['id'];
$ids = $_SESSION['flugzeugids'];


//echo 'GET ID: ' . $_POST['id'];

foreach ($ids as $var) {
    echo '<div class="vorgemerkt">';
    echo $var;
    echo '</div>';
}


?>
