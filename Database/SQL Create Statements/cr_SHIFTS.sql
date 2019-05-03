/*

    PRCS252 - Travel Management System - CREATE shifts
    
    Attributes and relations:
    
        - shift_id;
        
        - employee_id;
        
        - start_datetime;
        
        - end_datetime;
*/

CREATE TABLE shifts(
    
    shift_id NUMBER
        CONSTRAINT shifts_shift_id_pk PRIMARY KEY,
        
    employee_id VARCHAR2(15) NOT NULL
        CONSTRAINT shifts_employee_id_fk REFERENCES employees(employee_id),
    
    journey_id NUMBER NOT NULL
        CONSTRAINT shifts_journey_id_fk REFERENCES journeys(journey_id),
        
    start_datetime DATE
        CONSTRAINT shifts_start_datetime_nn NOT NULL,
        
    end_datetime DATE
        CONSTRAINT shifts_end_datetime_nn NOT NULL
);