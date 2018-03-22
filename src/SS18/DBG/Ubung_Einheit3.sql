SET serveroutput ON; 

CREATE OR REPLACE PROCEDURE halloword (v_personID integer)
IS
-- DECLARE
	v_name varchar(30);
	v_count integer;
-- v_personID integer := 1;

Begin
	SELECT count(*) INTO v_count
	FROM person
  WHERE personID = v_personID;
	
	IF v_count = 1 THEN
		SELECT vorname INTO v_name
		FROM person
    WHERE personID = v_personID;
		dbms_output.put_line('Hallo ' || v_name);
	ELSE 
		dbms_output.put_line('Keine person gefunden');
	END IF;
End;
/

EXEC halloword (33);