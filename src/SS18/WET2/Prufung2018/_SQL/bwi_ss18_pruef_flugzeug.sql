-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 03. Mai 2018 um 14:01
-- Server-Version: 10.1.22-MariaDB
-- PHP-Version: 7.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `bwi_ss18_pruef_flugzeug`
--
CREATE DATABASE IF NOT EXISTS `bwi_ss18_pruef_flugzeug` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `bwi_ss18_pruef_flugzeug`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `flugzeuge`
--

CREATE TABLE `flugzeuge` (
  `flugzeugID` int(11) NOT NULL,
  `bezeichnung` varchar(30) NOT NULL,
  `fluglinie` varchar(30) NOT NULL,
  `flugstunden` int(11) NOT NULL,
  `wartungsstatus` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `flugzeuge`
--

INSERT INTO `flugzeuge` (`flugzeugID`, `bezeichnung`, `fluglinie`, `flugstunden`, `wartungsstatus`) VALUES
(1, 'Boeing 747-300', 'Austrian Airlines', 17300, 'gewartet'),
(2, 'A320-200', 'FlyNiki', 17800, 'gewartet'),
(3, 'Boeing 747-300', 'Lufthansa', 15400, 'vorgemerkt'),
(4, 'A320-200', 'FlyNiki', 13000, 'gewartet'),
(5, 'Boeing 747-700', 'Sky Europe', 22000, 'gewartet'),
(6, 'Airbus A319', 'Lauda Air', 20800, 'gewartet'),
(7, 'Fokker 100', 'Sky Europe', 12400, 'vorgemerkt'),
(8, 'A320-700', 'Sky Europe', 25000, 'gewartet'),
(9, 'Boeing 747-300', 'Lufthansa', 8300, 'vorgemerkt'),
(10, 'Foker 100', 'Austrian Arrow', 25400, 'gewartet'),
(11, 'Boeing 747-700', 'Sky Europe', 18320, 'gewartet'),
(12, 'Fokker 100', 'Austrian Arrow', 16400, 'vorgemerkt');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `flugzeuge`
--
ALTER TABLE `flugzeuge`
  ADD PRIMARY KEY (`flugzeugID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `flugzeuge`
--
ALTER TABLE `flugzeuge`
  MODIFY `flugzeugID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
