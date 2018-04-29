<?php
$meinXML = simplexml_load_file('config/navigation.xml');
//var_dump($meinXML);

echo '<ul class="nav nav-pills nav-stacked">';
foreach ($meinXML->menuepunkt as $element) {
    // mit echo eventuell class active mitgeben --> <li class="active">
    echo '<li role="presentation"'; 
        if(isset($_GET['navi'])) {
            if ($_GET['navi'] == $element->section) {
                echo 'class="active"';
            }
        }
    echo '>';
    
    // in einem link mit url?variable= die section mitgeben
    // mit echo link ausgeben: <a href="url">link text</a>
    echo '<a href = "index.php?navi=';
        echo $element->section;
        echo '">';
        echo $element->bezeichnung;
    echo '</a></li>';    
    

}
echo '</ul>'

?>