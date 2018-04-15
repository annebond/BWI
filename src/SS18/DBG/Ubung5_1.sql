CREATE OR REPLACE PROCEDURE selnach (nname VARCHAR2)
AS
  res PERSON%ROWTYPE;
  -- eigene exception
  e_SPECIAL_PERSON EXCEPTION;
BEGIN
  
  IF nname LIKE 'A%' then
    -- eigene exception wenn person mit nname beginnend mit A mitgegeben werden
	  RAISE e_SPECIAL_PERSON;
  ELSE
   SELECT * INTO res FROM person WHERE lower(nachname) = nname;
   DBMS_OUTPUT.PUT_LINE(res.personID || ' ' || res.vorname || ' ' || res.nachname || ' ' || res.geburtsdatum);
	END IF;
	
  EXCEPTION
    WHEN TOO_MANY_ROWS THEN
      DBMS_OUTPUT.PUT_LINE(nname || ' mehrfach vorhanden Error-Code: ' || SQLCODE || ' Error-Meldung: ' || SQLERRM);
    WHEN NO_DATA_FOUND THEN
      DBMS_OUTPUT.PUT_LINE(nname || ' nicht vorhanden. Error-Code: ' || SQLCODE || ' Error-Meldung: ' || SQLERRM);
    WHEN TIMEOUT_ON_RESOURCE THEN
      DBMS_OUTPUT.PUT_LINE(nname || ' datensatz is gesperrt. Error-Code: ' || SQLCODE || ' Error-Meldung: ' || SQLERRM);
    WHEN e_SPECIAL_PERSON THEN
      DBMS_OUTPUT.PUT_LINE('Nachnamen mit A sind geheim');
    WHEN OTHERS THEN
      -- kann verwendet werden um eigene error messages zu erstellen
      RAISE_APPLICATION_ERROR (-20001,'Unexpected error');
    
END;
/

--nicht vorhanden
EXEC selnach ('drtzderzt');
-- mehrfach vorhanden: 
EXEC selnach ('bauer');
-- eigene exception: 
EXEC selnach ('ackerl');


-- Ubung 5/1 --
/*
Gegeben sei das folgendes Select-Statement: Select * from person where lower(nachname) = nname;
Schreiben Sie eine PL/SQL Prozedur mit einem Parameter nname (Nachname einer Person), welche das Ergebnis des Select-Statements ausdruckt (mit PUT_LINE). 
Verwenden Sie aber keinen Cursor (im Falle, dass es mehrere Ergebnisse zu einem Nachnamen gibt). Reagieren Sie stattdessen auf alle möglichen Fehler, die auftreten können und verwenden Sie diesmal explizites Exception Handling. 
Finden Sie eine Situation (nach Ihrer Wahl) für welche Sie einen eigenen Fehler (unter Verwendung von RAISE) definieren. 
Leiten Sie auch alle Fehler (mit entsprechender Fehlernummer und Meldung) nach außen weiter. Erklären Sie weiters, wann RAISE_APPLICATION_ERROR verwendet werden kann.
*/
