/*
    PRCS252 - Travel Management System - CREATE journeys
    
    Attributes and relations:
    
        - journey_id;

	- station_id;
        

*/

CREATE TABLE journey_stations(
    journey_id NUMBER NOT NULL
        CONSTRAINT journey_stations_journey_id_fk REFERENCES journeys(journey_id),
    station_id NUMBER NOT NULL
        CONSTRAINT journey_stations_station_id_fk REFERENCES stations(station_id)
);