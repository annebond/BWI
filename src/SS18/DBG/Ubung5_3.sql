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
      DBMS_OUTPUT.PUT_LINE('   Gep�ck ' || egep%ROWCOUNT || ': ' || vResult2.gewicht || 'kg');
    END LOOP;
END;

PROCEDURE gep (v_flugID NUMBER) IS 
  -- erster cursor um daten f�r diese Zeile zu fetchen: 1. Tom Brown: <Gep�ckst�cke: 3 - Gesamtgewicht: 27,4kg>
  CURSOR data IS
    SELECT passagierliste.personID, vorname, nachname, count(gepaeckID) AS gAnzahl, sum(gewicht) AS sumgewicht FROM passagierliste
    LEFT JOIN person ON person.personID = passagierliste.personID
    LEFT JOIN gepaeck ON gepaeck.personID = passagierliste.personID AND gepaeck.flugID = passagierliste.flugID
    WHERE passagierliste.flugID = v_flugID
    GROUP BY passagierliste.personID, vorname, nachname;
  vResult data%ROWTYPE;
  v_flugnummer VARCHAR2(6);
  v_count NUMBER;

  -- zweiter cursor der innerhalb vom ersten cursor die einzelnen gep�ckstk einer person ausgeben soll


  BEGIN
    -- zuletzt verwendete flugID wird in variable gespeichern
    SELECT flugID INTO v_flugID_intern FROM flug
    WHERE flugID = v_flugID;

      SELECT flugnummer INTO v_flugnummer FROM flug
      WHERE flugID = v_flugID;
      DBMS_OUTPUT.PUT_LINE('Flug: ' || v_flugnummer);
      OPEN data;
      LOOP
        FETCH data INTO vResult;
        EXIT WHEN data%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(data%ROWCOUNT || '. ' || vResult.vorname || ' ' || vResult.nachname || ': <Gep�ckst�cke: ' || vResult.gAnzahl || ' - Gesamtgewicht: ' || vResult.sumgewicht || 'kg>');
        -- private procedure ausf�hren:
        printGep(vResult.personID, v_flugID);
      END LOOP;
      CLOSE data;

  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      DBMS_OUTPUT.PUT_LINE('FlugID: ' || v_flugID || ' gibt es nicht');
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
    
   --  n�chste, �bern�chste, vorhergehende ? Flug ausgeben: �ber Select ausgabe haben mit abflugort und kleiner/gr��er als abflugzeit von flugID, �ber rownum die zeile ausgeben, welche als parameter mitgegeben wurde
    CASE
      WHEN v_zahl > 0 THEN    
          SELECT flugID INTO v_flugID_next FROM 
            (SELECT * FROM flug
            WHERE flughafen_abflug = v_flughafen_abflug AND abflugzeit > v_abflugzeit
            ORDER BY abflugzeit ASC)
          WHERE rownum = v_zahl; 

      WHEN v_zahl < 0 THEN
          SELECT flugID INTO v_flugID_next  FROM 
            (SELECT * FROM flug
            WHERE flughafen_abflug = v_flughafen_abflug AND abflugzeit < v_abflugzeit
            ORDER BY abflugzeit DESC)
          WHERE rownum = v_zahl * -1; 
        
      ELSE v_flugID_next := v_flugID_intern;
    END CASE;
END CASE;
RETURN v_flugID_next;

EXCEPTION
  -- Exception Handling, falls selects in when cases no data liefern w�rde, soll -1 zur�ckkommen
  WHEN NO_DATA_FOUND THEN
    v_flugID_next := -1;
    DBMS_OUTPUT.PUT_LINE('Kein Datensatz gefunden.');
RETURN v_flugID_next;
END;

END;
/
-- =============================================================================================================== --
-- =============================================================================================================== --

EXEC test.gep(24);

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
Die public procedure listet alle Passagiere und deren Gep�ck auf, welche zu einem bestimmten Flug geh�ren. 
Dabei wird der Flug durch einen �bergebenen Parameter flugID festgelegt. Das Ergebnis soll wie folgt ausgeschrieben werden:
Flug: OS1467
1. Tom Brown: <Gep�ckst�cke: 3 - Gesamtgewicht: 27,4kg>
 Gep�ck 1: 10,5kg
 ...

T2/2
Die public function gibt Folgendes zur�ck: Im Falle, wenn kein Parameter angegeben wird: die zuletzt verwendete flugID.
Im Falle, wenn eine ganze Zahl �bergeben wird: dann wird ausgehend von der aktuell gespeicherten flugID je nach �bergebenem Wert der
zeitlich gesehen n�chste, �bern�chste, vorhergehende ? Flug, welcher vom selben Flughafen startet, ausgegeben. So bedeutet z.B. 1 den n�chsten Flug, -1 den vorhergehenden Flug, 2 den �bern�chsten Flug etc.

Achten Sie dabei auf Folgendes: Explizites Exception Handling (mit Hilfe der Klausel exception) ist vorerst noch nicht vorgesehen. Versuchen Sie trotzdem m�gliche Fehler abzufangen. Im Fehlerfall geben Sie -1 zur�ck

T2/3
Verwenden Sie mindestens eine private function oder procedure, welche f�r die Berechnung interner Zwischenschritte verwendet wird. 
So ist z.B. eine interne Prozedur mit einem Parameter personID m�glich, welche die Gep�ckst�cke einer angegebenen Person abarbeitet.
*/


    SELECT * FROM flug
    WHERE flughafen_abflug = 127
    order by abflugzeit;

-- Test T2/2: FlugID 24, hat Flughaven_Abflug 127, hat Abflugzeit 2007-11-21 --> n�chste sollte flugID 26 sein
    SELECT flugID FROM 
      (SELECT * FROM flug
      WHERE flughafen_abflug = 127 AND abflugzeit > to_date('2007-11-21','yyyy-mm-ss')
      ORDER BY abflugzeit);
    --WHERE rownum = 1;
