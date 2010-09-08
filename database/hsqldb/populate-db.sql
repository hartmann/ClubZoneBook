-- MySQL dump 9.11
--
-- Host: localhost    Database: mybooking
-- ------------------------------------------------------
-- Server version	4.0.20a-debug

-- USE mybooking;


drop table bookingentryweektypeweek if exists;
drop table bookingentry if exists;
drop table weekperiod if exists; 
drop table usagetype if exists; 
drop table week if exists; 
drop table employee if exists;     
drop table bookingperiod if exists; 

--
-- Table structure for table `bookingperiod`
--

CREATE TABLE bookingperiod (
  id INTEGER generated by default as identity (start with 1),
  startWeek integer NOT NULL,
  endWeek integer NOT NULL,
  easterWeek integer,
  name varchar(100) default '',
  state int default -1,
  registrationOpen int default 0,
  PRIMARY KEY  (id)
) ;

--
-- Dumping data for table `bookingperiod`
--

INSERT INTO bookingperiod VALUES (1,1,12,0,'Q1',1, 0);
INSERT INTO bookingperiod VALUES (2,13,26,14,'Q2',1, 0);
INSERT INTO bookingperiod VALUES (3,27,39,0,'Q3',1, 0);
INSERT INTO bookingperiod VALUES (4,40,52,0,'Q4',0, 0);

--
-- Table structure for table `employee`
--

CREATE TABLE employee (
  id INTEGER generated by default as identity (start with 1),
  firstName varchar(100) default '',
  lastName varchar(100) default '',
  PRIMARY KEY  (id)
) ;

--
-- Dumping data for table `employee`
--

INSERT INTO employee VALUES (1,'Per Kristian','Mengshoel');
INSERT INTO employee VALUES (2,'Snorre','Brandstamoen');
INSERT INTO employee VALUES (3,'Thomas','Børheim');
INSERT INTO employee VALUES (4,'Janniche','Haugen');
INSERT INTO employee VALUES (5,'Tom Johannes','Bang');

--
-- Table structure for table `week`
--

CREATE TABLE week (
  id INTEGER generated by default as identity (start with 1),
  name varchar(100) default '',
  PRIMARY KEY  (id)
) ;

--
-- Dumping data for table `week`
--

INSERT INTO week VALUES (1,'Uke 1');
INSERT INTO week VALUES (2,'Uke 2');
INSERT INTO week VALUES (3,'Uke 3');
INSERT INTO week VALUES (4,'Uke 4');
INSERT INTO week VALUES (5,'Uke 5');
INSERT INTO week VALUES (6,'Uke 6');
INSERT INTO week VALUES (7,'Uke 7');
INSERT INTO week VALUES (8,'Uke 8');
INSERT INTO week VALUES (9,'Uke 9');
INSERT INTO week VALUES (10,'Uke 10');
INSERT INTO week VALUES (11,'Uke 11');
INSERT INTO week VALUES (12,'Uke 12');
INSERT INTO week VALUES (13,'Uke 13');
INSERT INTO week VALUES (14,'Uke 14');
INSERT INTO week VALUES (15,'Uke 15');
INSERT INTO week VALUES (16,'Uke 16');
INSERT INTO week VALUES (17,'Uke 17');
INSERT INTO week VALUES (18,'Uke 18');
INSERT INTO week VALUES (19,'Uke 19');
INSERT INTO week VALUES (20,'Uke 20');
INSERT INTO week VALUES (21,'Uke 21');
INSERT INTO week VALUES (22,'Uke 22');
INSERT INTO week VALUES (23,'Uke 23');
INSERT INTO week VALUES (24,'Uke 24');
INSERT INTO week VALUES (25,'Uke 25');
INSERT INTO week VALUES (26,'Uke 26');
INSERT INTO week VALUES (27,'Uke 27');
INSERT INTO week VALUES (28,'Uke 28');
INSERT INTO week VALUES (29,'Uke 29');
INSERT INTO week VALUES (30,'Uke 30');
INSERT INTO week VALUES (31,'Uke 31');
INSERT INTO week VALUES (32,'Uke 32');
INSERT INTO week VALUES (33,'Uke 33');
INSERT INTO week VALUES (34,'Uke 34');
INSERT INTO week VALUES (35,'Uke 35');
INSERT INTO week VALUES (36,'Uke 36');
INSERT INTO week VALUES (37,'Uke 37');
INSERT INTO week VALUES (38,'Uke 38');
INSERT INTO week VALUES (39,'Uke 39');
INSERT INTO week VALUES (40,'Uke 40');
INSERT INTO week VALUES (41,'Uke 41');
INSERT INTO week VALUES (42,'Uke 42');
INSERT INTO week VALUES (43,'Uke 43');
INSERT INTO week VALUES (44,'Uke 44');
INSERT INTO week VALUES (45,'Uke 45');
INSERT INTO week VALUES (46,'Uke 46');
INSERT INTO week VALUES (47,'Uke 47');
INSERT INTO week VALUES (48,'Uke 48');
INSERT INTO week VALUES (49,'Uke 49');
INSERT INTO week VALUES (50,'Uke 50');
INSERT INTO week VALUES (51,'Uke 51');
INSERT INTO week VALUES (52,'Uke 52');
INSERT INTO week VALUES (53,'Uke 53');
INSERT INTO week VALUES (60,'En hvilken som helst uke i perioden');
INSERT INTO week VALUES (61,'Påske 1 (Fre - Ons)');
INSERT INTO week VALUES (62,'Påske 2 (Ons - Søn)');

--
-- Table structure for table `usagetype`
--

CREATE TABLE usagetype (
  id INTEGER generated by default as identity (start with 1),
  name varchar(100) default '',
  priority decimal(10,0) default '0',
  PRIMARY KEY  (id)
) ;

--
-- Dumping data for table `usagetype`
--

INSERT INTO usagetype VALUES (1,'BEKK',1);
INSERT INTO usagetype VALUES (2,'Privat',2);

--
-- Table structure for table `weekperiod`
--

CREATE TABLE weekperiod (
  id INTEGER generated by default as identity (start with 1),
  name varchar(100) default '',
  startDay decimal(10,0) default NULL,
  stopDay decimal(10,0) default NULL,
  PRIMARY KEY  (id)
) ;



--
-- Dumping data for table `weekperiod`
--

INSERT INTO weekperiod VALUES (1,'Man-Fre',1,5);
INSERT INTO weekperiod VALUES (2,'Fre-Søn',5,7);
INSERT INTO weekperiod VALUES (3,'Man-Søn',1,7);
INSERT INTO weekperiod VALUES (4,'Fre-Ons (Kun ifm påske)',5,3);
INSERT INTO weekperiod VALUES (5,'Ons-Søn (Kun ifm påske)',3,7);

--
-- Table structure for table `bookingentry`
--

CREATE TABLE bookingentry (
  id INTEGER generated by default as identity (start with 1),
  ownerid INTEGER NOT NULL,
  usageTypeid INTEGER NOT NULL,
  bookingPeriodid INTEGER NOT NULL,
  description varchar(100) default '',
  PRIMARY KEY (id),
  CONSTRAINT C58 FOREIGN KEY (bookingPeriodId) REFERENCES bookingperiod (id),
  CONSTRAINT C59 FOREIGN KEY (ownerId) REFERENCES employee (id),
  CONSTRAINT C60 FOREIGN KEY (usageTypeId) REFERENCES usagetype (id)
) ;

--
-- Dumping data for table `bookingentry`
--

INSERT INTO bookingentry VALUES (100,1,1,1,'Testdata');


--
-- Table structure for table `bookingentryweektypeweek`
--

CREATE TABLE bookingentryweektypeweek (
  id INTEGER generated by default as identity (start with 1),
  bookingEntryId INTEGER NOT NULL,
  weekId INTEGER NOT NULL,
  weekPeriodId INTEGER NOT NULL,
  PRIMARY KEY  (id),
  CONSTRAINT C61 FOREIGN KEY (bookingEntryId) REFERENCES bookingentry (id),
  CONSTRAINT C62 FOREIGN KEY (weekId) REFERENCES week (id),
  CONSTRAINT C63 FOREIGN KEY (weekPeriodId) REFERENCES weekperiod (id)
) ;

--
-- Dumping data for table `bookingentryweektypeweek`
--

INSERT INTO bookingentryweektypeweek VALUES (1,100,1,1);
INSERT INTO bookingentryweektypeweek VALUES (2,100,2,2);
INSERT INTO bookingentryweektypeweek VALUES (3,100,3,3);

