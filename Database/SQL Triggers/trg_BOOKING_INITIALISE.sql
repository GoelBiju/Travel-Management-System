CREATE OR REPLACE TRIGGER trg_booking_initialise
BEFORE INSERT ON bookings FOR EACH ROW
BEGIN
    /* Concatenate the month, day and next sequence value to create the new booking reference. */
    :NEW.booking_reference := EXTRACT(YEAR FROM SYSDATE) || EXTRACT (MONTH FROM SYSDATE) || EXTRACT(DAY FROM SYSDATE) || seq_booking_reference.nextval;
END;