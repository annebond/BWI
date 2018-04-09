SET serveroutput ON; 

CREATE OR REPLACE PROCEDURE vi (v_i INT)
IS
CURSOR TEST IS SELECT * FROM person;
vResult TEST%ROWTYPE;
v_i2 INT;

BEGIN
  -- v_i wegspeichern, damit immer um diesen Wert erhöht werden kann = jede v_i ten Datensatz
  v_i2 := v_i;
  OPEN TEST;
  LOOP
    FETCH TEST INTO vResult;
    EXIT WHEN TEST%NOTFOUND;
    IF TEST%rowcount = v_i2 THEN
      DBMS_OUTPUT.PUT_LINE(vResult.personID || ' ' || vResult.vorname || ' ' || vResult.nachname);
      v_i2 := v_i2 + v_i;
    END IF;
--    dbms_output.put_line('rowcount is ' || TEST%rowcount);
  END LOOP;
  CLOSE TEST;
END;
/

EXEC vi(5);

-- Ubung 4/T1/1 --
/*
Schreiben Sie eine PL/SQL Prozedur mit einem Parameter (ganze Zahl v_i) und mit einem Select-Statement Ihrer Wahl. 
Aufgabe der Prozedur ist es, jeden v_i-ten Datensatz des Ergebnisse auszuschreiben. 
Verwenden Sie aber keine rownum (wie in der ersten Übung), sondern einen Cursor.
*/
