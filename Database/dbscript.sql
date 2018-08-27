-- ****************** SqlDBM: MySQL ******************;
-- ***************************************************;

DROP TABLE `LOG`;


DROP TABLE `PARKING_BAY`;


DROP TABLE `PARKING_LOT`;


DROP TABLE `CAMPUS`;


DROP TABLE `ACCOUNTS`;



-- ************************************** `Campus`

CREATE TABLE `CAMPUS`
(
 `CAMPUS_ID`   INTEGER unsigned NOT NULL AUTO_INCREMENT ,
 `CAMPUS_NAME` VARCHAR(255) NOT NULL ,
 `CAMPUS_ADDRESS`    VARCHAR(255) NOT NULL ,

PRIMARY KEY (`CAMPUS_ID`)
);





-- ************************************** `Accounts`

CREATE TABLE `ACCOUNTS`
(
 `ACCOUNT_ID`     INTEGER unsigned NOT NULL AUTO_INCREMENT ,
 `ACCOUNT_EMAIL ` VARCHAR(255) NOT NULL ,
 `ACCOUNT_IS_ADMIN`       BINARY NOT NULL ,
 `ACCOUNT_FNAME`          VARCHAR(255) ,
 `ACCOUNT_SURNAME`       VARCHAR(255) ,

PRIMARY KEY (`ACCOUNT_ID`)
) COMMENT='Table used to store which google email addresses are admin for login.';





-- ************************************** `Parking Lot`

CREATE TABLE `PARKING_LOT`
(
 `LOT_ID`           INTEGER unsigned NOT NULL AUTO_INCREMENT ,
 `CAMPUS_ID`        INTEGER unsigned NOT NULL ,
 `LOT_AVAILABLE_SPOTS` INTEGER ,
 `LOT_ALL`             BINARY NOT NULL ,
 `LOT_FIRST_YEAR`       BINARY NOT NULL ,
 `LOT_SECOND_YEAR`      BINARY NOT NULL ,
 `LOT_THIRD_YEAR`       BINARY NOT NULL ,
 `LOT_POST_GRAD`        BINARY NOT NULL ,
 `LOT_STAFF`           BINARY NOT NULL ,

PRIMARY KEY (`LOT_ID`, `CAMPUS_ID`),
KEY `fkIdx_48` (`CAMPUS_ID`),
CONSTRAINT `FK_48` FOREIGN KEY `fkIdx_48` (`CAMPUS_ID`) REFERENCES `CAMPUS` (`CAMPUS_ID`)
) COMMENT='Table used to store information about parking lots, such as there adresses, which campus they are on and the permissions required to enter them.';





-- ************************************** `Log`

CREATE TABLE `LOG`
(
 `LOG_ID`    INTEGER NOT NULL ,
 `LOT_ID`    INTEGER unsigned NOT NULL ,
 `CAMPUS_ID` INTEGER unsigned NOT NULL ,
 `LOG_DATE` DATETIME NOT NULL ,
 `LOG`      JSON NOT NULL ,

PRIMARY KEY (`LOG_ID`, `LOT_ID`, `CAMPUS_ID`),
KEY `fkIdx_60` (`LOT_ID`, `CAMPUS_ID`),
CONSTRAINT `FK_60` FOREIGN KEY `fkIdx_60` (`LOT_ID`, `CAMPUS_ID`) REFERENCES `PARKING_LOT` (`LOG_ID`, `CAMPUS_ID`)
);





-- ************************************** `Parking Bay`

CREATE TABLE `PARKING_BAY`
(
 `BAY_ID`       INTEGER unsigned NOT NULL AUTO_INCREMENT ,
 `LOT_ID`       INTEGER unsigned NOT NULL ,
 `CAMPUS_ID`    INTEGER unsigned NOT NULL ,
 `BAY_IS_FULL`      BINARY NOT NULL ,
 `BAY_COORDINATES` JSON NOT NULL ,

PRIMARY KEY (`BAY_ID`, `LOT_ID`, `CAMPUS_ID`),
KEY `fkIdx_32` (`LOT_ID`, `CAMPUS_ID`),
CONSTRAINT `FK_32` FOREIGN KEY `fkIdx_32` (`LOT_ID`, `CAMPUS_ID`) REFERENCES `PARKING_LOT` (`LOT_ID`, `CAMPUS_ID`)
) COMMENT='Table used to store information about each parking bay in a specific parking lot. \n\nEach parking bay has a unique BayID and LOTID, where the LotID corresponds to a parking lot in the Parking lot table.';





