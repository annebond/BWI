--- �bung 2: Teil 1.1 ---
--- Erstellen Sie eine View, welche Vorname, Nachname und Geburtsdatum aller Piloten (nicht Co-Piloten und Chefpiloten) anzeigt. ---

CREATE VIEW PView AS  
SELECT person.vorname, person.nachname, person.geburtsdatum FROM dienstrang
JOIN flugpersonal ON dienstrang.dienstrangID = flugpersonal.dienstrangID
JOIN personal ON personal.personID = flugpersonal.personID
JOIN person ON personal.personID = person.personID
WHERE dienstrang.bezeichnung IN ('Pilot');

--- �bung 2: Teil 1.2 ---
-- Erstellen Sie eine View "Personal_view", welche f�r jedes Mitglied aus dem Personal (personID, Vorname, Nachname, monatliches Gehalt) den Rang (Bezeichnung) im Falle von Flugpersonal bzw. den Abteilungsnamen (Bezeichnung) im Falle von Bodenpersonal angibt. ---

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
-- Versuchen Sie auf der View "Personal_view" zu selecten, inserten, updaten, deleten. Protokollieren Sie, was passiert. ---

SELECT * FROM Personal_view ORDER BY nachname;
INSERT INTO Personal_view (vorname, nachname, monatsgehalt) values ('Max', 'Muster', 5000); --- cannot modify more than one base table through a join view
INSERT INTO Personal_view (vorname, nachname) values ('Max', 'Muster'); --- NULL kann nicht in Person.PersonID eingef�gt werden (PrimaryKey)
DELETE FROM Personal_view WHERE nachname = 'Mann' --- child record found
UPDATE Personal_view
  SET nachname = 'Austrian'
  WHERE PersonID = 90 --- update successfull

--- �bung 2: Teil 1.4 ---
-- Welche Indexes m�ssen gesetzt werden (auf den Originaltabellen !!!) um die Performance m�glichst weit zu erh�hen. ---
-- Geben Sie SQL-Anweisungen zum Erstellen der Indexes an und geben Sie Argumente f�r jeden Index an. ---

-- SELECT *
-- FROM personal_view
-- WHERE monatsgehalt > 3000 AND
--  rang='Chefpilot' AND
--  UPPER(nachname) < 'G'
-- ORDER BY nachname, vorname;

--- No Indexes on small tables (5-10 Blocks)? ---
--- Index on Nachname: helpfull for Order By, not for Upper(nachname) < G --> function, existiert nicht zum Zeitpunk der Index Bildung ---
--- 1 Index for Nachname, Vorname. Annahme: Oft wird diese Kombi abgefragt, selten Vorname, Nachname oder nur Vorname/Nachname
--- Index auf Fremdschl�ssel ist eine �berlegung wert - da personal view auf JOINs basiert

CREATE INDEX Name ON person (nachname, vorname);
CREATE INDEX Monatsgehalt ON gehaltsstufe(monatsgehalt);
CREATE INDEX Rang ON dienstrang(bezeichnung);

--- �bung 2: Teil 1.5 ---
--- Im Bereich der Fl�ge sind eine Reihe sinnvoller Abfragen denkbar (z.B. die Anzeige der aktuellen F�ge an den Terminals oder Sitzplatzbuchungen etc.). --- 
--- �berlegen Sie sich mind. 4 verschiedene Indexes, die in diesem Zusammenhang sinnvoll sein k�nnten. Geben Sie f�r jeden Index eine (frei formulierte) Begr�ndung an, warum der Index Ihrer Meinung nach wichtig ist. ---

--- Index f�r flug(flughafen_destination): query - alle fl�ge nach z.B. wien f�r die Ankunftshalle
--- Index f�r flug(abflugzeit): query - alle fl�ge in der n�chsten stunde f�r eine Anzeigetafel
--- Index f�r fkugzeug(flugstunden_gesamt): query - filter alle flugzeuge, die �ber eine gewisse gesamtstunden-anzahl hinaus sind, damit ich diese in wartung schicken kann

--- �bung 2: Teil 2.1 ---
--- Finden Sie heraus, welche Rechte Sie mit Ihrem eigenen Account haben und listen Sie diese auf (SQL-statement) --- 

SELECT * FROM USER_SYS_PRIVS; --- returns a list with system privileges of the current user, rollen rechte sind hier nicht ber�cksichtigt ---
SELECT * FROM USER_TAB_PRIVS; --- rights on tables von au�en. rechte auf eigenen tabellen nicht dabei ---
SELECT * FROM USER_ROLE_PRIVS; --- roles assigned to current user
SELECT * FROM role_sys_privs; --- was in den rollen drin ist ----
SELECT * FROM session_privs; --- rechte auf der session (inkl. rollen, ...) ---

--- �bung 2: Teil 2.2 ---
--- W�hlen Sie 3 beliebige Tabellen aus Ihrem Account und die beiden Views aus Teil 1. Geben Sie dem User unter oben angegebenem Account unterschiedliche Rechte auf diese Datenbankobjekte (nur lesen, lesen und schreiben, �). Wechseln Sie zum oben angegebenen User und �berpr�fen Sie, ob alles wie geplant funktioniert. Dokumentieren Sie was Sie machen und was Sie dabei beobachten. ----

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
--- Schreiben Sie ein select-Statement, das alle Tabellen auflistet, welche Sie selbst besitzen. ---

SELECT * FROM ALL_TABLES WHERE LOWER(OWNER) = 's18dbsbb_171089'

--- �bung 2: Teil 2.4 ---
--- Schreiben Sie ein select-Statement, das alle Datenbankobjekte auflistet, zu denen Sie Zugang haben (nicht nur solche, die Sie selbst besitzen). ---

SELECT * FROM USER_OBJECTS; --- w�re nur die, die mir geh�ren
SELECT * FROM ALL_OBJECTS; --- alle, auch die die nicht nur mir geh�ren

--- �bung 2: Teil 2.5 ---
--- Geben Sie f�r eine Tabelle Ihrer Wahl, welche Sie selbst besitzen, den Tabellennamen, die zugeh�rigen Attribute mit ihren Namen und Datentypen, sowie eventuell bestehende Constraintnamen dazu aus.---

DESCRIBE Personal --- reicht nicht aus, da constraints nicht dabei

SELECT TABLE_NAME, CONSTRAINT_NAME, 'Constraint'
FROM USER_CONSTRAINTS
WHERE TABLE_NAME = 'PERSONAL'
UNION
SELECT TABLE_NAME, COLUMN_NAME, DATA_Type
FROM User_tab_Columns
WHERE table_name = 'PERSONAL'


--- �bung 2: Teil 2.6 ---
--- Wenn Sie einen neuen User anlegen m�ssten (Sie haben nicht die entsprechenden Rechte dazu !) mit Login-Rechten und grundlegenden Berechtigungen (hinzuf�gen, �ndern, l�schen von Tabellen und Daten), welche Rechtevergabe w�re daf�r notwendig? ---

CREATE USER test IDENTIFIED BY mypassword
GRANT all privileges to test
GRANT select any table, insert any table, delete any table, update any table TO test;

--- GRANT connect TO test
--- GRANT resource TO test

--- �bung 2: Teil 2.7 ---
--- Wenn Sie eine gr��ere Anzahl neuer User anlegen m�ssten, alle mit denselben Berechtigungen, was w�re die beste Methode dazu? Welche SQL Statements sind daf�r notwendig? Zeigen Sie das anhand von Beispiel 6 ---

CREATE ROLE myrole
GRANT select any table, insert any table, delete any table, update any table TO myrole;
GRANT myrole TO test;

--- �bung 2: Teil 2.8 ---
---  Geben Sie f�r alle eigenen Tabellen und Views den Namen und die L�nge aus (= Summe aller DATA_LENGTH Eintr�ge f�r jede Tabelle bzw. View) ---
SELECT table_name, SUM(data_length) FROM user_tab_columns
GROUP BY table_name

--- �bung 2: Teil 2.9 ---
--- Geben Sie alle Sequences, Synonyms, Tabellen und Views (auf die Sie Zugriff haben) die Namen aus, sortiert nach Kategorie und innerhalb davon nach Namen (m�glicherweise sind nicht alle Kategorien vorhanden).

--- da sehr viele sequences und vor allem synonyme - Einschr�nkung auf all that I own --> user_... ---
--- SELECT * FROM ALL_SEQUENCES
--- SELECT * FROM ALL_SYNONYMS

SELECT sequence_name AS name, 'sequence' AS kategorie FROM USER_SEQUENCES
UNION
SELECT table_name AS name, 'table_view' AS kategorie FROM user_tab_columns
UNION
SELECT synonym_name AS name, 'synonym' AS kategorie FROM user_SYNONYMS
ORDER BY kategorie, name;







