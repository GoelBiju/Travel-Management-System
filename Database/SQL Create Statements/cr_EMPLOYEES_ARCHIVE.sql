/*
    PRCS252 - Travel Management System
    
    Attributes and relations:
    
        - employee_id;
        
        - first_name;
        
        - last_name;
        
        - job_role;
*/


CREATE TABLE employees_archive(

    employee_id VARCHAR2(15)
        CONSTRAINT employees_archive_employee_id_pk PRIMARY KEY,
    
    first_name VARCHAR2(35)
        CONSTRAINT employees_archive_first_name NOT NULL,
        --CONSTRAINT employees_archive_first_name_chk_alpha
        --    CHECK (REGEXP_LIKE(first_name, '^[A-Za-z-'']+$'))
        --CONSTRAINT employees_archive_first_name_chk_initcap
        --    CHECK (first_name = INITCAP(first_name)),
    
    last_name VARCHAR2(35)
        CONSTRAINT employees_archive_last_name NOT NULL,
        --CONSTRAINT employees_archive_last_name_chk_alpha
        --    CHECK (REGEXP_LIKE(last_name, '^[A-Za-z-'']+$'))
        --CONSTRAINT employees_archive_last_name_chk_initcap
        --    CHECK (last_name = INITCAP(last_name)),
    
    job_role VARCHAR2(15)
        CONSTRAINT employees_archive_job_role NOT NULL
        --CONSTRAINT employees_archive_job_role_chk
        --    CHECK (job_role IN ('Driver', 'Administrator', 'Manager'))
);