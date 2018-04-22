CREATE OR REPLACE TRIGGER trig_del_pas BEFORE DELETE
ON passagierliste FOR EACH ROW
DECLARE
BEGIN
  DELETE FROM gepaeck WHERE personID = :old.personID AND flugID = :old.flugID;
  dbms_output.put_line('trigger ausgef�hrt - gepaeck gel�scht, da passagier gel�scht');
EXCEPTION  
  WHEN OTHERS THEN
    dbms_output.put_line('error beim trigger ' || SQLCODE);
    RAISE;
END;
/

SET serveroutput ON; 
-- Ubung 6/1 --
/*
Schreiben Sie einen Datenbanktrigger, der - im Falle des L�schens eines Passagiers - garantiert, dass auch alle Gep�ckst�cke des Passagiers entfernt werden.
*/
