CREATE OR REPLACE TRIGGER trg_booking_initialise
BEFORE INSERT ON bookings FOR EACH ROW
BEGIN
    SELECT seq_booking_reference.nextval
    INTO :NEW.booking_reference
    FROM sys.dual;
END;