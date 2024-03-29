/*
    PRCS252 - Travel Management System - CREATE coaches
    
    Attributes and relations:
        
        - coach_id;
        
        - coach_make;
        
        - coach_model;
        
        - registration_plate;
        
        - coach_capacity;
        
        - is_available;
    
*/


CREATE TABLE coaches(

    coach_id NUMBER
        CONSTRAINT coaches_coach_id_pk PRIMARY KEY,
    
    coach_make VARCHAR2(35)
        CONSTRAINT coaches_coach_make_nn NOT NULL,
    
    coach_model VARCHAR2(35)
        CONSTRAINT coaches_coach_model_nn NOT NULL,
    
    registration_plate VARCHAR2(8)
        CONSTRAINT coaches_registration_un UNIQUE
        CONSTRAINT coaches_registration_nn NOT NULL,
    
    coach_capacity NUMBER
        CONSTRAINT coaches_coach_capacity_nn NOT NULL,
    
    is_available NUMBER(1)
        CONSTRAINT coaches_is_available_nn NOT NULL
        CONSTRAINT is_available_chk
            CHECK (is_available IN (0, 1))
);