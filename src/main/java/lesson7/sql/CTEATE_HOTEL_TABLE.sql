CREATE TABLE HOTEL_SECOND(
  HOTEL_ID NUMBER ,
  CONSTRAINT HOTEL_SECOND_PK PRIMARY KEY (HOTEL_ID),
  NAME NVARCHAR2(50) NOT NULL ,
  COUNTRY NVARCHAR2(50) NOT NULL ,
  CITY NVARCHAR2(50) NOT NULL ,
  STREET NVARCHAR2(100) NOT NULL

);