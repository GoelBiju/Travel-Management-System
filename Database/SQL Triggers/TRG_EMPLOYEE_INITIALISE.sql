/*
    CREATE TRIGGER employee initialise
    
    Responsible for incrementing the customer id 
    of the next customer record to be inserted
    by using the customer ID sequence.
*/

CREATE OR REPLACE TRIGGER trg_employee_initalise
BEFORE INSERT ON employees FOR EACH ROW
BEGIN
    /* Check what the job role is and assign the correct employee prefix 
        Administrator/Driver (A/D) */
    IF :NEW.job_role = 'Driver' THEN
        :NEW.employee_id := 'D' || seq_employee_id.nextval;
    ELSIF :NEW.job_role = 'Administrator' THEN
        :NEW.employee_id := 'A' || seq_employee_id.nextval;
    ELSE
        RAISE_APPLICATION_ERROR(-2000, 'Could not recognise the job role.');
    END IF;
END;