CREATE OR REPLACE TRIGGER trg_stop_initalise
BEFORE INSERT ON stops FOR EACH ROW
BEGIN
    SELECT seq_stop_id.nextval
    INTO :NEW.stop_id
    FROM sys.dual;
END;