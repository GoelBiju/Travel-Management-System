CREATE OR REPLACE TRIGGER trg_journey_initalise
BEFORE INSERT ON journeys FOR EACH ROW
BEGIN
    SELECT seq_journey_id.nextval
    INTO :NEW.journey_id
    FROM sys.dual;
END;