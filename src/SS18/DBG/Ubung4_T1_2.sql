CREATE OR REPLACE FUNCTION auslastung (v_flugID NUMBER) 
RETURN NUMBER
IS
  v_auslastung NUMBER;
  v_count NUMBER;
  v_all NUMBER;
  v_vergeben NUMBER;
BEGIN
  -- prüfe ob es flugID überhaupt gibt
  SELECT count(flugID) INTO v_count FROM flug
  WHERE flugID = v_flugID;
  IF v_count = 1 THEN
    -- alle sitzplätze vom flug mit flugID zählen
    SELECT sitzplaetze into v_all FROM flugzeugtyp
    JOIN flugzeug ON flugzeug.flugzeugtypID = flugzeugtyp.flugzeugtypID
    JOIN flug ON flug.flugzeugID = flugzeug.flugzeugID
    WHERE flug.flugID = v_flugID;
    
    SELECT count(sitzplatznummer) INTO v_vergeben FROM passagierliste
    JOIN flug ON flug.flugID = passagierliste.flugID
    WHERE flug.flugID = v_flugID;
  END IF;
  
  RETURN (v_vergeben / v_all * 100);
--  DBMS_OUTPUT.PUT_LINE(v_all);
END;
/

SELECT auslastung(24) AS "Auslatung in %" FROM DUAL;

-- Ubung 4/T1/2 --
/*
Schreiben Sie eine PL/SQL Funktion, welche die Auslastung eines angegebenen Fluges (Parameter flugID) zurück gibt.
Die Auslastung berechnet sich als die Prozent der bereits gebuchten Sitzplätze.
*/
