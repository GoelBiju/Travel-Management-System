/*
    PRCS252 - Travel Management System - CREATE coaches
    
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
    
    active_status VARCHAR2(12)
        CONSTRAINT coaches_active_status_nn NOT NULL
);