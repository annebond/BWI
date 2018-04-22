--tabelle für logs anlegen
CREATE TABLE logs 
   (
  vorname VARCHAR2 (255), 
	nachname VARCHAR2 (255), 
	flugID NUMBER, 
	time_stamp TIMESTAMP (6)
   ) ;

-- trigger
CREATE OR REPLACE TRIGGER trig_logs_pas BEFORE INSERT OR DELETE OR UPDATE
ON passagierliste FOR EACH ROW
DECLARE
  v_vorname VARCHAR2 (255);
  v_nachname VARCHAR2 (255);
  v_check NUMBER;
PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN

IF DELETING THEN
  INSERT INTO logs SELECT vorname, nachname, :old.flugID, sysdate FROM person WHERE personID = :old.personID;
ELSE
  SELECT count(*) INTO v_check FROM person
  WHERE personID = :new.personID;
  
  IF v_check = 1 THEN
    SELECT vorname, nachname INTO v_vorname, v_nachname FROM person
    WHERE personID = :new.personID;
    INSERT INTO logs VALUES (v_vorname, v_nachname, :new.flugID, sysdate);
  ELSE
    INSERT INTO logs VALUES (NULL, NULL, :new.flugID, sysdate);
  END IF;
END IF;
  COMMIT;
EXCEPTION
  WHEN OTHERS THEN RAISE;
END;
/

------------------ TESTS BEGIN -------------------------------
SET serveroutput ON; 
select sysdate from dual;
select * from logs;
select * from passagierliste;

-- funktionierender INSERT:
insert into passagierliste values (24, 64, '1A');

-- mögliche fehler: flugid 2 existiert nicht
insert into passagierliste values (2, 64, '1A');
-- flugID existiert, personID aber nicht
insert into passagierliste values (106, 6666664, '1A');

------------------ TESTS END -------------------------------

-- Ubung 6/3 --
/*
Schreiben Sie einen Datenbanktrigger, der alle Änderungen (auch versuchte) an der Passagierliste in einer extra Tabelle (muss erst angelegt werden - speichert den vollen Namen des Passagiers, flugID, Timestamp) mitloggt.
Das Logging darf nicht durch Fehler oder zurückggesetzte Transaktionen beeinflusst werden. 
*/
