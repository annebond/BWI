CREATE OR REPLACE TRIGGER trig BEFORE INSERT
ON crew FOR EACH ROW
DECLARE
  v_bezeichnung VARCHAR2(255);
  v_check NUMBER;
BEGIN
  -- überprüfe was die ge-insertete person für bezeichnung hat
  SELECT bezeichnung INTO v_bezeichnung FROM dienstrang
  JOIN flugpersonal USING(dienstrangID)
  WHERE personID = :new.personID;
  
  -- weiter macht nur sinn wenn ge-insertete person gewisse bezeichnung hat - e.g. wenn trainee macht es kein sinn den weiteren Teil auszufü
  IF v_bezeichnung IN ('Co-Pilot', 'Pilot', 'Chefpilot', 'Bordpersonal')
  THEN
    -- check wie viele piloten und raise fehler wenn mit ge-insertete person zu viel
    SELECT count(*) INTO v_check FROM crew
    JOIN flugpersonal USING(personID)
    JOIN dienstrang USING(dienstrangID)
    WHERE flugID = :new.flugID AND bezeichnung IN ('Chefpilot', 'Pilot');   
    
    IF v_check >=1 AND v_bezeichnung IN ('Chefpilot', 'Pilot') THEN
      RAISE_APPLICATION_ERROR(-20001, 'Zu viele Piloten für den Flug: ' || :new.flugid);
    END IF;
    
    -- check wie viele copiloten und raise fehler wenn mit ge-insertete person zu viel
    SELECT count(*) INTO v_check FROM crew
    JOIN flugpersonal USING(personID)
    JOIN dienstrang USING(dienstrangID)
    WHERE flugID = :new.flugID AND bezeichnung IN ('Co-Pilot');   
    
    IF v_check >=2 AND v_bezeichnung IN ('Co-Pilot') THEN
      RAISE_APPLICATION_ERROR(-20002, 'Zu viele Co-Piloten für den Flug: ' || :new.flugid);
    END IF;
    
    -- check wie viel bordpersonal und raise fehler wenn mit ge-insertete person zu viel
    SELECT count(*) INTO v_check FROM crew
    JOIN flugpersonal USING(personID)
    JOIN dienstrang USING(dienstrangID)
    WHERE flugID = :new.flugID AND bezeichnung IN ('Bordpersonal');   
    
    IF v_check >=6 AND v_bezeichnung IN ('Bordpersonal') THEN
      RAISE_APPLICATION_ERROR(-20003, 'Zu viele Stewarts für den Flug: ' || :new.flugid);
    END IF;
      
  END IF; 

EXCEPTION
  WHEN OTHERS THEN RAISE;
END;
/

--------------------------- TESTS BEGIN ---------------------------
SELECT * FROM CREW
JOIN FLUGPERSONAL USING(personID)
JOIN dienstrang USING(dienstrangID)
WHERE flugID = 16;

-- hinzufügen von co-pilot obwohl schon max zahl erreich:
INSERT INTO CREW VALUES (16, 92);
--------------------------- TESTS END ---------------------------

SET serveroutput ON; 

-- Ubung 6/2 --
/*
Schreiben Sie einen Datenbanktrigger, der beim Hinzufügen von Personal zu einem Flug überprüft, dass nicht mehr als 1 Pilot (oder Chefpilot), 2 Copiloten und 6 Stewarts/Stewardessen zugeteilt sind. 
*/
