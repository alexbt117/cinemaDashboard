-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 05, 2019 at 08:23 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.2.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cinema`
--
CREATE DATABASE IF NOT EXISTS `cinema` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `cinema`;

-- --------------------------------------------------------

--
-- Table structure for table `cinema`
--

CREATE TABLE `cinema` (
  `id` int(11) NOT NULL,
  `cinemaname` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cinema`
--

INSERT INTO `cinema` (`id`, `cinemaname`) VALUES
(0, 'Village_Athens'),
(1, 'Village_Volos');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `surname` varchar(40) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `mobilephone` varchar(10) DEFAULT NULL,
  `numtickets` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `name`, `surname`, `email`, `mobilephone`, `numtickets`) VALUES
(0, 'Alexandros', 'Bampounis', 'alexakos01@gmail.com', '2147483647', 5),
(1, 'Giannis', 'Papadopoulos', 'gpapa@gmail.com', '1234567890', 5),
(2, 'Vasilis', 'Trimponias', 'vtri@gmai.com', '2147483647', 5),
(3, 'asd', 'asd', 'asd', '1234567890', 5);

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE `movie` (
  `id` int(11) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `director` varchar(80) DEFAULT NULL,
  `genre` varchar(20) DEFAULT NULL,
  `actors` varchar(200) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`id`, `title`, `description`, `director`, `genre`, `actors`, `duration`) VALUES
(0, 'Midsommar', 'A couple travels to Sweden to visit a rural hometown\'s fabled mid-summer festival. What begins as an idyllic retreat quickly devolves into an increasingly violent and bizarre competition at the hands of a pagan cult. ', 'Ari Aster', 'Thriller', 'Florence Pugh, Jack Reynor, Vilhelm Blomgren', 147),
(1, 'Killerman', 'Moe Diamond is a New York City money launderer who wakes up with no memory and millions of dollars in stolen cash and drugs. He must soon scour the streets in search of answers while trying to dodge a crew of violent and crooked cops.', 'Malik Bader', 'Action', 'Liam Hemsworth, Emory Cohen, Diane Guerrero', 112),
(2, 'Angel Has Fallen', 'Secret Service Agent Mike Banning is framed for the attempted assassination of the President and must evade his own agency and the FBI as he tries to uncover the real threat. ', 'Ric Roman Waugh', 'Action', 'Gerard Butler, Frederick Schmidt, Danny Huston ', 121);

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `idcustomer` int(11) DEFAULT NULL,
  `idmovie` int(11) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `viewing_id` int(11) DEFAULT NULL,
  `customer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`id`, `idcustomer`, `idmovie`, `status`, `viewing_id`, `customer_id`) VALUES
(1, 1, 1, 'Completed', 1, 1),
(2, 3, 1, 'Pending', 0, 3),
(3, 2, NULL, 'Pending', 10, 2);

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `roomname` varchar(10) NOT NULL,
  `numofseats` int(11) DEFAULT NULL,
  `ofcinema` varchar(40) DEFAULT NULL,
  `cinema_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`roomname`, `numofseats`, `ofcinema`, `cinema_id`) VALUES
('Diamond', 250, 'Village_Athens', 0),
('Galaxy', 120, 'Village_Volos', 1),
('Moonlight', 70, 'Village_Volos', 1),
('Pilio', 150, 'Village_Volos', 1),
('Ruby', 210, 'Village_Athens', 0),
('Sapphire', 200, 'Village_Athens', 0);

-- --------------------------------------------------------

--
-- Table structure for table `viewing`
--

CREATE TABLE `viewing` (
  `id` int(11) NOT NULL,
  `movieviewing` varchar(50) DEFAULT NULL,
  `roomviewing` varchar(10) DEFAULT NULL,
  `timeviewing` datetime DEFAULT NULL,
  `room_roomname` varchar(10) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `viewing`
--

INSERT INTO `viewing` (`id`, `movieviewing`, `roomviewing`, `timeviewing`, `room_roomname`, `movie_id`) VALUES
(0, 'Midsommar', 'Diamond', '2019-09-18 18:45:00', 'Diamond', 0),
(1, 'Midsommar', 'Ruby', '2019-09-18 18:45:00', 'Ruby', 0),
(2, 'Angel Has Fallen', 'Pilio', '2019-09-01 08:23:00', 'Pilio', 2),
(3, 'Killerman', 'Galaxy', '2019-09-19 20:15:00', 'Galaxy', 1),
(4, 'Killerman', 'Galaxy', '2019-08-28 20:25:00', 'Galaxy', 1),
(5, 'Anger Has Fallen', 'Ruby', '2019-09-19 17:50:00', 'Ruby', 2),
(6, 'Anger Has Fallen', 'Ruby', '2019-09-19 20:50:00', 'Ruby', 2),
(7, 'Midsommar', 'Moonlight', '2019-09-20 17:00:00', 'Moonlight', 0),
(8, 'Midsommar', 'Pilio', '2019-08-28 17:15:00', 'Pilio', 0),
(9, 'Angel Has Fallen', 'Pilio', '2019-08-28 20:00:00', 'Pilio', 2),
(10, 'Killerman', 'Sapphire', '2019-08-29 19:00:00', 'Sapphire', 1),
(11, 'Killerman', 'Galaxy', '2019-09-19 18:00:00', 'Galaxy', 1),
(12, 'Killerman', 'Moonlight', '2019-09-19 20:50:00', 'Moonlight', 1),
(13, 'Angel Has Fallen', 'Galaxy', '2019-09-01 08:23:00', 'Galaxy', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cinema`
--
ALTER TABLE `cinema`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `reservation_customer_fk` (`customer_id`),
  ADD KEY `reservation_viewing_fk` (`viewing_id`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`roomname`),
  ADD KEY `room_cinema_fk` (`cinema_id`);

--
-- Indexes for table `viewing`
--
ALTER TABLE `viewing`
  ADD PRIMARY KEY (`id`),
  ADD KEY `viewing_movie_fk` (`movie_id`),
  ADD KEY `viewing_room_fk` (`room_roomname`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_customer_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `reservation_viewing_fk` FOREIGN KEY (`viewing_id`) REFERENCES `viewing` (`id`);

--
-- Constraints for table `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `room_cinema_fk` FOREIGN KEY (`cinema_id`) REFERENCES `cinema` (`id`);

--
-- Constraints for table `viewing`
--
ALTER TABLE `viewing`
  ADD CONSTRAINT `viewing_movie_fk` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`),
  ADD CONSTRAINT `viewing_room_fk` FOREIGN KEY (`room_roomname`) REFERENCES `room` (`roomname`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
