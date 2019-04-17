/*
    PRCS252E - Travel Management System - CREATE routes_archive
    
    Attributes and relations:
    
        - route_id;
        
        - departure_station;
        
        - arrival_station;
*/

CREATE TABLE routes_archive(

    route_id NUMBER
        CONSTRAINT routes_archive_route_id_pk PRIMARY KEY,

    departure_station NUMBER
        CONSTRAINT routes_archive_departure_station_fk REFERENCES stations (station_id),

    arrival_station NUMBER
        CONSTRAINT routes_archive_arrival_station_fk REFERENCES stations (station_id)
);