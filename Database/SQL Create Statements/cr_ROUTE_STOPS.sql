/*
    PRCS252E - Travel Management System - CREATE route_stops
    
    Attributes and relations:
        
        - route_id;
        
        - stop_id;
        
        - position_in_route;
        
*/


CREATE TABLE route_stops(

    route_id NUMBER NOT NULL
        CONSTRAINT route_stops_route_id_fk REFERENCES routes (route_id),
        
    stop_id NUMBER NOT NULL
        CONSTRAINT route_stops_stop_id_fk REFERENCES stops (stop_id),
        
    position_in_route NUMBER
        CONSTRAINT position_in_route_nn NOT NULL
);
        