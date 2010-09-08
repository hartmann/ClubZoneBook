drop table event if exists; 
drop table location if exists; 
drop table organizer if exists; 

CREATE TABLE event (
  event_id int NOT NULL,
  name varchar(255) NOT NULL,
  description varchar(255) NOT NULL,
  organizer_id int NOT NULL,
  location_id int NOT NULL,
  startTime bigint NOT NULL,
  PRIMARY KEY  (event_id)
);
INSERT INTO event VALUES (1,'Oktoberfest','Fredagspils',1,1,1209068319640);
INSERT INTO event VALUES (2,'WA Hemsedaltur','Skitur',2,2,1209078319640);
CREATE TABLE location (
  location_id int NOT NULL,
  name varchar(45) NOT NULL,
  description varchar(255) NOT NULL,
  PRIMARY KEY  (location_id)
);
INSERT INTO location VALUES (1,'Bekk','Skur 39');
INSERT INTO location VALUES (2,'Hytte Hemsedal','Hytte Hemsedal');
CREATE TABLE organizer (
  organizer_id int NOT NULL,
  name varchar(45) NOT NULL,
  link varchar(255) NOT NULL,
  PRIMARY KEY  (organizer_id)
);
INSERT INTO organizer VALUES (1,'Spirit','Spirit');
INSERT INTO organizer VALUES (2,'Webarkitektur','Faggrupp');
commit;
