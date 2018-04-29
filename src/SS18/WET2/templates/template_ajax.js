/**
 * js EINBINDEN
 * 	<script type="text/javascript„ src="JS/prototype.js"></script>
 * 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
 * 
 * JS ZUGRIFF auf Elemente
 * 	$('myDiv').style.color = „red“;
 *
 * ajax REQUEST
 *	new Ajax.Request(‘myUrl', { method:'get' });
 * 	new Ajax.Updater(‘myDiv', ‘myUrl', { method: 'get' });
 * 	new Ajax.PeriodicalUpdater('myDiv', 'myOtherSite.html',
 		{ method: 'get',
 		frequency: 1 });
 *
 * 	var xmlhttp; 
 * 	if (window.XMLHttpRequest)
 * 	  {// code for IE7+, Firefox, Chrome, Opera, Safari
 * 	  xmlhttp=new XMLHttpRequest();
 * 	  }
 * 	else
 * 	  {// code for IE6, IE5
 * 	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
 * 	  }
 * 
 * 	xmlhttp.open("GET","inc/filelocation.php",true);
 * 	xmlhttp.send();
 * 
 * 	req.open("GET",“eineAdresse.html…“);
 * 	req.send(null);
 * 
 * 	req.open(“POST”,”eineSeite.php”);
 * 	req.send(“user=xy&pwd=pw1”);
 * 
 * AJAX response
 * 	document.body.innerHTML + = req.responseText;
 *
 * JQUERY ZUGRIFF auf Elemente, ansprechen
 * 	$("img").slideToggle("slow")
 * 	$("#content").slideToggle("slow")
 * 	$(".rechts").slideToggle("slow")
 * 
 * erst aufrufen wenn DOM geladen
 *	$(function(){
 *		funktionsAufruf();
 *	});
 *
 * JQUERY EVENT methoden
 * https://www.w3schools.com/jquery/jquery_events.asp
 *	$("p").click(function(){
 *  	// action goes here!!
 *  	$(this).hide();
 *	}); 
 *
 *	function notify() {
 *	  alert( "clicked" );
 *	}
 *	$( "button" ).on( "click", notify );
 *
 * JQUERY Zugriff und Setzen
 *	 var txt = $("#box").text();
 * 	$("#box").text("Textinhalt");
 * 
 * JS methode: POST ajax Request
 * https://api.jquery.com/jquery.post/
 * $.post( "test.php", { name: "John", time: "2pm" })
  .done(function( data ) {
    alert( "Data Loaded: " + data );
  });
 */


