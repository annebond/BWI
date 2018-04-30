// JS in HTML mittels EventHandler onmouseover einbetten
	<p style="cursor:pointer"
 		onmouseover="this.innerHTML = 'Sehen Sie?'",
 		onmouseout="this.innerHTML = 'Ich bin dynamisch'">
 	</p>
 	
 	document.getElementById("first").innerHTML = "Paragraph changed!"; //javascript
 	$('#first').text("Paragraph changed!") //das gleich mit JQuery

// JS EINBINDEN
 	<script type="text/javascript" src="JS/prototype.js"></script>
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script> 	
 	
 	//JS file einbinden
 	<script type="text/javascript" src="meinScript.js"> </script>
// JQUERY ZUGRIFF auf Elemente
 	// alle elemente vom typ img, alle elemente mit ID content, alle elemente mit class rechts
 	$("img").slideToggle("slow")
 	$("#content").slideToggle("slow")
 	$(".rechts").slideToggle("slow")

// JQUERY ZUGRIFF und SETZEN
 	//in die variable txt den value des elementes mit der ID box auslesen
  	//den value des elementes mit der ID box überschreiben mit Textinhalt
 	var txt = $("#box").text();
 	$("#box").text("Textinhalt");
 	

  
// JQuery vs. Vanilla JS
 	$(el).css('border-width', '20px');
 	el.style.borderWidth = '20px';
 	
 	 document.getElementById('firstHeading')
 	 $('#firstHeading')
  
 // JQUERY EVENT methoden
	// alle elemente vom typ p beim click passiert das, was in der function steht --> hide (function anonym, muss nicht wo anders noch declariert werden)
 	$("p").click(function(){
 		// action goes here!!
 		$(this).hide();
 	}); 
 
 	// function schreiben -> alert anzeigen. Definieren, dass beim clik on button funktion aufgerufen wird
 	function notify() {
 	  alert("clicked");
 	}
 	$("button").on("click", notify);
 	
 	// funktionen erst aufrufen wenn DOM geladen, prüfen mit JQuery
 	$(document).ready(function() { //code});
 	
 	
// JS methode: POST ajax Request
// https://api.jquery.com/jquery.post/
	$.post( "test.php", { name: "John", time: "2pm" })
	.done(function( data ) {
		alert( "Data Loaded: " + data );
	});


	
// ajax REQUEST
 	new Ajax.Request(‘myUrl', { method:'get' });
  	new Ajax.Updater(‘myDiv', ‘myUrl', { method: 'get' });
  	new Ajax.PeriodicalUpdater('myDiv', 'myOtherSite.html',
 		{ method: 'get',
 		frequency: 1 });
 
  	var xmlhttp; 
  	if (window.XMLHttpRequest)
  	  {// code for IE7+, Firefox, Chrome, Opera, Safari
  	  xmlhttp=new XMLHttpRequest();
  	  }
  	else
  	  {// code for IE6, IE5
  	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  	  }
  
  	xmlhttp.open("GET","inc/filelocation.php",true);
  	xmlhttp.send();
  
  	req.open("GET",“eineAdresse.html…“);
  	req.send(null);
  
  	req.open(“POST”,”eineSeite.php”);
  	req.send(“user=xy&pwd=pw1”);
  
  AJAX response
  	document.body.innerHTML + = req.responseText;

//EventHandler
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