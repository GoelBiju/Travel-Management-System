create or replace TRIGGER trg_check_journey_end_date
BEFORE INSERT ON journeys
FOR EACH ROW
BEGIN
  IF( (:new.arrival_datetime < sysdate) AND (:new.arrival_datetime > :new.departure_datetime))
  THEN
    RAISE_APPLICATION_ERROR( 
      -20001, 
      'Journeys need to made in future and not today' );
  END IF;
END;