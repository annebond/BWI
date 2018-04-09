SET serveroutput ON; 

CREATE OR REPLACE package test AS
PROCEDURE gep (v_flugID NUMBER);
PROCEDURE gep;
FUNCTION fun RETURN NUMBER;
FUNCTION fun(v_zahl INTEGER) RETURN NUMBER;
END test;
/

CREATE OR REPLACE package body test AS
  v_flugID_intern NUMBER := -1;

-- private procedure
PROCEDURE printGep (v_personID NUMBER, v_flugID NUMBER)IS
  CURSOR egep IS
    SELECT gewicht FROM gepaeck
    WHERE personID = v_personID AND flugID = v_flugID;
BEGIN
  FOR vResult2 IN egep
    LOOP
      DBMS_OUTPUT.PUT_LINE('   Gepäck ' || egep%ROWCOUNT || ': ' || vResult2.gewicht || 'kg');
    END LOOP;
END;

PROCEDURE gep (v_flugID NUMBER) IS 
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


  BEGIN
    -- IF um zu prüfen ob es FlugID gibt, damit kein no data error kommt
    SELECT COUNT(flugID) INTO v_count FROM flug
    WHERE flugID = v_flugID;
  
    IF v_count = 1 THEN
      -- zuletzt verwendete flugID wegspeichern
      v_flugID_intern := v_flugID;
      SELECT flugnummer INTO v_flugnummer FROM flug
      WHERE flugID = v_flugID;
      DBMS_OUTPUT.PUT_LINE('Flug: ' || v_flugnummer);
      OPEN data;
      LOOP
        FETCH data INTO vResult;
        EXIT WHEN data%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(data%ROWCOUNT || '. ' || vResult.vorname || ' ' || vResult.nachname || ': <Gepäckstücke: ' || vResult.gAnzahl || ' - Gesamtgewicht: ' || vResult.sumgewicht || 'kg>');
        -- private procedure ausführen:
        printGep(vResult.personID, v_flugID);
      END LOOP;
      CLOSE data;
    ELSE DBMS_OUTPUT.PUT_LINE('FlugID: ' || v_flugID || ' gibt es nicht');
    END IF;
  END;

PROCEDURE gep IS
BEGIN
  BEGIN
    gep(v_flugID_intern);
  END;
END;

-- Ubung 4/T2/2 FUNCTION
FUNCTION fun
RETURN NUMBER
IS
  BEGIN
    return v_flugID_intern;
  END;

FUNCTION fun(v_zahl INTEGER)
RETURN NUMBER
IS
  v_flugID_next NUMBER;
  v_flughafen_abflug NUMBER;
  v_abflugzeit TIMESTAMP(6);
  v_count NUMBER;
BEGIN
CASE
  WHEN v_flugID_intern = -1 THEN v_flugID_next := v_flugID_intern;
  ELSE
    SELECT flughafen_abflug, abflugzeit INTO v_flughafen_abflug, v_abflugzeit FROM flug
    WHERE flugID = v_flugID_intern;
    
   --  nächste, übernächste, vorhergehende ? Flug ausgeben: über Select ausgabe haben mit abflugort und kleiner/größer als abflugzeit von flugID, über rownum die zeile ausgeben, welche als parameter mitgegeben wurde
    CASE
      WHEN v_zahl > 0 THEN
        -- Exception Handling, falls select no data liefern würde, soll -1 zurückkommen
        SELECT count(flugID) INTO v_count FROM 
          (SELECT * FROM flug
          WHERE flughafen_abflug = v_flughafen_abflug AND abflugzeit > v_abflugzeit
          ORDER BY abflugzeit ASC)
        WHERE rownum = v_zahl; 
        IF v_count = 1 THEN        
          SELECT flugID INTO v_flugID_next FROM 
            (SELECT * FROM flug
            WHERE flughafen_abflug = v_flughafen_abflug AND abflugzeit > v_abflugzeit
            ORDER BY abflugzeit ASC)
          WHERE rownum = v_zahl; 
        ELSE v_flugID_next := -1;
        END IF;  

      WHEN v_zahl < 0 THEN
        -- Exception Handling, falls select no data liefern würde, soll -1 zurückkommen
        SELECT count(flugID) INTO v_count  FROM 
          (SELECT * FROM flug
          WHERE flughafen_abflug = v_flughafen_abflug AND abflugzeit < v_abflugzeit
          ORDER BY abflugzeit DESC)
        WHERE rownum = v_zahl * -1; 
        IF v_count = 1 THEN  
          SELECT flugID INTO v_flugID_next  FROM 
            (SELECT * FROM flug
            WHERE flughafen_abflug = v_flughafen_abflug AND abflugzeit < v_abflugzeit
            ORDER BY abflugzeit DESC)
          WHERE rownum = v_zahl * -1; 
        ELSE v_flugID_next := -1;
        END IF;
        
      ELSE v_flugID_next := v_flugID_intern;
    END CASE;
END CASE;
  return v_flugID_next;
END;

END;
/

EXEC test.gep(26);

BEGIN
DBMS_OUTPUT.PUT_LINE(test.fun);
END;
/

BEGIN
DBMS_OUTPUT.PUT_LINE(test.fun(1));
END;
/

-- Ubung 4 --
/*
T2/1
Die public procedure listet alle Passagiere und deren Gepäck auf, welche zu einem bestimmten Flug gehören. 
Dabei wird der Flug durch einen übergebenen Parameter flugID festgelegt. Das Ergebnis soll wie folgt ausgeschrieben werden:
Flug: OS1467
1. Tom Brown: <Gepäckstücke: 3 - Gesamtgewicht: 27,4kg>
 Gepäck 1: 10,5kg
 ...

T2/2
Die public function gibt Folgendes zurück: Im Falle, wenn kein Parameter angegeben wird: die zuletzt verwendete flugID.
Im Falle, wenn eine ganze Zahl übergeben wird: dann wird ausgehend von der aktuell gespeicherten flugID je nach übergebenem Wert der
zeitlich gesehen nächste, übernächste, vorhergehende ? Flug, welcher vom selben Flughafen startet, ausgegeben. So bedeutet z.B. 1 den nächsten Flug, -1 den vorhergehenden Flug, 2 den übernächsten Flug etc.

T2/3
Verwenden Sie mindestens eine private function oder procedure, welche für die Berechnung interner Zwischenschritte verwendet wird. 
So ist z.B. eine interne Prozedur mit einem Parameter personID möglich, welche die Gepäckstücke einer angegebenen Person abarbeitet.
*/


    SELECT * FROM flug
    WHERE flughafen_abflug = 127
    order by abflugzeit;

-- Test T2/2: FlugID 24, hat Flughaven_Abflug 127, hat Abflugzeit 2007-11-21 --> nächste sollte flugID 26 sein
    SELECT flugID FROM 
      (SELECT * FROM flug
      WHERE flughafen_abflug = 127 AND abflugzeit > to_date('2007-11-21','yyyy-mm-ss')
      ORDER BY abflugzeit);
    --WHERE rownum = 1;
