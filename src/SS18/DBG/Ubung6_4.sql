CREATE SEQUENCE flugID_seq
  START WITH 46
  INCREMENT BY 1
  CACHE 20;

CREATE SEQUENCE gepaeckID_seq
  START WITH 20
  INCREMENT BY 1
  CACHE 20;
   
select * from ALL_SEQUENCES;
-- ========================================================================
-- ========================================================================

CREATE OR REPLACE PACKAGE test AS
PROCEDURE gep (v_personID NUMBER, v_flugID NUMBER, v_flugID_neu NUMBER);
PROCEDURE delFlug (v_flugID NUMBER);
END test;
/

-- package body

CREATE OR REPLACE PACKAGE BODY test AS
PROCEDURE gep (v_personID NUMBER, v_flugID NUMBER, v_flugID_neu NUMBER) IS
  v_gepaeckID NUMBER;
  v_gewicht NUMBER;
  CURSOR egep IS
    SELECT * FROM gepaeck
    WHERE personID = v_personID AND flugID = v_flugID;
BEGIN  
  FOR vResult2 IN egep
    LOOP
      v_gewicht := vResult2.gewicht;
      SELECT gepaeckID_seq.NEXTVAL INTO v_gepaeckID FROM dual;
      INSERT INTO gepaeck VALUES (v_gepaeckID, v_personID, v_flugID_neu, v_gewicht);
      -- ggf. gibt es personID in gep�ck nicht -> egal, da dann einfach 0 rows effected
    END LOOP;
    DELETE FROM gepaeck WHERE personID = v_personID AND flugID = v_flugID;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN NULL;
END;


PROCEDURE delFlug (v_flugID NUMBER) AS
  v_abflug NUMBER;
  v_destination NUMBER;
  v_gateID NUMBER;
  v_flugzeugID NUMBER;
  v_flugID_neu NUMBER;
  v_personID NUMBER;
  v_sitzplatznummer VARCHAR2(20);
  
  v_test NUMBER;
  
  CURSOR passagier IS
    SELECT personID, sitzplatznummer FROM passagierliste 
    WHERE flugID = v_flugID;  
  
  speziellerFlug EXCEPTION;  
  
BEGIN
savepoint startDelFlug;

  -- falls flugID nicht vorhanden -> exception handling no data
  SELECT flughafen_abflug, flughafen_destination, gateID, flug.flugzeugID  INTO v_abflug, v_destination, v_gateID, v_flugzeugID FROM flug
  JOIN flugzeug ON flugzeug.flugzeugID = flug.flugzeugID
  WHERE flugID = v_flugID;
  
  -- neuen Flug erstellen
  SELECT flugID_seq.NEXTVAL INTO v_flugID_neu FROM dual;
  INSERT INTO flug (flugID, flugzeugID, gateID, flughafen_abflug, flughafen_destination) VALUES (v_flugID_neu, v_flugzeugID, v_gateID, v_abflug, v_destination);
  DBMS_OUTPUT.PUT_LINE('neue FlugID erstellt');
  
  -- passagiere vom alten auf neuen Flug umbuchen
  FOR vResult IN passagier
  LOOP
    v_personID := vResult.personID;
    v_sitzplatznummer := vResult.sitzplatznummer;
    INSERT INTO passagierliste VALUES (v_flugID_neu, v_personID, v_sitzplatznummer);
    -- gepaeck von einem passagier umbuchen auf neuen flug
   gep (v_personID, v_flugID, v_flugID_neu);
  END LOOP;
  -- optional falls UPDATE von gepaeck auch OK (statt alle alten eintr�ge l�schen und umbuchen/duplizieren):
  -- UPDATE gepaeck SET flugID = v_flugID_neu WHERE flugID = v_flugID;
  DELETE FROM passagierliste WHERE flugID = v_flugID;
  DBMS_OUTPUT.PUT_LINE ('Alle passagiere umgebucht und passagierliste des Fluges gel�scht.');

  -- wenn flugID = 106 raise exception
  IF v_flugID = 106 THEN
    RAISE speziellerFlug; 
  END IF; 

--v_test := 1/0;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
      DBMS_OUTPUT.PUT_LINE(v_flugID || ' -> FlugID. Datensatz nicht gefunden. '|| SQLCODE || ' Error-Meldung: ' || SQLERRM);
      rollback to savepoint startDelFlug;
    WHEN speziellerFlug THEN
      RAISE_APPLICATION_ERROR(-20001, 'Dieser Flug darf nicht gecancelt werden. Alle �nderungen werden zur�ckgesetzt. FlugID: ' || v_flugID);
      rollback to savepoint startDelFlug;
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Unbekannter Fehler. Alle �nderungen werden zur�ckgesetzt.'||  SQLCODE || ' Error-Meldung: ' || SQLERRM);
      rollback to savepoint startDelFlug;
END;
END;
/

------------------ TESTS BEGIN -------------------------------
EXEC test.delFlug(106);
select * from passagierliste
WHERE flugID = 106;

select * from logs
WHERE flugID = 106;

SET serveroutput ON; 
------------------ TESTS END -------------------------------

-- Ubung 6/4 --
/*
Pr�fen Sie anhand Aufgabe 4 aus �bung 5 (ganzer Flug wird gecancelt), ob obiger Trigger aus Beispiel 3 tats�chlich funktioniert. 
�berlegen Sie sich eine Situation, wo am Ende der Umbuchung ein Rollback durchgef�hrt wird (Prozedur entsprechend anpassen). �berpr�fen Sie, ob das Logging trotzdem stattfindet! 
*/
