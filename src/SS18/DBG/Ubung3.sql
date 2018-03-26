-- Ubung 3/1 --
/* Schreiben Sie einen anonymen Block, welcher eine Variable ?v_personID" deklariert, 
diese mit dem höchsten Eintrag der personID aus der Tabelle person befüllt und ausgibt. */
SET serveroutput ON; 
/

DECLARE 
  v_personID NUMBER;
BEGIN
  SELECT max(personID) INTO v_personID FROM person;  
  DBMS_OUTPUT.PUT_LINE(v_personID);
END;
/

-- Ubung 3/2 --
/* Schreiben Sie einen anonymen Block, welcher das Verhältnis zwischen Flugpersonal und Bodenpersonal ausgibt 
(z.B.: "flugpersonal : bodenpersonal = 10:24"). Verwenden Sie dafür mindestens 2 verschiedene Select Statements. */

DECLARE 
  v_flugp INT;
  v_bodenp INT;
BEGIN
  SELECT count(personID) INTO v_flugp FROM flugpersonal;
  SELECT count(personID) INTO v_bodenp FROM bodenpersonal;
  DBMS_OUTPUT.PUT_LINE('flugpersonal : bodenpersonal = ' || v_flugp || ':' || v_bodenp);
END;
/

-- Ubung 3/4 --
/* Schreiben Sie eine Datenbankfunktion, welche die Anzahl der verschiedenen Ortsnamen zurück gibt. 
Sollten keine Namen verfügbar sein, dann soll -1 zurück gegeben werden.  */

CREATE OR REPLACE FUNCTION AnzahlOrt
RETURN INT
IS
  v_ort_anzahl INT;
BEGIN
  SELECT COUNT(DISTINCT bezeichnung) into v_ort_anzahl FROM ort;
  IF v_ort_anzahl = 0 THEN
    v_ort_anzahl := -1;
  END IF;
  return v_ort_anzahl;
END;
/
SELECT AnzahlOrt FROM dual;

-- Ubung 3/5 --
/* Schreiben Sie eine Datenbankprozedur, welche alle Dienstränge (dienstrangID, kbz und bezeichnung) für die IDs von 100 bis 110 ausschreibt. 
Sollte eine dienstrangID in diesem Bereich nicht vorhanden sein, dann schreiben Sie stattdessen "kein Dienstrang verfügbar für ID: xxx" aus.  */

CREATE OR REPLACE PROCEDURE DienstrangAusgabe
IS
  v_kbz VARCHAR2(20);
  v_bezeichnung VARCHAR2(255);
  v_count INT;
BEGIN
  FOR i IN 100..110 LOOP
      SELECT COUNT(dienstrangID) INTO v_count FROM dienstrang
      WHERE dienstrangID = i;
      IF v_count = 1 THEN
            SELECT kbz, bezeichnung INTO v_kbz, v_bezeichnung FROM dienstrang
            WHERE dienstrangID = i;
            DBMS_OUTPUT.PUT_LINE(i || ' ' ||v_kbz || ' '  || v_bezeichnung);
      ELSE
        DBMS_OUTPUT.PUT_LINE('Kein Dienstrang verfügbar für ID: ' || i);
      END IF;
  END LOOP;
END;
/

EXEC DienstrangAusgabe;
