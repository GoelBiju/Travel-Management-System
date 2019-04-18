/*
    CREATE TRIGGER routes initialise
    
*/

CREATE OR REPLACE TRIGGER trg_route_initialise
BEFORE INSERT ON routes FOR EACH ROW
BEGIN
    SELECT seq_route_id.nextval
    INTO :NEW.route_id
    FROM sys.dual;
END;