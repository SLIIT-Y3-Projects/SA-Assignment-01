-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 18, 2023 at 09:36 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `event_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `transportbookings`
--

CREATE TABLE `transportbookings` (
  `id` int(11) NOT NULL,
  `customerName` varchar(250) NOT NULL,
  `customerNIC` varchar(250) NOT NULL,
  `packageId` int(11) NOT NULL,
  `numDays` int(11) NOT NULL,
  `totalCost` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transportbookings`
--

INSERT INTO `transportbookings` (`id`, `customerName`, `customerNIC`, `packageId`, `numDays`, `totalCost`) VALUES
(1, 'Thushara', '123456789V', 5, 5, 400000),
(2, 'Thushara', '123456789V', 4, 5, 225000);

-- --------------------------------------------------------

--
-- Table structure for table `transportdetails`
--

CREATE TABLE `transportdetails` (
  `id` int(11) NOT NULL,
  `packageName` varchar(250) NOT NULL,
  `vehicleRegNo` varchar(10) NOT NULL,
  `vehicleType` varchar(250) NOT NULL,
  `capacity` int(11) NOT NULL,
  `amenities` varchar(300) NOT NULL,
  `cost` double NOT NULL,
  `safetySecurity` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transportdetails`
--

INSERT INTO `transportdetails` (`id`, `packageName`, `vehicleRegNo`, `vehicleType`, `capacity`, `amenities`, `cost`, `safetySecurity`) VALUES
(4, 'Pack 3', 'FED8532', 'sedan', 6, '[Heated seats, Sunroof]', 45000, '[Traction control, Airbags]'),
(5, 'Pack 5', 'JHD2520', 'limousine', 6, '[GPS, Bluetooth, Sunroof]', 80000, '[Airbags, Alarm system, Backup camera]'),
(6, 'Pack 10', 'ADC5262', 'Limousine', 4, '[Wi-Fi, Air conditioning, Sunroof]', 45000, '[Airbags, GPS tracking, Driver identification]');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `transportbookings`
--
ALTER TABLE `transportbookings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transportdetails`
--
ALTER TABLE `transportdetails`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `transportbookings`
--
ALTER TABLE `transportbookings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `transportdetails`
--
ALTER TABLE `transportdetails`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
