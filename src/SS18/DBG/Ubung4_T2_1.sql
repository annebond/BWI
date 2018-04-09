SET serveroutput ON; 

CREATE OR REPLACE package test AS
PROCEDURE gep (v_flugID NUMBER default 24);
END test;
/

CREATE OR REPLACE package body test AS
PROCEDURE gep (v_flugID NUMBER default 24) IS 
  -- erster cursor um daten für diese Zeile zu fetchen: 1. Tom Brown: <Gepäckstücke: 3 - Gesamtgewicht: 27,4kg>
  CURSOR data IS
    SELECT passagierliste.personID, vorname, nachname, count(gepaeckID) AS gAnzahl, sum(gewicht) AS sumgewicht FROM passagierliste
    LEFT JOIN person ON person.personID = passagierliste.personID
    LEFT JOIN gepaeck ON gepaeck.personID = passagierliste.personID AND gepaeck.flugID = passagierliste.flugID
    WHERE passagierliste.flugID = v_flugID
    GROUP BY passagierliste.personID, vorname, nachname;
  vResult data%ROWTYPE;
  v_flugnummer VARCHAR2(6);
  v_count NUMBER;

  -- zweiter cursor der innerhalb vom ersten cursor die einzelnen gepäckstk einer person ausgeben soll
  CURSOR egep IS
  SELECT gewicht FROM gepaeck
  WHERE personID = vResult.personID AND flugID = v_flugID;

  BEGIN
    -- IF um zu prüfen ob es FlugID gibt, damit kein no data error kommt
    SELECT COUNT(flugID) INTO v_count FROM flug
    WHERE flugID = v_flugID;
  
    IF v_count = 1 THEN
      SELECT flugnummer INTO v_flugnummer FROM flug
      WHERE flugID = v_flugID;
      DBMS_OUTPUT.PUT_LINE('Flug: ' || v_flugnummer);
      OPEN data;
      LOOP
        FETCH data INTO vResult;
        EXIT WHEN data%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(data%ROWCOUNT || '. ' || vResult.vorname || ' ' || vResult.nachname || ': <Gepäckstücke: ' || vResult.gAnzahl || ' - Gesamtgewicht: ' || vResult.sumgewicht || 'kg>');
            FOR vResult2 IN egep
            LOOP
              DBMS_OUTPUT.PUT_LINE('   Gepäck ' || egep%ROWCOUNT || ': ' || vResult2.gewicht || 'kg');
            END LOOP;
      END LOOP;
      CLOSE data;
    ELSE DBMS_OUTPUT.PUT_LINE('FlugID: ' || v_flugID || ' gibt es nicht');
    END IF;
  END;
END;
/

EXEC test.gep(1);

-- Ubung 4/T2/1 --
/*
Die public procedure listet alle Passagiere und deren Gepäck auf, welche zu einem bestimmten Flug gehören. 
Dabei wird der Flug durch einen übergebenen Parameter flugID festgelegt. Das Ergebnis soll wie folgt ausgeschrieben werden:
Flug: OS1467
1. Tom Brown: <Gepäckstücke: 3 - Gesamtgewicht: 27,4kg>
 Gepäck 1: 10,5kg
 ...
*/
