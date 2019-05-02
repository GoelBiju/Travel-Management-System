CREATE OR REPLACE TRIGGER trg_booking_initialise
BEFORE INSERT ON bookings FOR EACH ROW
BEGIN
    SELECT EXTRACT(YEAR FROM SYSDATE) INTO :NEW.booking_reference
    FROM sys.dual;
    
    
END;