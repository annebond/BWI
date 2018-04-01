

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Bootstrap 101 Template</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  	<div class="page-header">
  		<h1>BWI WET2 Feedreader</h1>
	</div>
  	<div>
      	<form method="post" action="./rss_reader.php">
      		URL: <input type="text" name="URL" placeholder="Link to Feed">
      		<input type ="submit" value="Senden">
      	</form>
        <?php

        if (count($_POST)>0){
            //echo "hallo";
            //var_dump($_POST);
            $feed=$_POST["URL"];
            $meinXML = simplexml_load_file($feed);
            
            $root_element_name = $meinXML->getName();
            if ($root_element_name  == 'feed') {
                //echo "is atom feed";
                foreach ($meinXML->entry as $eintrag){
                    //var_dump($eintrag);
                    $itemTitle = $eintrag->title;
                    $itemDesc = $eintrag->summary;
                    $itemLink = $eintrag->link;
                    
                    echo "<div>";
                    echo "<h2><a href='$itemLink'>$itemTitle</a></h2>";
                    echo "<p>$itemDesc</p>";
                    echo "<a href='$itemLink'>Zum Artikel</a>";
                    echo "</div>";
                };
                
            } else if ($root_element_name  == 'rss') {
                //echo "is rss feed";
                foreach ($meinXML->channel->item as $eintrag){
                    //var_dump($eintrag);
                    $itemTitle = $eintrag->title;
                    $itemDesc = $eintrag->description;
                    $itemLink = $eintrag->link;
                    
                    echo "<div>";
                    echo "<h2><a href='$itemLink'>$itemTitle</a></h2>";
                    echo "<p>$itemDesc</p>";
                    echo "<a href='$itemLink'>Zum Artikel</a>";
                    echo "</div>";
                };
                
            };
            
            

        };
        ?>      	
  	</div>
  
  
  
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
  </body>
</html>
  
