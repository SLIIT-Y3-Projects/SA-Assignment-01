-- Table structure for table `transportbookings`

CREATE TABLE `TRANSPORTBOOKINGS` (
  `ID` INT(11) NOT NULL,
  `CUSTOMERNAME` VARCHAR(250) NOT NULL,
  `CUSTOMERNIC` VARCHAR(250) NOT NULL,
  `PACKAGEID` INT(11) NOT NULL,
  `NUMDAYS` INT(11) NOT NULL,
  `TOTALCOST` DOUBLE NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

-- Dumping data for table `transportbookings`

INSERT INTO `TRANSPORTBOOKINGS` (
  `ID`,
  `CUSTOMERNAME`,
  `CUSTOMERNIC`,
  `PACKAGEID`,
  `NUMDAYS`,
  `TOTALCOST`
) VALUES (
  1,
  'Thushara',
  '123456789V',
  5,
  5,
  400000
),
(
  2,
  'Thushara',
  '123456789V',
  4,
  5,
  225000
);

-- --------------------------------------------------------

-- Table structure for table `transportdetails`

CREATE TABLE `TRANSPORTDETAILS` (
  `ID` INT(11) NOT NULL,
  `PACKAGENAME` VARCHAR(250) NOT NULL,
  `VEHICLEREGNO` VARCHAR(10) NOT NULL,
  `VEHICLETYPE` VARCHAR(250) NOT NULL,
  `CAPACITY` INT(11) NOT NULL,
  `AMENITIES` VARCHAR(300) NOT NULL,
  `COST` DOUBLE NOT NULL,
  `SAFETYSECURITY` VARCHAR(300) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

-- Dumping data for table `transportdetails`

INSERT INTO `TRANSPORTDETAILS` (
  `ID`,
  `PACKAGENAME`,
  `VEHICLEREGNO`,
  `VEHICLETYPE`,
  `CAPACITY`,
  `AMENITIES`,
  `COST`,
  `SAFETYSECURITY`
) VALUES (
  4,
  'Pack 3',
  'FED8532',
  'sedan',
  6,
  '[Heated seats, Sunroof]',
  45000,
  '[Traction control, Airbags]'
),
(
  5,
  'Pack 5',
  'JHD2520',
  'limousine',
  6,
  '[GPS, Bluetooth, Sunroof]',
  80000,
  '[Airbags, Alarm system, Backup camera]'
),
(
  6,
  'Pack 10',
  'ADC5262',
  'Limousine',
  4,
  '[Wi-Fi, Air conditioning, Sunroof]',
  45000,
  '[Airbags, GPS tracking, Driver identification]'
);

-- --------------------------------------------------------

-- Indexes for table `transportbookings`
ALTER TABLE `TRANSPORTBOOKINGS` ADD PRIMARY KEY (`ID`);

-- Indexes for table `transportdetails`
ALTER TABLE `TRANSPORTDETAILS` ADD PRIMARY KEY (`ID`);

-- AUTO_INCREMENT for table `transportbookings`
ALTER TABLE `TRANSPORTBOOKINGS` MODIFY `ID` INT(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

-- AUTO_INCREMENT for table `transportdetails`
ALTER TABLE `TRANSPORTDETAILS` MODIFY `ID` INT(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;


-- --------------------------------------------------------

-- Table structure for table `packagedetails`

CREATE TABLE `PACKAGEDETAILS` (
  `ID` INT(11) NOT NULL,
  `PACKAGEID` VARCHAR(20) NOT NULL,
  `PACKAGENAME` VARCHAR(50) NOT NULL,
  `PRICE` INT(10) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_GENERAL_CI;

-- Dumping data for table `packagedetails`

INSERT INTO `PACKAGEDETAILS` (
  `ID`,
  `PACKAGEID`,
  `PACKAGENAME`,
  `PRICE`
) VALUES (
  1,
  'Wedding001',
  'Wedding Package',
  3500
);

-- --------------------------------------------------------

-- Indexes for table `packagedetails`
ALTER TABLE `PACKAGEDETAILS` ADD PRIMARY KEY (`ID`);

-- AUTO_INCREMENT for table `packagedetails`
ALTER TABLE `PACKAGEDETAILS` MODIFY `ID` INT(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;


-- --------------------------------------------------------

-- Table structure for table `decorationpackageslist`

CREATE TABLE `DECORATIONPACKAGESLIST` (
  `ID` INT(11) NOT NULL,
  `PACKAGENAME` VARCHAR(20) NOT NULL,
  `EVENTTIME` VARCHAR(10) NOT NULL,
  `EVENTTYPE` VARCHAR(15) NOT NULL,
  `AREASIZE` INT(10) NOT NULL,
  `PRICE` INT(11) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_GENERAL_CI;

-- Dumping data for table `decorationpackageslist`

INSERT INTO `DECORATIONPACKAGESLIST` (
  `ID`,
  `PACKAGENAME`,
  `EVENTTIME`,
  `EVENTTYPE`,
  `AREASIZE`,
  `PRICE`
) VALUES (
  3,
  'package 3 updated',
  'Night',
  'Wedding',
  6000,
  2500
),
(
  4,
  'test package update',
  'Day',
  'Wedding',
  5500,
  500
),
(
  5,
  'test package 2',
  'Day',
  'Wedding',
  6000,
  90000
),
(
  6,
  'test package one',
  'Night',
  'Wedding',
  6000,
  9000
);

-- Indexes for table `decorationpackageslist`
ALTER TABLE `DECORATIONPACKAGESLIST` ADD PRIMARY KEY (`ID`);

-- AUTO_INCREMENT for table `decorationpackageslist`
ALTER TABLE `DECORATIONPACKAGESLIST` MODIFY `ID` INT(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;


-- --------------------------------------------------------

-- Table structure for table `soundpackdetails`

CREATE TABLE `SOUNDPACKDETAILS` (
  `ID` INT(5) NOT NULL,
  `PACKNAME` VARCHAR(100) NOT NULL,
  `PACKTYPE` VARCHAR(100) NOT NULL,
  `PRICE` FLOAT NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_GENERAL_CI;

-- Dumping data for table `soundpackdetails`

INSERT INTO `SOUNDPACKDETAILS` (
  `ID`,
  `PACKNAME`,
  `PACKTYPE`,
  `PRICE`
) VALUES (
  1,
  'pack 01',
  'outdoor',
  2000
),
(
  3,
  'pack 003',
  'indoor',
  5000
),
(
  4,
  'package spk',
  'indoor',
  5000
),
(
  5,
  'DJ Package',
  'indoor',
  10000
),
(
  6,
  'Dj Package 2',
  'outdoor',
  15000
),
(
  7,
  'Sound Pack Extra',
  'indoor',
  20000
),
(
  8,
  'package Extra 2',
  'indoor',
  7500
);

-- Indexes for table `soundpackdetails`
ALTER TABLE `SOUNDPACKDETAILS` ADD PRIMARY KEY (`ID`);

-- AUTO_INCREMENT for table `soundpackdetails`
ALTER TABLE `SOUNDPACKDETAILS` MODIFY `ID` INT(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
