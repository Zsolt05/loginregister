-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2021. Ápr 07. 17:16
-- Kiszolgáló verziója: 10.4.11-MariaDB
-- PHP verzió: 7.2.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `youtube`
--
CREATE DATABASE IF NOT EXISTS `youtube` DEFAULT CHARACTER SET utf8 COLLATE utf8_hungarian_ci;
USE `youtube`;

-- --------------------------------------------------------
GRANT ALL PRIVILEGES ON *.* TO 'youtubefelh'@'%' 
IDENTIFIED BY PASSWORD '*00A51F3F48415C7D4E8908980D443C29C69B60C9' WITH GRANT OPTION;
--
-- Tábla szerkezet ehhez a táblához `emberek`
--

DROP TABLE IF EXISTS `emberek`;
CREATE TABLE `emberek` (
  `ID` int(10) NOT NULL,
  `nev` varchar(100) COLLATE utf8_hungarian_ci NOT NULL,
  `email` varchar(200) COLLATE utf8_hungarian_ci NOT NULL,
  `jelszo` varchar(100) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `emberek`
--

INSERT INTO `emberek` (`ID`, `nev`, `email`, `jelszo`) VALUES
(1, 'Zsolt', 'email@email.test', '12345'),
(2, 'Sanyi', 'teszteles@teszt.test', 'jelszo'),
(3, 'Jani', 'jani@email.test', '54321');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `emberek`
--
ALTER TABLE `emberek`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `email` (`email`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `emberek`
--
ALTER TABLE `emberek`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
