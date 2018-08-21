-- ****************** SqlDBM: MySQL ******************;
-- ***************************************************;

DROP TABLE `Log`;


DROP TABLE `Parking Bay`;


DROP TABLE `Parking Lot`;


DROP TABLE `Campus`;


DROP TABLE `Accounts`;



-- ************************************** `Campus`

CREATE TABLE `Campus`
(
 `CampusID`   INTEGER unsigned NOT NULL AUTO_INCREMENT ,
 `CampusName` VARCHAR(255) NOT NULL ,
 `Address`    VARCHAR(255) NOT NULL ,

PRIMARY KEY (`CampusID`)
);





-- ************************************** `Accounts`

CREATE TABLE `Accounts`
(
 `AccountID`     INTEGER unsigned NOT NULL AUTO_INCREMENT ,
 `Google Email ` VARCHAR(255) NOT NULL ,
 `IsAdmin`       BINARY NOT NULL ,
 `Name`          VARCHAR(255) ,
 `Surname`       VARCHAR(255) ,

PRIMARY KEY (`AccountID`)
) COMMENT='Table used to store which google email addresses are admin for login.';





-- ************************************** `Parking Lot`

CREATE TABLE `Parking Lot`
(
 `LotID`           INTEGER unsigned NOT NULL AUTO_INCREMENT ,
 `CampusID`        INTEGER unsigned NOT NULL ,
 `Available Spots` INTEGER ,
 `All`             BINARY NOT NULL ,
 `FirstYear`       BINARY NOT NULL ,
 `SecondYear`      BINARY NOT NULL ,
 `ThirdYear`       BINARY NOT NULL ,
 `PostGrad`        BINARY NOT NULL ,
 `Staff`           BINARY NOT NULL ,

PRIMARY KEY (`LotID`, `CampusID`),
KEY `fkIdx_48` (`CampusID`),
CONSTRAINT `FK_48` FOREIGN KEY `fkIdx_48` (`CampusID`) REFERENCES `Campus` (`CampusID`)
) COMMENT='Table used to store information about parking lots, such as there adresses, which campus they are on and the permissions required to enter them.';





-- ************************************** `Log`

CREATE TABLE `Log`
(
 `LogID`    INTEGER NOT NULL ,
 `LotID`    INTEGER unsigned NOT NULL ,
 `CampusID` INTEGER unsigned NOT NULL ,
 `Log_date` DATETIME NOT NULL ,
 `Log`      JSON NOT NULL ,

PRIMARY KEY (`LogID`, `LotID`, `CampusID`),
KEY `fkIdx_60` (`LotID`, `CampusID`),
CONSTRAINT `FK_60` FOREIGN KEY `fkIdx_60` (`LotID`, `CampusID`) REFERENCES `Parking Lot` (`LotID`, `CampusID`)
);





-- ************************************** `Parking Bay`

CREATE TABLE `Parking Bay`
(
 `BayID`       INTEGER unsigned NOT NULL AUTO_INCREMENT ,
 `LotID`       INTEGER unsigned NOT NULL ,
 `CampusID`    INTEGER unsigned NOT NULL ,
 `isFull`      BINARY NOT NULL ,
 `Coordinates` JSON NOT NULL ,

PRIMARY KEY (`BayID`, `LotID`, `CampusID`),
KEY `fkIdx_32` (`LotID`, `CampusID`),
CONSTRAINT `FK_32` FOREIGN KEY `fkIdx_32` (`LotID`, `CampusID`) REFERENCES `Parking Lot` (`LotID`, `CampusID`)
) COMMENT='Table used to store information about each parking bay in a specific parking lot. \n\nEach parking bay has a unique BayID and LOTID, where the LotID corresponds to a parking lot in the Parking lot table.';





