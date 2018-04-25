-- 1) --------------------------------------------------

create or replace function charge_baggage (
	i_flugID passagierliste.flugID%type,
	i_personID passagierliste.personID%type)
return number
is
  v_count integer;
  v_gewicht number;
begin
	select count(*) into v_count
	from passagierliste
	where flugID = i_flugID
	and personID = i_personID;
	
	if v_count < 1 then
		return -1;
	end if;
	
	-- COALESCE (expr1, expr2) is equivalent to: CASE WHEN expr1 IS NOT NULL THEN expr1 ELSE expr2 END
	select coalesce(sum(gewicht),0) into v_gewicht from gepaeck
	where flugID=i_flugID and personid=i_personid;

	-- optional aufrunden: v_gewicht := round(v_gewicht - 20);
	v_gewicht := v_gewicht - 20;
	if v_gewicht>0 then
		v_gewicht:=v_gewicht*2.5;
	else 
		v_gewicht:=0;
	end if; 
	
	return (v_gewicht);
	
  exception
    when others then
      return -1;
  
end;
/

select charge_baggage(45,333) from dual;
select charge_baggage(45,300) from dual;
select charge_baggage(45,301) from dual;


-- 2) --------------------------------------------------

create view passagier as
select personID, flugID, vorname, nachname, sitzplatznummer, 
       coalesce(sum(gewicht),0) gesamtgewicht
from person join passagierliste using(personID)
            left join gepaeck using(personID, flugID)
group by personID, flugID, vorname, nachname, sitzplatznummer;


create or replace trigger trigger_passenger
instead of insert on passagier
for each row
declare
  v_help integer;
  v_personID person.personID%type;

begin
  
  -- check if flight is existing
  begin
  	select flugID into v_help from flug 
  	where flugID = :new.flugID;
  exception
    when NO_DATA_FOUND then
    	raise_application_error(-20001,'flugID existiert nicht');
  end;
  
  -- check if person is existing and unique
  begin
  	select personID into v_personID
  	from person 
  	where nachname = :new.nachname
  	and vorname = :new.vorname;
  exception
    when NO_DATA_FOUND or TOO_MANY_ROWS then
    	raise_application_error(-20002,'Person existiert nicht oder ist nicht eindeutig');
  end;
  
	-- check if person is already booked on flight
	select count(*) into v_help
	from passagierliste 
	where personID = v_personID
	and flugID = :new.flugID;
	
	if v_help > 0 then
    	raise_application_error(-20003,'Person bereits auf Flug gebucht');
  end if;
 
	-- check if seat is available
	select count(*) into v_help
	from passagierliste 
	where upper(sitzplatznummer) = upper(:new.sitzplatznummer)
	and flugID = :new.flugID;
	

	if v_help > 0 then
    	raise_application_error(-20004,'Sitzplatz nicht mehr verfügbar');
  end if;

  insert into passagierliste values(:new.flugID, v_personID, :new.sitzplatznummer);

end;
/

-- Testcases
insert into passagier(flugID, vorname, nachname, sitzplatznummer) values(2,'Herbert','Maier','6A');  -- flugID existiert nicht
insert into passagier(flugID, vorname, nachname, sitzplatznummer) values(1,'Max','Maier','6A'); -- person existiert nicht
insert into passagier(flugID, vorname, nachname, sitzplatznummer) values(1,'Herbert','Maier','6A'); -- person ist bereits Passagier
insert into passagier(flugID, vorname, nachname, sitzplatznummer) values(16,'Herbert','Maier','1'); -- Sitzplatz bereits belegt
insert into passagier(flugID, vorname, nachname, sitzplatznummer) values(16,'Herbert','Maier','15');  -- ist ok (kein Fehler)






-- 3) --------------------------------------------------

create or replace procedure print_flight_list (i_minPassAnz integer)
is
	cursor c_fluege is
		select flugNummer, bezeichnung, count(*) passagierAnzahl
		from flug join passagierliste using(flugID)
		     join flughafen on flughafen_abflug=flughafenID
		group by flugNummer, bezeichnung having count(*) > i_minPassAnz;
	v_praefix varchar(2);
begin
  for v_result in c_fluege loop
  	if v_result.bezeichnung = 'Wien-Schwechat' then
  		v_praefix := '* ';
  	else
  	  v_praefix := '';
  	end if;
  	dbms_output.put_line(v_praefix||
  	                     'Flug '||v_result.flugNummer||
  	                     ' von '||v_result.bezeichnung||
  	                     ' mit '||v_result.passagierAnzahl||' Passagieren');
  end loop;
end;
/

exec print_flight_list(3);
