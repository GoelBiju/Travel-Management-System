CREATE TABLE COACHES
(
    coach_id INT
    CONSTRAINT coaches_coach_id PRIMARY KEY,
    
    coach_make VARCHAR(35)
    CONSTRAINT coaches_coach_make_notnull NOT NULL,
    
    coach_model VARCHAR(35)
    CONSTRAINT coaches_coach_model_notnull NOT NULL,
    
    registration_plate VARCHAR(8)
    CONSTRAINT coaches_registration_notnull NOT NULL,
    CONSTRAINT coaches_registration_unique UNIQUE,
    
    coach_capacity INT
    CONSTRAINT coaches_coach_capacity_notnull NOT NULL,
    
    active_status VARCHAR(12)
    CONSTRAINT coaches_active_status_notnull NOT NULL
);