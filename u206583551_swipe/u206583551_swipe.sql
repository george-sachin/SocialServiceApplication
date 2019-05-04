-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 13, 2018 at 03:53 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `u206583551_swipe`
--

-- --------------------------------------------------------

--
-- Table structure for table `organisation`
--

CREATE TABLE IF NOT EXISTS `organisation` (
  `orgid` varchar(25) NOT NULL,
  `orgname` varchar(25) NOT NULL,
  `orgaddress` varchar(100) NOT NULL,
  `orgphone` varchar(12) NOT NULL,
  `category` varchar(5) NOT NULL,
  `photo` varchar(400) NOT NULL,
  PRIMARY KEY (`orgid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `organisation`
--

INSERT INTO `organisation` (`orgid`, `orgname`, `orgaddress`, `orgphone`, `category`, `photo`) VALUES
('5789aec6c34048.69752102', 'Cry', 'Chembur', '9167333237', '124', 'http://192.168.10.100/u206583551_swipe/uploads/5789aec6c34048.69752102.png'),
('5789af2373c758.84942833', 'Balbhavan', 'Vashi', '9167835402', '124', 'http://192.168.10.100/u206583551_swipe/uploads/5789af2373c758.84942833.png'),
('5789afd10532a7.03617408', 'Sacred Heart Church', 'Vashi', '9167333236', '1234', 'http://192.168.10.100/u206583551_swipe/uploads/5789afd10532a7.03617408.png'),
('5789b05d4af499.33943630', 'Braille Bookstore', 'Thane', '8108735621', '2', 'http://192.168.10.100/u206583551_swipe/uploads/5789b05d4af499.33943630.png'),
('5789b11d4c16c1.75846032', 'Foodshare', 'Bandra', '9820732716', '3', 'http://192.168.10.100/u206583551_swipe/uploads/5789b11d4c16c1.75846032.png'),
('5789b1ad139dc0.41300681', 'Noor Masjid', 'Vashi', '27822373', '1234', 'http://192.168.10.100/u206583551_swipe/uploads/5789b1ad139dc0.41300681.png');

-- --------------------------------------------------------

--
-- Table structure for table `organisations`
--

CREATE TABLE IF NOT EXISTS `organisations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unique_id` varchar(23) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `encrypted_password` varchar(80) NOT NULL,
  `salt` varchar(10) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_id` (`unique_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `organisations`
--

INSERT INTO `organisations` (`id`, `unique_id`, `name`, `email`, `encrypted_password`, `salt`, `created_at`, `updated_at`) VALUES
(8, '5789aec6c34048.69752102', 'Cry', 'cry@gmail.com', 'BuizUAHMZgsNoZIUsFIfWtmKlmFhMzcyYjU5MDFk', 'a372b5901d', '2016-07-16 09:19:26', NULL),
(9, '5789af2373c758.84942833', 'Balbhavan', 'bal@gmail.com', 'BIL0730S837Q3dnq7m36qM4EuQ0wODFiZTdjMzcw', '081be7c370', '2016-07-16 09:20:59', NULL),
(10, '5789afd10532a7.03617408', 'Sacred Heart Church', 'sacredheart@gmail.com', 'Rb6DYV8z5WuGoD5RJM0vUQY+jsk3MTVlZDVhZTEy', '715ed5ae12', '2016-07-16 09:23:53', NULL),
(11, '5789b05d4af499.33943630', 'Braille Bookstore', 'bbstore@gmail.com', 'yrR7iun+l5O7BopDXoUAU6p9xXZkY2Y1MWI3NGE4', 'dcf51b74a8', '2016-07-16 09:26:13', NULL),
(12, '5789b11d4c16c1.75846032', 'Foodshare', 'foodshare@gmail.com', 'H3R+HeCR4+hkkkw38nfqRS4Dr4JlYTc1NjAwNzg5', 'ea75600789', '2016-07-16 09:29:25', NULL),
(13, '5789b1ad139dc0.41300681', 'Noor Masjid', 'noor@gmail.com', '7g0Bpft3WJ9YVGRKl0xSHD61e503MTRjZmRlZmE5', '714cfdefa9', '2016-07-16 09:31:49', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE IF NOT EXISTS `request` (
  `requestid` int(10) NOT NULL AUTO_INCREMENT,
  `uid` varchar(25) NOT NULL,
  `orgid` varchar(25) NOT NULL,
  `orgaccept` varchar(25) NOT NULL,
  `info` varchar(200) NOT NULL,
  PRIMARY KEY (`requestid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=37 ;

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`requestid`, `uid`, `orgid`, `orgaccept`, `info`) VALUES
(20, '5789b2814285d5.66989148', '5789aec6c34048.69752102', 'Accepted', 'Clothes: Adults Kids  Description:15 Clothes Date:17/7/2016'),
(21, '5789b2a86e9925.03447550', '5789aec6c34048.69752102', 'Rejected', 'Amenities: Stationery  Description: pens Date:17/7/2016'),
(22, '5789b2a86e9925.03447550', '5789aec6c34048.69752102', 'Rejected', 'Amenities: Stationery  Description: 100 pens Date:21/7/2016'),
(23, '5789b2814285d5.66989148', '5789aec6c34048.69752102', 'Accepted', 'Groceries: Chocolates  Description: biscuits Date:20/7/2016'),
(24, '5789b2814285d5.66989148', '5789aec6c34048.69752102', 'Accepted', 'Clothes: Adults  Description:jeans Date:17/7/2016'),
(25, '5789b2814285d5.66989148', '5789aec6c34048.69752102', 'Accepted', 'Clothes: Adults  Description: Date:16/7/2016'),
(26, '5789b2814285d5.66989148', '5789aec6c34048.69752102', 'Accepted', 'Clothes: Kids  Description: Date:16/7/2016'),
(27, '5789b2814285d5.66989148', '5789aec6c34048.69752102', 'Accepted', 'Clothes: Adults  Description: Date:16/7/2016'),
(28, '5789b2814285d5.66989148', '5789af2373c758.84942833', 'Rejected', 'Clothes: Adults Kids  Description: Date:16/7/2016'),
(29, '5789f252a5c7c8.58167478', '5789af2373c758.84942833', 'Accepted', 'Books: Magazines  Description: \n Date:16/7/2016'),
(30, '5789f252a5c7c8.58167478', '5789af2373c758.84942833', 'Rejected', 'Books: Notebooks  Description:  Date:17/7/2016'),
(31, '5789b2a86e9925.03447550', '5789af2373c758.84942833', 'Accepted', 'Clothes: Kids  Description: Date:19/7/2016'),
(32, '5789b2a86e9925.03447550', '5789af2373c758.84942833', 'Accepted', 'Books: Magazines  Description:  Date:16/7/2016'),
(34, '5789b2a86e9925.03447550', '5789aec6c34048.69752102', 'Accepted', 'Books: Novels  Description:  Date:18/7/2016'),
(35, '5789b2a86e9925.03447550', '5789af2373c758.84942833', 'Accepted', 'Clothes:  Description:Nike Date:18/7/2016'),
(36, '5789b2814285d5.66989148', '5789aec6c34048.69752102', 'Accepted', 'Books: Novels  Description: harry potter Date:18/7/2016');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unique_id` varchar(23) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `encrypted_password` varchar(80) NOT NULL,
  `salt` varchar(10) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_id` (`unique_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `unique_id`, `name`, `email`, `encrypted_password`, `salt`, `created_at`, `updated_at`) VALUES
(10, '5789b2814285d5.66989148', 'Sachin George', 'sac@gmail.com', 'X3ODSVwUHb/AtJqanMpJQ7ohCE1hNTUwOTljNGU4', 'a55099c4e8', '2016-07-16 09:35:21', NULL),
(11, '5789b2a86e9925.03447550', 'Nisha', 'nisha@gmail.com', 'nFHTovX1hGTiLqdmTnFVxHU/ZbhjYzY3OTY2ZjRi', 'cc67966f4b', '2016-07-16 09:36:00', NULL),
(12, '5789f252a5c7c8.58167478', 'Navin Pophare', 'naveenp667@gmail.com', 'njLzKVCjPlaSB7WZBFaAfJP/TXAwMDkwZDFlYWRi', '0090d1eadb', '2016-07-16 14:07:38', NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
