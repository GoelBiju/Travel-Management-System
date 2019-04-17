/*
    PRCS252 - Travel Management System - CREATE journeys
    
    Attributes and relations:
    
        - journey_id;
        
        - route_id;
        
        - shift_id; 
        
        - coach_id;

        - departure_datetime;
        
        - arrival_datetime;
        
        - current_stop;
        
        - stop_arrival_datetime;
        
        - stop_departed_datetime;
        
        - coach_status;
*/


CREATE TABLE journeys(
    
    journey_id NUMBER NOT NULL
        CONSTRAINT journeys_journey_id_pk PRIMARY KEY,
  
    route_id NUMBER NOT NULL
        CONSTRAINT journeys_route_id_fk REFERENCES routes(route_id),
      
    shift_id NUMBER NOT NULL
        CONSTRAINT journeys_shift_id_fk REFERENCES shifts(shift_id),
      
    coach_id NUMBER NOT NULL
        CONSTRAINT coaches_coach_id_fk REFERENCES coaches(coach_id),
        
    --employee_id VARCHAR(15) NOT NULL
    --    CONSTRAINT employees_employee_id_fk REFERENCES employees (employee_id),    
    
    departure_datetime DATE 
        CONSTRAINT journeys_departure_datetime_nn NOT NULL,
    
    arrival_datetime DATE 
        CONSTRAINT journeys_arrival_datetime_nn NOT NULL, 
        
    current_stop NUMBER,
    
    stop_arrival_datetime DATE,
    
    stop_departed_datetime DATE,
        
    coach_status VARCHAR2(20) 
        CONSTRAINT journeys_coach_status_nn NOT NULL
        CONSTRAINT journeys_coach_status_chk
            CHECK (coach_status IN 
            ('Scheduled', 'Departed', 'On-route', 'At Stop', 'Arrived', 'Broken Down', 'Replacement Deployed', 
            'Cancelled', 'Complete'))
);