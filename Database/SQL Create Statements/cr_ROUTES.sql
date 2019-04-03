/*
    PRCS252E - Travel Management System - CREATE routes
    
    Attributes and relations:
    
        - route_id;
        
        - departure_station;
        
        - arrival_station;
*/


CREATE TABLE routes(

    route_id NUMBER 
        CONSTRAINT routes_route_id_pk PRIMARY KEY,

    departure_station NUMBER
        CONSTRAINT routes_departure_station_fk REFERENCES stations (station_id),

    arrival_station NUMBER
        CONSTRAINT routes_arrival_station_fk REFERENCES stations (station_id)
);