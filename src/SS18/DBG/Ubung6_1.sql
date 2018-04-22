CREATE OR REPLACE TRIGGER trig_del_pas BEFORE DELETE
ON passagierliste FOR EACH ROW
DECLARE
BEGIN
  DELETE FROM gepaeck WHERE personID = :old.personID AND flugID = :old.flugID;
  dbms_output.put_line('trigger ausgeführt - gepaeck gelöscht, da passagier gelöscht');
EXCEPTION  
  WHEN OTHERS THEN
    dbms_output.put_line('error beim trigger ' || SQLCODE);
    RAISE;
END;
/

SET serveroutput ON; 
-- Ubung 6/1 --
/*
Schreiben Sie einen Datenbanktrigger, der - im Falle des Löschens eines Passagiers - garantiert, dass auch alle Gepäckstücke des Passagiers entfernt werden.
*/
