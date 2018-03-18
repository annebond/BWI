--- Übung 2: Teil 1.1 ---
CREATE VIEW PView AS  
SELECT person.vorname, person.nachname, person.geburtsdatum FROM dienstrang
JOIN flugpersonal ON dienstrang.dienstrangID = flugpersonal.dienstrangID
JOIN personal ON personal.personID = flugpersonal.personID
JOIN person ON personal.personID = person.personID
WHERE dienstrang.bezeichnung IN ('Pilot');

--- Übung 2: Teil 1.2 ---

CREATE VIEW Personal_view AS
SELECT personID, person.vorname, person.nachname, gehaltsstufe.monatsgehalt, 
  ((SELECT Bezeichnung FROM dienstrang
  JOIN flugpersonal ON flugpersonal.dienstrangID = dienstrang.dienstrangID
  WHERE flugpersonal.personID = personal.personID)
  UNION
  (SELECT Bezeichnung FROM abteilung
  JOIN bodenpersonal ON bodenpersonal.abteilungsID = abteilung.abteilungsID
  WHERE bodenpersonal.personID = personal.personID))
  AS Bezeichnung
FROM personal
JOIN gehaltsstufe ON gehaltsstufe.gehaltsstufeID = personal.gehaltsstufeID
JOIN person ON person.personID = personal.personID;

--- Übung 2: Teil 1.3 ---
SELECT * FROM Personal_view ORDER BY nachname;
INSERT INTO Personal_view (vorname, nachname, monatsgehalt) values ('Max', 'Muster', 5000); --- cannot modify more than one base table through a join view
INSERT INTO Personal_view (vorname, nachname) values ('Max', 'Muster'); --- NULL kann nicht in Person.PersonID eingefügt werden (PrimaryKey)
DELETE FROM Personal_view WHERE nachname = 'Mann' --- child record found
UPDATE Personal_view
  SET nachname = 'Austrian'
  WHERE PersonID = 90 --- update successfull

--- Übung 2: Teil 1.4 ---
-- SELECT *
-- FROM personal_view
-- WHERE monatsgehalt > 3000 AND
--  rang='Chefpilot' AND
--  UPPER(nachname) < 'G'
-- ORDER BY nachname, vorname;

--- No Indexes on small tables (5-10 Blocks)? ---
--- Index on Nachname: helpfull for Order By, not for Upper(nachname) < G --> function, existiert nicht zum Zeitpunk der Index Bildung ---
--- 1 Index for Nachname, Vorname. Annahme: Oft wird diese Kombi abgefragt, selten Vorname, Nachname oder nur Vorname/Nachname

CREATE INDEX Name ON person (nachname, vorname);
CREATE INDEX Monatsgehalt ON gehaltsstufe(monatsgehalt);
CREATE INDEX Rang ON dienstrang(bezeichnung);

--- Übung 2: Teil 1.5 ---
--- Index für flug(flughafen_abflug): query - alle flüge aus z.B. wien für die Ablfughalle
--- Index für flug(flughafen_destination): query - alle flüge nach z.B. wien für die Ankunftshalle
--- Index für flug(abflugzeit): query - alle flüge in der nächsten stunde für eine Anzeigetafel
--- Index für fkugzeug(flugstunden_gesamt): query - filter alle flugzeuge, die über eine gewisse gesamtstunden-anzahl hinaus sind, damit ich diese in wartung schicken kann

--- Übung 2: Teil 2.1 ---




