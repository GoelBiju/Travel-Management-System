/*
    PRCS252E - Travel Management System
    
    Attributes and relations:
    
        - coach_id;
        
        - coach_make;
        
        - coach_model;
        
        - registration_plate;
        
        - coach_capacity;
*/

CREATE TABLE coaches_archive(

    coach_id NUMBER
        CONSTRAINT coaches_archive_coach_id_pk PRIMARY KEY,
        
    coach_make VARCHAR2(35)
        CONSTRAINT coaches_archive_coach_make_nn NOT NULL,
        
    coach_model VARCHAR2(35)
        CONSTRAINT coaches_archive_coach_model_nn NOT NULL,
        
    registration_plate VARCHAR2(8)
        CONSTRAINT coaches_archive_registration_plate_nn NOT NULL,
        
    coach_capacity NUMBER
        CONSTRAINT coaches_archive_coach_capacity_nn NOT NULL
);