/*
    CREATE TRIGGER shift initialise
*/

CREATE OR REPLACE TRIGGER trg_shift_initialise
BEFORE INSERT ON shifts FOR EACH ROW
BEGIN
    SELECT seq_shift_id.nextval
    INTO :NEW.shift_id
    FROM sys.dual;
END;