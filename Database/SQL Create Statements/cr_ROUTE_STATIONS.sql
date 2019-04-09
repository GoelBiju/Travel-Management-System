/*
    PRCS252 - Travel Management System - CREATE route_stations
    
    Attributes and relations:
    
        - route_id;

	- station_id;
        

*/

CREATE TABLE route_stations(
    route_id NUMBER NOT NULL
        CONSTRAINT route_stations_journey_id_fk REFERENCES routes(route_id),
    station_id NUMBER NOT NULL
        CONSTRAINT route_stations_station_id_fk REFERENCES stations(station_id)
);