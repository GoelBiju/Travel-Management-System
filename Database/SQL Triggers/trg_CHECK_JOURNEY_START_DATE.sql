create or replace TRIGGER trg_check_journey_start_date

BEFORE INSERT ON journeys

FOR EACH ROW

BEGIN
  
	IF( :new.departure_datetime < sysdate)
  
	THEN
    
		RAISE_APPLICATION_ERROR( 
      
	 	   -20001, 
      
		  'Journeys need to made in future and not today' );
  
	END IF;

END;