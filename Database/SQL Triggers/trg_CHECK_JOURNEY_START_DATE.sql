create or replace TRIGGER trg_check_journey_start_date

BEFORE INSERT OR UPDATE ON journeys

FOR EACH ROW

BEGIN
  
	IF( :new.start_date_time < sysdate)
  
	THEN
    
		RAISE_APPLICATION_ERROR( 
      
	 	   -20001, 
      
		  'Journeys need to made in future and not today' );
  
	END IF;

END;