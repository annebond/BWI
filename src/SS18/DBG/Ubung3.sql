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


