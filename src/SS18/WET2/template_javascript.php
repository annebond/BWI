<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">

    <title>Hello, world!</title>
    
    <script language="JavaScript">
 			function Quadrat(Zahl)
 			{
 				Ergebnis=Zahl*Zahl;
 				alert("Das Quadrat von "+Zahl+“ = "+Ergebnis);
 			}
	</script> 
    
  </head>
  <body>
    <h1>Hello, world!</h1>
    
    <!-- javaScript links -->
    <p>
        <a href="javascript:meineFunktion();">
        	<b>meine Funktion aufrufen</b>
    	</a>
    </p>
    
    <!-- javaScript mittels EventHandler onmouseover einbetten -->
    <p style="cursor:pointer"
 		onmouseover="this.innerHTML = 'Sehen Sie?'"
 		onmouseout="this.innerHTML = 'Ich bin dynamisch'">
		Ich bin dynamisch
 	</p>
 		<!-- Eventhandler:
 		onAbort (bei Abbruch)
        onClick (beim Anklicken)
        onDblClick (bei doppeltem Anklicken)
        onKeydown (bei gedrückter Taste)
        onKeypress (bei gedrückt gehaltener Taste)
        onKeyup (bei losgelassener Taste)
        onLoad (beim Laden einer Datei)
        onMousedown (bei gedrückter Maustaste)
        onMousemove (bei weiterbewegter Maus)
        onMouseout(beim Verlassen des Elements mit der Maus)
        onMouseover (beim Überfahren des Elements mit der Maus)
        onMouseUp (bei losgelassener Maustaste)
        onReset (beim Zurücksetzen des Formulars)
        onSelect (beim Selektieren von Text)
        onSubmit (beim Absenden des Formulars) 		 
 		 -->
    <p>
    	<!-- siehe funktion definition im head -->
    	<input type=button value="Quadrat von 6 errechnen"
			onClick="Quadrat(6)"> 
    </p>
    
    <p>
    	<script type="text/javascript">
    	<!-- javascript DOM funktionen 
    	https://www.w3schools.com/jsref/dom_obj_document.asp
        	
    	document.getElementById(“content”);
    	document.getElementsByName(“myForm”);
    	document.getElementsByTagName(“h2”);
    	write(“<h1>Hallo Welt</h1>”);
    	writeln(“<h1>Hallo Welt</h1>”);

    	//neues DIV und Text erzeugen, attribute vergeben
    	var newNode = document.createElement("div");
    	var newText = document.createTextNode("Hello World");
		newNode.setAttribute("id","newOne");    	    	
    	--> 		
    	</script>	
    </p>
    
	<p>
    </p>
    
    <p>
    	<!-- script file einbinden -->
    	<script type="text/javascript„ src=„meinScript.js“> </script>
    </p>
    

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
  </body>
</html>






