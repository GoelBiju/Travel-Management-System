/*
    PRCS252E - Travel Management System
    
    Attributes and relations:
    
        - station_id;
        
        - station_name;
    
*/


CREATE TABLE stations(

    station_id NUMBER 
        CONSTRAINT stations_station_id_pk PRIMARY KEY,
  
    station_name VARCHAR2(100) 
        CONSTRAINT stations_station_name_nn NOT NULL 
);