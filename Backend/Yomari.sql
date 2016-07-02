-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 02, 2016 at 01:46 PM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 5.5.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Yomari`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userid` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `usertype` varchar(50) NOT NULL,
  `status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userid`, `username`, `password`, `usertype`, `status`) VALUES
(1, 'admin', 'admin', 'admin', 'active');

-- --------------------------------------------------------

--
-- Table structure for table `Volunteer_Detail`
--

CREATE TABLE `Volunteer_Detail` (
  `Id` int(11) NOT NULL,
  `Org_Name` varchar(200) NOT NULL,
  `Num_Of_Volunteers` int(200) NOT NULL,
  `Emergency_Level` varchar(50) NOT NULL,
  `Disaster_Type` varchar(50) NOT NULL,
  `Org_Address` varchar(200) NOT NULL,
  `Org_ContactNumber` varchar(14) NOT NULL,
  `Disaster_Address` varchar(200) NOT NULL,
  `Org_Email` varchar(50) NOT NULL,
  `Num_Of_Reports` int(10) NOT NULL,
  `Status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Volunteer_Detail`
--

INSERT INTO `Volunteer_Detail` (`Id`, `Org_Name`, `Num_Of_Volunteers`, `Emergency_Level`, `Disaster_Type`, `Org_Address`, `Org_ContactNumber`, `Disaster_Address`, `Org_Email`, `Num_Of_Reports`, `Status`) VALUES
(1, 'Red Cross Society ', 34, 'Medium ', 'Earthquake ', 'Sanepa, Lalitpur ', '01-6436359 ', 'Nuwakot, Nepal ', 'redcrosssociety@gmail.com ', 0, 'Active');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userid`);

--
-- Indexes for table `Volunteer_Detail`
--
ALTER TABLE `Volunteer_Detail`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `Volunteer_Detail`
--
ALTER TABLE `Volunteer_Detail`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
