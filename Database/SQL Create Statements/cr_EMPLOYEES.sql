/*
    PRCS252E - Travel Management System - CREATE employees
    
    Attributes and relations:
    
        - employee_id;
        
        - employee_password;
        
        - first_name;
        
        - last_name;
        
        - job_role;      
*/


CREATE TABLE employees(

    employee_id VARCHAR2(15)
        CONSTRAINT employees_employee_id_pk PRIMARY KEY,
        
    employee_password VARCHAR2(16)
        CONSTRAINT employees_employee_password NOT NULL
        CONSTRAINT employees_employee_password_chk
            CHECK (REGEXP_LIKE(employee_password, '^[a-z0-9A-Z]{7,16}$')),

    first_name VARCHAR2(35)
        CONSTRAINT employees_first_name_nn NOT NULL
        CONSTRAINT employees_first_name_chk_alpha
            CHECK (REGEXP_LIKE(first_name, '^[A-Za-z-'']+$'))
        CONSTRAINT employees_first_name_chk_initcap
            CHECK (first_name = INITCAP(first_name)),

    last_name VARCHAR2(35)
        CONSTRAINT employees_last_name NOT NULL
        CONSTRAINT employees_last_name_chk_alpha
            CHECK (REGEXP_LIKE(last_name, '^[A-Za-z-'']+$'))
        CONSTRAINT employees_last_name_chk_initcap
            CHECK (last_name = INITCAP(last_name)),

    job_role VARCHAR2(15)
        CONSTRAINT employees_job_role NOT NULL
        CONSTRAINT employees_job_role_chk
            CHECK (job_role IN ('Driver', 'Administrator', 'Manager'))
);