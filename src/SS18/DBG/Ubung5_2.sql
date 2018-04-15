CREATE OR REPLACE PACKAGE test AS
PROCEDURE delpas (v_personID NUMBER);
PROCEDURE nummern (v_personID_1 NUMBER, v_personID_2 NUMBER, v_personID_3 NUMBER);
END test;
/

CREATE OR REPLACE PACKAGE BODY test AS

-- procedure welche für jede mitgegebene personID eine procedure aufruft, welche das Löschen durchführt
PROCEDURE nummern (v_personID_1 NUMBER, v_personID_2 NUMBER, v_personID_3 NUMBER) IS
  v_count NUMBER;
  e_noperson EXCEPTION;
BEGIN
  savepoint startdelpas;
  delpas (v_personID_1);
  delpas (v_personID_2);
  delpas (v_personID_3);

  EXCEPTION
    WHEN e_noperson THEN
      DBMS_OUTPUT.PUT_LINE('Fehler: Nicht existierende PersonID. Alle Änderungen werden rückgängig gemacht.');
      rollback to startdelpas;
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Fehler. Alle Änderungen werden rückgängig gemacht.');
      rollback to startdelpas;
END;

-- procedure, die das löschen durchführt
PROCEDURE delpas (v_personID NUMBER) IS
  v_count NUMBER;
  e_noperson EXCEPTION;
BEGIN 

  SELECT COUNT(*) INTO v_count FROM passagierliste WHERE personID = v_personID;
  IF v_count = 0 THEN
    RAISE e_noperson;
  END IF;

  -- ggf. gibt es personID in gepäck nicht -> egal, da dann einfach 0 rows effected
  DELETE FROM gepaeck WHERE personID = v_personID;
  DELETE FROM passagierliste WHERE personID = v_personID;
  DBMS_OUTPUT.PUT_LINE ('Person mit PersonID: ' || v_personID || ' wurde aus der Passagierliste gelöscht.');
END;

END;
/


EXEC test.nummern(6,64,65554);


-- Ubung 5/2 --
/*
Schreiben Sie eine PL/SQL Prozedur, welche hintereinander (z.B. durch explizite Angabe von personIDs) Personen aus der Passagierliste löscht.
Zumindest 2 delete Statements hintereinander sollen funktionieren, ein weiteres delete Statement gibt einen Fehler zurück (z.B. durch Angabe einer personID, welche nicht existiert). 
Reagieren Sie auf den Fehler im ExceptionBereich, geben Sie eine entsprechende Fehlermeldung aus und machen Sie alle vorhergehenden Deletes rückgängig
*/
