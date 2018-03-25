SET serveroutput ON; 
CREATE OR REPLACE PROCEDURE PersonSitzplatz (v_sitzplatz VARCHAR2,  v_flugnummer VARCHAR2)
IS
/*  
  v_flugnummer VARCHAR2;
  v_sitzplatz VARCHAR2;
*/
  v_personID NUMBER;
  v_count INT;
  v_flugID NUMBER;
  v_name VARCHAR2(550);
  v_reisepassnr VARCHAR2(20);
  v_gewicht NUMBER;
  v_bezeichnung VARCHAR2(255);

BEGIN
	-- pr�fe wie oft flugnummer in flug vorhanden ist
  SELECT count(*) INTO v_count
	FROM flug
  WHERE flugnummer = v_flugnummer;
  
  -- wenn flugnummer 1x vorhanden ist, speicher zugrh�rige flugID
	IF v_count = 1 THEN
		SELECT flugID INTO v_flugID
		FROM flug
    WHERE flugnummer = v_flugnummer;
    -- DBMS_OUTPUT.PUT_LINE(v_flugID);
    
    -- pr�fe wie oft (gespeicherte flugID und mitgegebene sitzplatznummer) vorhanden ist
    SELECT count(*) INTO v_count
    FROM passagierliste
    WHERE flugID = v_flugID AND sitzplatznummer = v_sitzplatz;
    
    -- wenn (gespeicherte flugID und mitgegebene sitzplatznummer) 1x  vorhanden ist, speicher personID
    IF v_count = 0 THEN
      DBMS_OUTPUT.PUT_LINE('0 Platz ist frei');
      ELSIF v_count = 1 THEN
        SELECT personID INTO v_personID
        FROM passagierliste
        WHERE flugID = v_flugID AND sitzplatznummer = v_sitzplatz;

        SELECT vorname || ' ' || nachname INTO v_name FROM person
        WHERE personID = v_personID;
      
        SELECT COUNT(*) INTO v_count
        FROM reisepass
        WHERE personID = v_personID;
      
        -- wird nur durchlaufen, wenn es f�r die PersonID nur einen Eintrag in reisepass gibt (eine Person kann auch mehrere Reisep�sse haben)
        IF v_count = 1 THEN
          SELECT reisepassnr, land.bezeichnung INTO v_reisepassnr, v_bezeichnung FROM reisepass
          JOIN land ON land.landID = reisepass.landID
          WHERE personID = v_personID;
        
          SELECT sum(gewicht) INTO v_gewicht FROM gepaeck
          WHERE flugID = v_flugID AND personID = v_personID;
          DBMS_OUTPUT.PUT_LINE('Flugnummer: ' || v_flugnummer || ', Passagiername: ' || v_name || ', Sitzplatz: ' || v_sitzplatz || ', Landeszugeh�rigkeit: ' || v_bezeichnung || ', Reisepassnummer: ' || v_reisepassnr || ', Gep�ck-Gesamtgewicht: ' || v_gewicht);
        ELSE
          DBMS_OUTPUT.PUT_LINE('-1 Mehrere Reisepassnummern f�r Person auf Sitzplatz: ' || v_sitzplatz);
        END IF;
    ELSE 
      DBMS_OUTPUT.PUT_LINE('Sitzplatznummer auf dem Flug nicht gefunden');
    END IF;
  ELSE
    DBMS_OUTPUT.PUT_LINE('-1 Flugnummer nicht gefunden');
  END IF;   
END;
/


-- Bsp. EXEC PersonSitzplatz ('14C', 'OS1467');
-- Bsp. f�r Person mit mehreren Reisep�ssen: EXEC PersonSitzplatz ('12A', 'OS1467');
-- Bsp. f�r Person ohne Gep�ck: EXEC PersonSitzplatz ('1B', 'OS1467');


-- Ubung 3/2 --
/* Schreiben Sie eine Datenbankprozedur, welche die Daten eines Passagiers auf einem bestimmten Sitzplatz eines Fluges ausgibt. 
Deklarieren Sie dazu mindestens die beiden Variablen v_flugnummer und v_sitzplatz und weisen Sie diesen Werte zu. Ausgegeben werden sollen die Flugnummer,
der Passagiername, sein Sitzplatz, seine Landeszugeh�rigkeit (sofern vorhanden), seine Reisepassnummer und das Gesamtgewicht seines Gep�cks.
Ist der Platz noch frei, geben Sie 0 aus. �berlegen Sie sich wo und wie es zu Fehlerf�llen kommen kann (z.B. mehrere Reisepassnummern).
�berpr�fen Sie auf diese Fehlerf�lle und geben, wenn einer Eintritt, -1 aus. Verwenden Sie noch kein explizites Exception Handling! */
