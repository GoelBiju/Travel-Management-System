/*
    PRCS252 - Travel Management System - CREATE journeys
    
    Attributes and relations:
    
        - journey_id;
        
        - route_id;
        
        - coach_id;
        
        - employee_id;

	- start_station
	
	- end_station        

        - start_time/date;
        
        - end_time/date;
*/


CREATE TABLE journeys(
    
    journey_id NUMBER NOT NULL
        CONSTRAINT journeys_journey_id_pk PRIMARY KEY,
  
    route_id NUMBER NOT NULL
        CONSTRAINT routes_route_id_fk REFERENCES routes(route_id),
        
    coach_id NUMBER NOT NULL
        CONSTRAINT coaches_coach_id_fk REFERENCES coaches(coach_id),
        
    employee_id VARCHAR(15) NOT NULL
        CONSTRAINT employees_employee_id_fk REFERENCES employees(employee_id),
        
    start_station NUMBER NOT NULL    
        CONSTRAINT stations_start_station_id_fk REFERENCES stations(station_id),
        
    end_station NUMBER NOT NULL
        CONSTRAINT stations_end_station_id_fk REFERENCES stations(station_id),
        
    start_date_time DATE NOT NULL,
    
    end_date_time DATE NOT NULL
    
);