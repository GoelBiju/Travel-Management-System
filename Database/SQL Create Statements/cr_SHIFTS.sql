CREATE TABLE shifts(
    shift_id NUMBER
        CONSTRAINT shifts_shift_id_pk PRIMARY KEY,
        
    employee_id VARCHAR2(112) NOT NULL
        CONSTRAINT shifts_employee_id_fk REFERENCES employees(employee_id),
    
    route_id VARCHAR(4) NOT NULL
        CONSTRAINT shifts_route_id_fk REFERENCES routes(route_id),
    
    coach_id NUMBER NOT NULL
        CONSTRAINT shifts_coach_id_fk REFERENCES coaches(coach_id)
);