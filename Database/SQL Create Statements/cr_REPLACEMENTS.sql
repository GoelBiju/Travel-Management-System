/*
    PRCS252 - Travel Management System - CREATE replacements
    
    Attributes and relations:
    
        - replacement_id;
        
        - journey_id;
        
        - replacement_coach_id;
        
        - shift_id;
        
        - requested_datetime;
        
        - completed_datetime;
        
        - status ("Waiting", "Deployed", "Complete", "Cancelled", "Incomplete");
*/

CREATE TABLE replacements(

    replacement_id NUMBER 
        CONSTRAINT replacements_replacement_id_pk PRIMARY KEY,
        
    journey_id NUMBER NOT NULL
        CONSTRAINT replacements_journey_id_fk REFERENCES journeys (journey_id),
        
    coach_id NUMBER NOT NULL
        CONSTRAINT replacements_coach_id_fk REFERENCES coaches (coach_id),
        
    shift_id NUMBER NOT NULL
        CONSTRAINT replacements_shift_id_fk REFERENCES shifts (shift_id),
        
    requested_datetime DATE 
        CONSTRAINT replacements_request_datetime_nn NOT NULL,
       
    completed_datetime DATE,
    
    status VARCHAR2(20)
        CONSTRAINT replacements_status_nn NOT NULL
        CONSTRAINT replacements_status_chk
            CHECK (status IN 
            ('Waiting', 'Deployed', 'Complete', 'Cancelled', 'Incomplete')) 
);
    