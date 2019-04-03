/*

    PRCS252 - Travel Management System - CREATE shifts
    
    Attributes and relations:
    
        - shift_id;
        
        - employee_id;
        
        - route_id;
        
        - coach_id;
        
        - start_datetime;
        
        - end_datetime;

*/

CREATE TABLE shifts(
    
    shift_id NUMBER
        CONSTRAINT shifts_shift_id_pk PRIMARY KEY,
        
    employee_id VARCHAR2(15) NOT NULL
        CONSTRAINT shifts_employee_id_fk REFERENCES employees(employee_id),
    
    route_id NUMBER NOT NULL
        CONSTRAINT shifts_route_id_fk REFERENCES routes(route_id),
    
    coach_id NUMBER NOT NULL
        CONSTRAINT shifts_coach_id_fk REFERENCES coaches(coach_id),
        
    start_datetime DATE
        CONSTRAINT shifts_start_datetime_nn NOT NULL,
        
    end_datetime DATE
        CONSTRAINT shifts_end_datetime_nn NOT NULL
);