--- �bung 2: Teil 1.1 ---
CREATE VIEW PView AS  
SELECT person.vorname, person.nachname, person.geburtsdatum FROM dienstrang
JOIN flugpersonal ON dienstrang.dienstrangID = flugpersonal.dienstrangID
JOIN personal ON personal.personID = flugpersonal.personID
JOIN person ON personal.personID = person.personID
WHERE dienstrang.bezeichnung IN ('Pilot');

--- �bung 2: Teil 1.2 ---

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

--- �bung 2: Teil 1.3 ---
SELECT * FROM Personal_view ORDER BY nachname;
INSERT INTO Personal_view (vorname, nachname, monatsgehalt) values ('Max', 'Muster', 5000); --- cannot modify more than one base table through a join view
INSERT INTO Personal_view (vorname, nachname) values ('Max', 'Muster'); --- NULL kann nicht in Person.PersonID eingef�gt werden (PrimaryKey)
DELETE FROM Personal_view WHERE nachname = 'Mann' --- child record found
UPDATE Personal_view
  SET nachname = 'Austrian'
  WHERE PersonID = 90 --- update successfull

--- �bung 2: Teil 1.4 ---
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

--- �bung 2: Teil 1.5 ---
--- Index f�r flug(flughafen_abflug): query - alle fl�ge aus z.B. wien f�r die Ablfughalle
--- Index f�r flug(flughafen_destination): query - alle fl�ge nach z.B. wien f�r die Ankunftshalle
--- Index f�r flug(abflugzeit): query - alle fl�ge in der n�chsten stunde f�r eine Anzeigetafel
--- Index f�r fkugzeug(flugstunden_gesamt): query - filter alle flugzeuge, die �ber eine gewisse gesamtstunden-anzahl hinaus sind, damit ich diese in wartung schicken kann

--- �bung 2: Teil 2.1 ---
SELECT * FROM USER_SYS_PRIVS
SELECT * FROM USER_TAB_PRIVS --- WHERE LOWER(OWNER) = 's18dbsbb_171089';
SELECT * FROM USER_ROLE_PRIVS;

--- �bung 2: Teil 2.2 ---
GRANT select ON person TO s17dbsbb_bwi
GRANT select, insert ON land TO s17dbsbb_bwi
GRANT select, update(monatsgehalt) ON gehaltsstufe TO s17dbsbb_bwi
GRANT select ON PView TO s17dbsbb_bwi
GRANT select ON Personal_View TO s17dbsbb_bwi

SELECT * FROM S18DBSBB_171089.flugpersonal --- Zugriff auf eine Tablle auf welche keine Rechte vergeben wurden ->  Flugpersonal ist nicht m�glich
SELECT * FROM S18DBSBB_171089.person --- Zugriff auf Tabelle auf welche Rechte vergeben wurden -> Person ist m�glich

UPDATE S18DBSBB_171089.Personal_view
  SET nachname = 'Austrian'
  WHERE PersonID = 90 --- �nderungen auf eine View, auf welche nur lesender Zugriff vergeben wurde ist nicht m�glich
SELECT * FROM S18DBSBB_171089.Personal_view --- lesender Zugriff auf die View ist m�glich
SELECT * FROM S18DBSBB_171089.PView --- lesender Zugriff auf die zweite View ist auch m�glich

SELECT * FROM S18DBSBB_171089.land --- Zugriff nur mit INSERT Rechten nicht m�glich (Fehler: Zu wenig Rechte), �nderung auf INSERT UND SELECT
UPDATE S18DBSBB_171089.land
  SET Kennzeichen = 'AT'
  WHERE Bezeichnung = '�sterreich' --- �nderungen nicht m�glich, da nur Insert Rechte
INSERT INTO S18DBSBB_171089.land values (421, 'SVK', 'Slowakei') --- �nderung m�glich, da INSERT Rechte auf Tabelle land vorhanden

SELECT * FROM S18DBSBB_171089.gehaltsstufe --- lesender Zugriff m�glich da SELECT REchte auf die Tabelle vorhanden
INSERT INTO S18DBSBB_171089.gehaltsstufe values (1, 5000) --- HInzuf�gen von neuen Werten nicht m�glich, da keine INSERT Rechte auf diese Tabelle

--- �bung 2: Teil 2.3 ---
SELECT * FROM ALL_TABLES WHERE LOWER(OWNER) = 's18dbsbb_171089'

--- �bung 2: Teil 2.4 ---
SELECT * FROM USER_OBJECTS;

--- �bung 2: Teil 2.5 ---
DESCRIBE Personal

-- SELECT * --- TABLE_NAME, CONSTRAINT_NAME, 'Constraint'
-- FROM USER_CONSTRAINTS
-- WHERE TABLE_NAME = 'PERSONAL'
-- UNION
-- SELECT TABLE_NAME, COLUMN_NAME, DATA_Type
-- FROM User_tab_Columns
-- WHERE table_name = 'PERSONAL'

--- �bung 2: Teil 2.6 ---
CREATE USER test IDENTIFIED BY mypassword
GRANT all privileges to test
GRANT select any table, insert any table, delete any table, update any table TO test;

--- �bung 2: Teil 2.7 ---
CREATE ROLE myrole
GRANT select any table, insert any table, delete any table, update any table TO myrole;
GRANT myrole TO test;

--- �bung 2: Teil 2.8 ---
SELECT table_name, SUM(data_length) FROM user_tab_columns
GROUP BY table_name

--- �bung 2: Teil 2.9 ---
--- da sehr viele sequences und vor allem synonyme - Einschr�nkung auf all that I own --> user_... ---
--- SELECT * FROM ALL_SEQUENCES
--- SELECT * FROM ALL_SYNONYMS

SELECT sequence_name AS name, 'sequence' AS kategorie FROM USER_SEQUENCES
UNION
SELECT table_name AS name, 'table_view' AS kategorie FROM user_tab_columns
UNION
SELECT synonym_name AS name, 'synonym' AS kategorie FROM user_SYNONYMS
ORDER BY kategorie, name;



