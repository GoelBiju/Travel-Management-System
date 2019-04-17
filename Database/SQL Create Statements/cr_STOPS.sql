/*

    PRCS252E - Travel Managment System - CREATE stops
    
    Attributes and relations:
    
        - stop_id;
        
        - stop_name;
        
        - is_station;
        
        - stop_postcode;
        
        - stop_latitude;
        
        - stop_longitude;
*/


CREATE TABLE stops(

    stop_id NUMBER
        CONSTRAINT stops_stop_id_pk PRIMARY KEY,
        
    stop_name VARCHAR2(100)
        CONSTRAINT stops_stop_name_nn NOT NULL,
        
    is_station NUMBER(1)
        CONSTRAINT stops_is_station_nn NOT NULL
        CONSTRAINT is_station_check
            CHECK (is_station IN (0, 1)),
    
    stop_postcode VARCHAR2(8),
    
    stop_latitude NUMBER(9, 6),
        
    stop_longitude NUMBER(9, 6)
);