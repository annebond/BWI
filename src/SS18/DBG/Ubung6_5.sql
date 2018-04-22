SET serveroutput ON; 

CREATE OR REPLACE FUNCTION CancelFlug (v_flugnummer VARCHAR2, v_vorname VARCHAR2, v_nachname VARCHAR2, exc NUMBER)
RETURN INT
IS
v_count INT;
v_gebuhr INT;
v_flugID NUMBER;
v_personID NUMBER;

BEGIN

  SELECT COUNT(personID) INTO v_count FROM person
  JOIN passagierliste USING(personID)
  JOIN flug USING(flugID)
  WHERE vorname = v_vorname AND nachname = v_nachname AND flugnummer = v_flugnummer;
  
  IF v_count = 1 THEN
    -- PersonID von der Person mit dem V und N speichern
    SELECT personID INTO v_personID from person
    WHERE vorname = v_vorname AND nachname = v_nachname;
    
    -- FlugID zur mitgegebenen Flugnummer speichern
    SELECT flugID INTO v_flugID from flug
    WHERE flugnummer = v_flugnummer;
    
    IF exc = 1 THEN
      --lösche Person aus passagierliste, zuerst das gepaeck the ON DELETE RESTRICT
      DELETE FROM gepaeck
      WHERE flugID = v_flugID AND personID = v_personID;
      DELETE FROM passagierliste
      WHERE flugID = v_flugID AND personID = v_personID;
    END IF;
    -- berechne Stornokosten
    SELECT dauer*50 INTO v_gebuhr FROM flug
    WHERE flugID = v_flugID;
    
    
  ELSE
    v_gebuhr := 0;
  END IF;
  return v_gebuhr;
END;
/

DECLARE
v_result INT;
BEGIN
  v_result := CancelFlug ('AF3012', 'Chistine', 'Bauer', 1);
  IF v_result > 0 THEN
    DBMS_output.put_line('Stornierungskosten: ' || v_result);
  ELSE
    DBMS_output.put_line('Person mit diesem Vornamen und Nachnamen kommt nicht auf dem Flug vor oder mehrmals vor.');
  END IF;
END;

select * from logs order by time_stamp desc;

-- gibt es nicht: 'OS1467', 'Chistine', 'Bauer', 0
-- gibt es: 'AF3012', 'Chistine', 'Bauer', 1 --> eintrag im log


-- Ubung 6/5 --
/* 
Erweitern Sie Beispiel 6 aus Übung 3 (einzelner Passagier wird gecancelt und Stornierungsgebühr wird berechnet). Verwenden Sie diesmal mehrere IN Parameter (Nachname, Vorname des Passagiers, flugID und execute), 
um angeben zu können, welcher Passagier auf welchem Flug gecancelt werden soll. 
Für die Berechnung der Stornierungsgebühr wird wiederum die Formel  Gebühr = Flugdauer (in Stunden) * 50 verwendet. 
Der Paramter execute (0 oder 1) wird dazu verwendet um anzugeben, ob der Passagier tatsächlich aus der Passagierliste entfernt wird, oder nur die Stornierungsgebühr berechnet wird.
Testen Sie, ob auch hier obige Trigger (Beispiel 2 und 3) funktionieren.
*/

-- Ubung 3/6 --
/* Schreiben Sie eine Datenbankfunktion, welche einen Passagier auf einem Flug cancelt. 
Zu diesem Zweck deklarieren Sie einige Variablen, welche z.B. den Familien- und den Vornamen halten, sowie die Flugnummer.
Löschen Sie tatsächlich den Passagier aus der Passagierliste heraus und geben Sie als Rückgabewert der Funktion die Stornierungskosten aus.
Diese berechnen sich nach folgender Formel:  Gebühr = Flugdauer (in Stunden) * 50
Sollte es zu Fehlermeldungen kommen, überlegen Sie, ob man (ohne explizites Exception Handling) etwas dagegen machen kann. */
