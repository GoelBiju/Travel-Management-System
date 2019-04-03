/*
    CREATE TRIGGER trg_archive_coaches
    
*/

CREATE OR REPLACE TRIGGER trg_archive_coaches
BEFORE DELETE ON coaches FOR EACH ROW
BEGIN
    INSERT INTO coaches_archive
        (coach_id, coach_make, coach_model, registration_plate, coach_capacity)
    VALUES
        (:OLD.coach_id, :OLD.coach_make, :OLD.coach_model, :OLD.registration_plate, 
        :OLD.coach_capacity);
END;