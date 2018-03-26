SET serveroutput ON; 

CREATE OR REPLACE FUNCTION CancelFlug (v_flugnummer VARCHAR2, v_vorname VARCHAR2, v_nachname VARCHAR2)
RETURN INT
IS
v_count INT;
v_gebuhr INT;
v_flugID NUMBER;
v_personID NUMBER;

BEGIN
  SELECT COUNT(personID) INTO v_count FROM person
  WHERE vorname = v_vorname AND nachname = v_nachname;
  
  IF v_count = 1 THEN
    -- PersonID von der Person mit dem V und N speichern
    SELECT personID INTO v_personID from person
    WHERE vorname = v_vorname AND nachname = v_nachname;
    
    -- FlugID zur mitgegebenen Flugnummer speichern
    SELECT flugID INTO v_flugID from flug
    WHERE flugnummer = v_flugnummer;
    
    --lösche Person aus passagierliste, zuerst das gepaeck the ON DELETE RESTRICT
    DELETE FROM gepaeck
    WHERE flugID = v_flugID AND personID = v_personID;
    DELETE FROM passagierliste
    WHERE flugID = v_flugID AND personID = v_personID;

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
  v_result := CancelFlug ('OS1467', 'Chistine', 'Bauer');
  IF v_result > 0 THEN
    DBMS_output.put_line('Stornierungskosten: ' || v_result);
  ELSE
    DBMS_output.put_line('Person mit diesem Vornamen und Nachnamen kommt nicht vor oder mehrmals vor.');
  END IF;
END;

-- Bsp. PersonID 5, FlugID 1 => Flugnummer OS1467, Herbert Maier (test erfolgreich)

-- SELECT CancelFlug FROM dual; 
/*A function is supposed to compute and return a result, not change the state of the database. 
If you want to do DML in a function, you cannot call that function in a SELECT statement since a SELECT statement cannot change the state of the database. */

-- Ubung 3/6 --
/* Schreiben Sie eine Datenbankfunktion, welche einen Passagier auf einem Flug cancelt. 
Zu diesem Zweck deklarieren Sie einige Variablen, welche z.B. den Familien- und den Vornamen halten, sowie die Flugnummer.
Löschen Sie tatsächlich den Passagier aus der Passagierliste heraus und geben Sie als Rückgabewert der Funktion die Stornierungskosten aus.
Diese berechnen sich nach folgender Formel:  Gebühr = Flugdauer (in Stunden) * 50
Sollte es zu Fehlermeldungen kommen, überlegen Sie, ob man (ohne explizites Exception Handling) etwas dagegen machen kann. */
