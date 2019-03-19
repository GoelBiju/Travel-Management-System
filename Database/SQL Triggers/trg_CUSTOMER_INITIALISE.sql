/*
    CREATE TRIGGER customer initialise
    
    Responsible for incrementing the customer id 
    of the next customer record to be inserted
    by using the customer ID sequence.
*/

CREATE OR REPLACE TRIGGER trg_customer_initalise
BEFORE INSERT ON customers FOR EACH ROW
BEGIN
    SELECT seq_customer_id.nextval
    INTO :NEW.customer_id
    FROM sys.dual;
END;