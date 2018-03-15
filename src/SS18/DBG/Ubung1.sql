INSERT INTO land SELECT * FROM flughafenbase.land;
INSERT INTO person SELECT * FROM flughafenbase.person;
INSERT INTO gehaltsstufe SELECT * FROM flughafenbase.gehaltsstufe;
INSERT INTO abteilung SELECT * FROM flughafenbase.abteilung;
INSERT INTO dienstrang SELECT * FROM flughafenbase.dienstrang;
INSERT INTO fluglinie SELECT * FROM flughafenbase.fluglinie;
INSERT INTO flughafen SELECT * FROM flughafenbase.flughafen;
INSERT INTO flugzeugtyp SELECT * FROM flughafenbase.flugzeugtyp;
INSERT INTO reisepass SELECT * FROM flughafenbase.reisepass;
INSERT INTO ort SELECT * FROM flughafenbase.ort;
INSERT INTO gate SELECT * FROM flughafenbase.gate;
INSERT INTO flugzeug SELECT * FROM flughafenbase.flugzeug;
INSERT INTO personal SELECT * FROM flughafenbase.personal;
INSERT INTO bodenpersonal SELECT * FROM flughafenbase.bodenpersonal;
INSERT INTO flugpersonal SELECT * FROM flughafenbase.flugpersonal;
INSERT INTO flug SELECT * FROM flughafenbase.flug;
INSERT INTO passagierliste SELECT * FROM flughafenbase.passagierliste;
INSERT INTO crew SELECT * FROM flughafenbase.crew;
INSERT INTO gepaeck SELECT * FROM flughafenbase.gepaeck;


--- ‹bung 1: 1 ---
--- ABS - absolut/ normalisieren, da im DIF Werte positiv und negativ zur¸ckkommen kˆnnen ---
--- ORDER BY und WHERE funktionieren in einem SELECT nicht ---
--- The where statement gets executed before the order by. W¸rde bedeuten nimm rownum 1 und mach order by ---

SELECT * FROM
(SELECT flugzeugID,
ABS((SELECT AVG(flugstunden_gesamt) FROM flugzeug) - flugstunden_gesamt) as DIF
FROM flugzeug
ORDER BY DIF DESC)
WHERE rownum = 1;


--- ‹bung 1: 2 ---
SELECT vorname, nachname FROM (
SELECT vorname, nachname, mod(rownum,2) as inc FROM
(SELECT * FROM person
ORDER BY nachname))
WHERE inc = 0;

--- ‹bung 1: 3 ---
SELECT vorname, nachname, gehaltsstufe.monatsgehalt
FROM person
JOIN personal ON person.personID = personal.personID
JOIN gehaltsstufe on personal.gehaltsstufeID = gehaltsstufe.gehaltsstufeID
WHERE gehaltsstufe.monatsgehalt > 
  (SELECT monatsgehalt
  FROM person
  JOIN personal ON person.personID = personal.personID
  JOIN gehaltsstufe on personal.gehaltsstufeID = gehaltsstufe.gehaltsstufeID
  WHERE nachname = 'Luttkus' AND vorname = 'Nikolaus');
 
--- ‹bung 1: 4 ---
--- months_between - age as decimal number in years ---
SELECT *
FROM (SELECT 
      vorname, 
      nachname ,
      reisepass.landID, 
      months_between (sysdate, geburtsdatum)/12  AS age,
      AVG(months_between (sysdate, geburtsdatum)/12) OVER (PARTITION BY reisepass.landID) AS avg_for_landID
      FROM person
      JOIN reisepass ON person.personID = reisepass.personID
    )
WHERE age > avg_for_landID;

--- ‹bung 1: 5 ---
--- Annahme: Piloten sind Flugpersonal mit Dienstrang-Bezeichnung Pilot, Chefpilot, Co-Pilot ---
--- Annahme: Gep‰ckcrew ist bodenpersonal in der Abteilung mit Bezeichnung Gep‰ckdienst ---

SELECT 'Flugpersonal' AS Gruppe, SUM(Jahresgehalt) AS Jahresgehalt FROM 
(
  SELECT SUM(monatsgehalt)*15 AS Jahresgehalt FROM dienstrang
  JOIN flugpersonal ON dienstrang.dienstrangID = flugpersonal.dienstrangID
  JOIN personal ON personal.personID = flugpersonal.personID
  JOIN gehaltsstufe ON gehaltsstufe.gehaltsstufeID = personal.gehaltsstufeID
  WHERE Bezeichnung IN ('Pilot', 'Chefpilot', 'Co-Pilot')
  UNION
  SELECT SUM(monatsgehalt)*14 AS Jahresgehalt FROM dienstrang
  JOIN flugpersonal ON dienstrang.dienstrangID = flugpersonal.dienstrangID
  JOIN personal ON personal.personID = flugpersonal.personID
  JOIN gehaltsstufe ON gehaltsstufe.gehaltsstufeID = personal.gehaltsstufeID
  WHERE Bezeichnung NOT IN ('Pilot', 'Chefpilot', 'Co-Pilot')
)
UNION
SELECT 'Bodenpersonal' AS Gruppe, SUM(Jahresgehalt) AS Jahresgehalt FROM 
(
  SELECT SUM(monatsgehalt)*12 AS Jahresgehalt FROM abteilung
  JOIN bodenpersonal ON bodenpersonal.abteilungsID = abteilung.abteilungsID
  JOIN personal ON personal.personID = bodenpersonal.personID
  JOIN gehaltsstufe ON gehaltsstufe.gehaltsstufeID = personal.gehaltsstufeID
  WHERE Bezeichnung IN ('Gep‰ckdienst')
  UNION
  SELECT SUM(monatsgehalt)*14 AS Jahresgehalt FROM abteilung
  JOIN bodenpersonal ON bodenpersonal.abteilungsID = abteilung.abteilungsID
  JOIN personal ON personal.personID = bodenpersonal.personID
  JOIN gehaltsstufe ON gehaltsstufe.gehaltsstufeID = personal.gehaltsstufeID
  WHERE Bezeichnung NOT IN ('Gep‰ckdienst')
);


--- ‹bung 1: 6 ---
--- innerhalb von p gruppiere ich nach destination, auﬂerhalb nach personID. somit bekomme ich zu einer personID BCN und/oder PMI eintr‰ge ---
--- group by p.personID mit having <=1 stellt sicher, dass wenn eine person in BCN und PMI war (w‰re having 2) rausf‰llt ---
--- gruppieren in p nach personID stellt sicher, dass wenn eine person 2x in BCN/PMI war nicht 2 eintr‰ge angezeigt werden ---
SELECT person.vorname, person.nachname, p.personID FROM (
  SELECT flug.flughafen_destination, passagierliste.personID FROM flughafen
  JOIN flug ON flughafen.flughafenID = flug.flughafen_destination
  JOIN passagierliste ON passagierliste.flugID = flug.flugID
  WHERE flughafen.KBZ IN ('BCN', 'PMI')
  GROUP BY flug.flughafen_destination, passagierliste.personID
  ) p
JOIN person ON person.personID = p.personID
GROUP BY p.personID, person.vorname, person.nachname
HAVING count(p.personID) <=1;

