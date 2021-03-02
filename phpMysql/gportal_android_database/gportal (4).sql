-- phpMyAdmin SQL Dump
-- version 4.9.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Mar 02, 2021 at 08:53 AM
-- Server version: 5.6.49-cll-lve
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gportal`
--

-- --------------------------------------------------------

--
-- Table structure for table `aytbl`
--

CREATE TABLE `aytbl` (
  `ayid` int(11) NOT NULL,
  `ayear1` year(4) NOT NULL,
  `ayear2` year(4) NOT NULL,
  `ayearlevel` int(10) NOT NULL,
  `aysem` int(10) NOT NULL,
  `schoolcode` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `aytbl`
--

INSERT INTO `aytbl` (`ayid`, `ayear1`, `ayear2`, `ayearlevel`, `aysem`, `schoolcode`) VALUES
(1, 2020, 2021, 1, 1, '87db8d5b11'),
(2, 2020, 2021, 1, 2, '87db8d5b11'),
(3, 2020, 2021, 1, 1, 'cbcc84af7e'),
(4, 2020, 2021, 2, 1, 'cbcc84af7e'),
(5, 2020, 2021, 3, 1, 'cbcc84af7e'),
(6, 2020, 2021, 4, 1, 'cbcc84af7e'),
(7, 2020, 2021, 4, 2, 'cbcc84af7e'),
(8, 2020, 2021, 3, 2, 'cbcc84af7e'),
(9, 2020, 2021, 2, 2, 'cbcc84af7e'),
(10, 2020, 2021, 1, 2, 'cbcc84af7e');

-- --------------------------------------------------------

--
-- Table structure for table `collegetbl`
--

CREATE TABLE `collegetbl` (
  `collegeid` int(11) NOT NULL,
  `collegecode` varchar(25) NOT NULL,
  `collegename` varchar(100) NOT NULL,
  `schoolcode` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `collegetbl`
--

INSERT INTO `collegetbl` (`collegeid`, `collegecode`, `collegename`, `schoolcode`) VALUES
(1, 'IIT', 'Industrial and Information Technology', '87db8d5b11'),
(2, 'IIT', 'Industrial and Information Technology', 'cbcc84af7e'),
(3, 'EDUC', 'Education Department', 'cbcc84af7e'),
(4, 'COE', 'College of Engineering', 'cbcc84af7e');

-- --------------------------------------------------------

--
-- Table structure for table `enrollcodetbl`
--

CREATE TABLE `enrollcodetbl` (
  `enrollid` int(11) NOT NULL,
  `teacherscode` varchar(25) NOT NULL,
  `cardid` varchar(25) NOT NULL,
  `date` date NOT NULL,
  `status` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `enrollcodetbl`
--

INSERT INTO `enrollcodetbl` (`enrollid`, `teacherscode`, `cardid`, `date`, `status`) VALUES
(1, 'TEX47A', '17500101', '2021-02-23', 'active'),
(2, 'TEJV8Y', '567890', '2021-02-24', 'active');

-- --------------------------------------------------------

--
-- Table structure for table `gradestbl`
--

CREATE TABLE `gradestbl` (
  `gradeid` int(11) NOT NULL,
  `subjectid` int(11) NOT NULL,
  `teacherscode` varchar(25) NOT NULL,
  `userid` int(11) NOT NULL,
  `grade` float(10,2) NOT NULL,
  `date` date NOT NULL,
  `note` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `gradestbl`
--

INSERT INTO `gradestbl` (`gradeid`, `subjectid`, `teacherscode`, `userid`, `grade`, `date`, `note`) VALUES
(1, 1, 'TEX47A', 159, 0.00, '2021-02-23', 'No note found'),
(2, 2, 'TEJV8Y', 178, 1.25, '2021-02-24', 'Your overall grades:Attendance (10%) - 100Recitation (10%)-100Lab(25%)-100Activities(25%)-100Exam(30%)-93--------------------------------------TOTAL : 95%');

-- --------------------------------------------------------

--
-- Table structure for table `programtbl`
--

CREATE TABLE `programtbl` (
  `programid` int(11) NOT NULL,
  `programcode` varchar(25) NOT NULL,
  `programname` varchar(100) NOT NULL,
  `major` varchar(25) NOT NULL,
  `collegeid` varchar(25) NOT NULL,
  `schoolcode` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `programtbl`
--

INSERT INTO `programtbl` (`programid`, `programcode`, `programname`, `major`, `collegeid`, `schoolcode`) VALUES
(1, 'BSIT', 'Bachelor of Science in Information Technology', '', '1', '87db8d5b11'),
(2, 'ACT', 'Associate in Computer Technology', '', '1', '87db8d5b11'),
(3, 'BSIT', 'BS in Information Technology ', '', '2', 'cbcc84af7e'),
(4, 'BSED', 'BS in Education ', 'English', '3', 'cbcc84af7e'),
(5, 'ENGR', 'BS in Mechanical Engineering ', '', '4', 'cbcc84af7e');

-- --------------------------------------------------------

--
-- Table structure for table `schoolcodetbl`
--

CREATE TABLE `schoolcodetbl` (
  `schoolcodeid` int(11) NOT NULL,
  `schoolcode` varchar(25) NOT NULL,
  `userid` int(11) NOT NULL,
  `date` date NOT NULL,
  `status` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `schoolcodetbl`
--

INSERT INTO `schoolcodetbl` (`schoolcodeid`, `schoolcode`, `userid`, `date`, `status`) VALUES
(90, '325b39d736', 153, '2021-02-23', 'active'),
(91, '9531d48c63', 154, '2021-02-23', 'active'),
(92, '87db8d5b11', 155, '2021-02-23', 'active'),
(93, '9130655595', 156, '2021-02-23', 'active'),
(94, '6196728a07', 160, '2021-02-23', 'active'),
(95, '2529a6d761', 161, '2021-02-23', 'active'),
(96, 'd443def210', 162, '2021-02-23', 'active'),
(97, '7bb43b2490', 163, '2021-02-23', 'active'),
(98, '3c1aec761c', 164, '2021-02-23', 'active'),
(99, 'cbcc84af7e', 176, '2021-02-24', 'active'),
(100, '0d60ba1fec', 179, '2021-02-26', 'active');

-- --------------------------------------------------------

--
-- Table structure for table `sectiontbl`
--

CREATE TABLE `sectiontbl` (
  `sectionid` int(11) NOT NULL,
  `sectioncode` varchar(25) NOT NULL,
  `programid` int(11) NOT NULL,
  `schoolcode` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sectiontbl`
--

INSERT INTO `sectiontbl` (`sectionid`, `sectioncode`, `programid`, `schoolcode`) VALUES
(1, 'IT1A', 1, '87db8d5b11'),
(2, 'IT2A', 1, '87db8d5b11'),
(3, 'IT3A', 1, '87db8d5b11'),
(4, 'IT4A', 1, '87db8d5b11'),
(5, 'ACT1A', 2, '87db8d5b11'),
(6, 'ACT2A', 2, '87db8d5b11'),
(7, 'IT1A', 3, 'cbcc84af7e'),
(8, 'IT2A', 3, 'cbcc84af7e'),
(9, 'IT3A', 3, 'cbcc84af7e'),
(10, 'IT4A', 3, 'cbcc84af7e'),
(11, 'EDUC1A', 4, 'cbcc84af7e'),
(12, 'EDUC2A', 4, 'cbcc84af7e'),
(13, 'EDUC3A', 4, 'cbcc84af7e'),
(14, 'EDUC4A', 4, 'cbcc84af7e');

-- --------------------------------------------------------

--
-- Table structure for table `subjecttbl`
--

CREATE TABLE `subjecttbl` (
  `subjectid` int(11) NOT NULL,
  `subjectcode` varchar(25) NOT NULL,
  `subjecttitle` varchar(100) NOT NULL,
  `programid` int(11) NOT NULL,
  `ayid` int(11) NOT NULL,
  `schoolcode` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `subjecttbl`
--

INSERT INTO `subjecttbl` (`subjectid`, `subjectcode`, `subjecttitle`, `programid`, `ayid`, `schoolcode`) VALUES
(1, 'IT101', 'Basic Programming', 1, 1, '87db8d5b11'),
(2, 'IT101', 'Basic Programming', 3, 3, 'cbcc84af7e'),
(3, 'IT102', 'Basic OOP', 3, 3, 'cbcc84af7e');

-- --------------------------------------------------------

--
-- Table structure for table `teacherscodetbl`
--

CREATE TABLE `teacherscodetbl` (
  `teacherscodeid` int(11) NOT NULL,
  `teacherscode` varchar(25) NOT NULL,
  `collegeid` int(11) NOT NULL,
  `programid` int(11) NOT NULL,
  `sectionid` int(11) NOT NULL,
  `subjectid` int(11) NOT NULL,
  `ayid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `date` date NOT NULL,
  `status` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `teacherscodetbl`
--

INSERT INTO `teacherscodetbl` (`teacherscodeid`, `teacherscode`, `collegeid`, `programid`, `sectionid`, `subjectid`, `ayid`, `userid`, `date`, `status`) VALUES
(1, 'TEX47A', 1, 1, 1, 1, 1, 158, '2021-02-23', 'active'),
(2, 'TEJV8Y', 2, 3, 7, 2, 3, 177, '2021-02-24', 'active');

-- --------------------------------------------------------

--
-- Table structure for table `usertbl`
--

CREATE TABLE `usertbl` (
  `userid` int(11) NOT NULL,
  `cardid` varchar(250) NOT NULL,
  `lname` varchar(100) NOT NULL,
  `fname` varchar(25) NOT NULL,
  `mname` varchar(25) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `viewcode` varchar(25) NOT NULL,
  `access` varchar(25) NOT NULL,
  `userpassword` varchar(25) NOT NULL,
  `sectionid` int(25) NOT NULL,
  `collegeid` int(25) NOT NULL,
  `programid` int(25) NOT NULL,
  `schoolcode` varchar(25) NOT NULL,
  `address` text NOT NULL,
  `status` varchar(25) NOT NULL,
  `photo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `usertbl`
--

INSERT INTO `usertbl` (`userid`, `cardid`, `lname`, `fname`, `mname`, `gender`, `viewcode`, `access`, `userpassword`, `sectionid`, `collegeid`, `programid`, `schoolcode`, `address`, `status`, `photo`) VALUES
(155, 'bulsu@edu.ph', 'Bulacan State University ', '', '', '', '', 'School', 'bulsu', 0, 0, 0, '87db8d5b11', 'San Jose Del Monte City, Bulacan', 'active', 'upload/logoschool/Uvr0r3JlZh7YnJYtmBQe.jpeg'),
(158, '123456', 'Astillero', 'Dan', 'Obrien', 'Male', '', 'Teacher', '123456', 0, 1, 0, '87DB8D5B11', '', 'active', 'upload/profileteacher/KxOMdrW76Espus0t5s6W.jpeg'),
(159, '17500101', 'Adan', 'Allan', 'Astillero', 'Male', 'STHEYS', 'Student', 'danastillero', 4, 1, 1, '87DB8D5B11', '', 'active', 'upload/profilestudent/dh1q0VQVzBg2atFIYjOj.jpeg'),
(171, 'cc', 'cc', 'cc', 'cc', 'Male', '', 'Teacher', 'cc', 0, 1, 0, '87DB8D5B11', '', 'active', 'upload/profileteacher/N0ehxWganWJidO81nfqo.jpeg'),
(172, 'gg', 'gg', 'ggg', 'gg', 'Male', 'STH916', 'Student', 'gg', 1, 1, 2, '87DB8D5B11', '', 'active', 'upload/profilestudent/CwRdfAkyCDInoY7y7D48.jpeg'),
(173, 'rr', 'rr', 'rr', 'rr', 'Male', 'STV21L', 'Student', 'rr', 1, 1, 1, '87DB8D5B11', '', 'active', 'upload/profilestudent/z5EUiA2UKZmWbGFwefnt.jpeg'),
(174, 'ii', 'ii', 'ii', 'ii', 'Male', 'STLGDX', 'Student', 'ii', 1, 1, 1, '87DB8D5B11', '', 'active', 'upload/profilestudent/sVZ6V1rhzKPRaq2L8Frx.jpeg'),
(175, 'zz', 'zz', 'zz', 'zz', 'Male', '', 'Teacher', 'zz', 0, 1, 0, '87DB8D5B11', '', 'active', 'upload/profileteacher/lrcnH1AuSlQTaOKhxOUf.jpeg'),
(176, 'sarmiento@campus.com', 'Sarmiento Campus', '', '', '', '', 'School', 'sarmiento', 0, 0, 0, 'cbcc84af7e', 'SJDM', 'active', 'upload/logoschool/PjClPEY1PRao2LBReqz8.jpeg'),
(177, '098765', 'Astillero', 'Dan', 'OBrien', 'Male', '', 'Teacher', '098765', 0, 2, 0, 'CBCC84AF7E', '', 'active', 'upload/profileteacher/QlEYSiT7P3hONMcUNyfk.jpeg'),
(178, '567890', 'Student', 'Student', 'Student', 'Male', 'STDIKZ', 'Student', '567890', 7, 2, 3, 'CBCC84AF7E', '', 'active', 'upload/profilestudent/vtsCuXaErVlLxJzxCGtI.jpeg'),
(179, 'ty@ty.com', 'Ty', '', '', '', '', 'School', 'ty', 0, 0, 0, '0d60ba1fec', 'Ty', 'active', 'upload/logoschool/0MqhSyDn4jYjRDFZet5j.jpeg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `aytbl`
--
ALTER TABLE `aytbl`
  ADD PRIMARY KEY (`ayid`);

--
-- Indexes for table `collegetbl`
--
ALTER TABLE `collegetbl`
  ADD PRIMARY KEY (`collegeid`);

--
-- Indexes for table `enrollcodetbl`
--
ALTER TABLE `enrollcodetbl`
  ADD PRIMARY KEY (`enrollid`);

--
-- Indexes for table `gradestbl`
--
ALTER TABLE `gradestbl`
  ADD PRIMARY KEY (`gradeid`);

--
-- Indexes for table `programtbl`
--
ALTER TABLE `programtbl`
  ADD PRIMARY KEY (`programid`);

--
-- Indexes for table `schoolcodetbl`
--
ALTER TABLE `schoolcodetbl`
  ADD PRIMARY KEY (`schoolcodeid`);

--
-- Indexes for table `sectiontbl`
--
ALTER TABLE `sectiontbl`
  ADD PRIMARY KEY (`sectionid`);

--
-- Indexes for table `subjecttbl`
--
ALTER TABLE `subjecttbl`
  ADD PRIMARY KEY (`subjectid`);

--
-- Indexes for table `teacherscodetbl`
--
ALTER TABLE `teacherscodetbl`
  ADD PRIMARY KEY (`teacherscodeid`);

--
-- Indexes for table `usertbl`
--
ALTER TABLE `usertbl`
  ADD PRIMARY KEY (`userid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `aytbl`
--
ALTER TABLE `aytbl`
  MODIFY `ayid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `collegetbl`
--
ALTER TABLE `collegetbl`
  MODIFY `collegeid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `enrollcodetbl`
--
ALTER TABLE `enrollcodetbl`
  MODIFY `enrollid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `gradestbl`
--
ALTER TABLE `gradestbl`
  MODIFY `gradeid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `programtbl`
--
ALTER TABLE `programtbl`
  MODIFY `programid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `schoolcodetbl`
--
ALTER TABLE `schoolcodetbl`
  MODIFY `schoolcodeid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT for table `sectiontbl`
--
ALTER TABLE `sectiontbl`
  MODIFY `sectionid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `subjecttbl`
--
ALTER TABLE `subjecttbl`
  MODIFY `subjectid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `teacherscodetbl`
--
ALTER TABLE `teacherscodetbl`
  MODIFY `teacherscodeid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `usertbl`
--
ALTER TABLE `usertbl`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=180;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
