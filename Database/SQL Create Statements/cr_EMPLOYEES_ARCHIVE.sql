/*
    PRCS252 - Travel Management System
    
    Attributes and relations:
    
        - employee_id;
        
        - first_name;
        
        - last_name;
        
        - job_role;
*/


CREATE TABLE employees(

    employee_id VARCHAR2(15) 
        CONSTRAINT employees_employee_id_pk PRIMARY KEY,
    
    first_name VARCHAR2(35) 
        CONSTRAINT employees_first_name NOT NULL,
    
    last_name VARCHAR2(35)
        CONSTRAINT employees_last_name NOT NULL,
    
    job_role VARCHAR2(15)
        CONSTRAINT employees_job_role NOT NULL
);