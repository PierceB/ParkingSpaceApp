/*Script file for Automated Parking Space App database*/

DROP TABLE `Parking Bay`;


DROP TABLE `Parking Lot`;


DROP TABLE `Accounts`;



-- ************************************** `Parking Lot`

CREATE TABLE `Parking Lot`
(
 `LotID`           INTEGER unsigned NOT NULL AUTO_INCREMENT ,
 `Campus`          VARCHAR(255) NOT NULL ,
 `Address`         VARCHAR(255) ,
 `Available Spots` INTEGER ,
 `All`             BINARY NOT NULL ,
 `FirstYear`       BINARY NOT NULL ,
 `SecondYear`      BINARY NOT NULL ,
 `ThirdYear`       BINARY NOT NULL ,
 `PostGrad`        BINARY NOT NULL ,
 `Staff`           BINARY NOT NULL ,

PRIMARY KEY (`LotID`)
) COMMENT='Table used to store information about parking lots, such as there adresses, which campus they are on and the permissions required to enter them.';





-- ************************************** `Accounts`

CREATE TABLE `Accounts`
(
 `AccountID`     INTEGER unsigned NOT NULL AUTO_INCREMENT ,
 `Google Email ` VARCHAR(255) NOT NULL ,
 `IsAdmin`       BINARY NOT NULL ,

PRIMARY KEY (`AccountID`)
) COMMENT='Table used to store which google email addresses are admin for login.';





-- ************************************** `Parking Bay`

CREATE TABLE `Parking Bay`
(
 `BayID`       INTEGER unsigned NOT NULL AUTO_INCREMENT ,
 `LotID`       INTEGER unsigned NOT NULL ,
 `isFull`      BINARY NOT NULL ,
 `Coordinates` JSON NOT NULL ,

PRIMARY KEY (`BayID`, `LotID`),
KEY `fkIdx_32` (`LotID`),
CONSTRAINT `FK_32` FOREIGN KEY `fkIdx_32` (`LotID`) REFERENCES `Parking Lot` (`LotID`)
) COMMENT='Table used to store information about each parking bay in a specific parking lot. \n\nEach parking bay has a unique BayID and LOTID, where the LotID corresponds to a parking lot in the Parking lot table.';


