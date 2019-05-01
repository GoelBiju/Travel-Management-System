/*
    PRCS252E - Travel Management System - CREATE route_stops
    
    Attributes and relations:
        
        - route_id;
        
        - stop_id;
        
        - position_in_route;
        
        - expected_arrival_datetime;
*/


CREATE TABLE route_stops(

    route_id NUMBER NOT NULL
        CONSTRAINT route_stops_route_id_fk REFERENCES routes (route_id),
        
    stop_id NUMBER NOT NULL
        CONSTRAINT route_stops_stop_id_fk REFERENCES stops (stop_id),
        
    position_in_route NUMBER
        CONSTRAINT position_in_route_nn NOT NULL,
        
    expected_arrival_datetime DATE
        CONSTRAINT expected_arrival_datetime_nn NOT NULL,
        
    CONSTRAINT route_stop_pk PRIMARY KEY (route_id, stop_id)
);
        