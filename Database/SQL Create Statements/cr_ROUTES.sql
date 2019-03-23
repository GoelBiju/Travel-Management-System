/*
    PRCS252E - Travel Management System - CREATE routes
    
    Attributes and relations:
    
        - route_id;
        
        - departure_station;
        
        - arrival_station;
        
        - 
        
*/

CREATE TABLE routes(

    route_id VARCHAR2(4) 
        CONSTRAINT routes_route_id_pk PRIMARY KEY,

    departure_station NUMBER
        CONSTRAINT routes_departure_station_nn NOT NULL,

    arrival_station NUMBER
        CONSTRAINT routes_arrival_station_nn NOT NULL  
);