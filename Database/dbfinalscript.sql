-- we don't know how to generate schema APSAPP_DB (class Schema) :(
create table if not exists ACCOUNTS
(
	ACCOUNT_ID int auto_increment
		primary key,
	ACCOUNT_USERNAME varchar(50) not null,
	ACCOUNT_EMAIL varchar(100) not null,
	ACCOUNT_IS_ADMIN binary(1) default '0' not null,
	ACCOUNT_FNAME varchar(50) not null,
	ACCOUNT_SURNAME varchar(50) not null,
	constraint ACCOUNTS_ACCOUNT_EMAIL_uindex
		unique (ACCOUNT_EMAIL),
	constraint ACCOUNTS_ACCOUNT_USERNAME_uindex
		unique (ACCOUNT_USERNAME)
)
comment 'Table used to store which google email addresses are admin for login.'
;

create table if not exists CAMPUS
(
	CAMPUS_ID varchar(30) not null
		primary key,
	CAMPUS_NAME varchar(50) not null,
	CAMPUS_ADDRESS varchar(200) not null
)
comment 'Stores information about the campuses that use the system'
;

create table if not exists PARKING_LOTS
(
	LOT_ID varchar(30) not null
		primary key,
	LOT_NAME varchar(50) not null,
	LOT_LOCATION varchar(200) not null,
	CAMPUS_ID varchar(30) not null,
	LOT_ALL binary(1) default '0' not null,
	LOT_FIRST_YEAR binary(1) default '0' not null,
	LOT_SECOND_YEAR binary(1) default '0' not null,
	LOT_THIRD_YEAR binary(1) default '0' not null,
	LOT_FOURTH_YEAR binary(1) default '0' not null,
	LOT_POST_GRAD binary(1) default '0' not null,
	LOT_STAFF binary(1) default '0' not null,
	LOT_VISITORS binary(1) default '0' not null,
	LOT_PART_TIME binary(1) default '0' not null,
	constraint CAMPUS_ID
		foreign key (CAMPUS_ID) references CAMPUS (CAMPUS_ID)
)
comment 'Table used to store information about parking lots, such as there adresses, which campus they are on and the permissions required to enter them.'
;

create table if not exists PARKING_LOG
(
	LOG_ID varchar(30) not null
		primary key,
	LOT_ID varchar(30) not null,
	LOG_DATE date not null,
	LOG_TIME time not null,
	LOG_OPEN int not null,
	constraint LOT_ID
		foreign key (LOT_ID) references PARKING_LOTS (LOT_ID)
)
comment 'Stores a log of the information regarding free parking spaces in a parking lot at a given time.'
;

create table if not exists PARKING_SPACE
(
	PARK_ID varchar(20) not null
		primary key,
	LOT_ID varchar(30) not null,
	PARK_COORD json not null,
	PARK_IS_OPEN binary(1) default '0' not null,
	constraint LOT_ID_fk
		foreign key (LOT_ID) references PARKING_LOTS (LOT_ID)
)
;

