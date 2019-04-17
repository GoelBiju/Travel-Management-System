/*
    PRCS252 - Travel Management System - CREATE TABLE bookings_history
    
    Attributes and relations:
*/

CREATE TABLE bookings_history(
    
    booking_id NUMBER
        CONSTRAINT bookings_history_booking_id_pk PRIMARY KEY,
        
    customer_id NUMBER NOT NULL
        CONSTRAINT bookings_history_customer_id_fk REFERENCES customers (customer_id),
        
    journey_id NUMBER
        CONSTRAINT bookings_history_journey_id NOT NULL,
        
    starting_stop NUMBER
        CONSTRAINT bookings_history_starting_stop NOT NULL,
        
    ending_stop NUMBER
        CONSTRAINT bookings_history_ending_stop NOT NULL,
        
    date_time DATE
        CONSTRAINT bookings_history_date_time NOT NULL,
        
    price NUMBER(6, 2) 
        CONSTRAINT bookings_history_price NOT NULL,
        
    amount_of_people NUMBER
        CONSTRAINT bookings_history_amount_of_people NOT NULL,
        
    status NUMBER(1)
        CONSTRAINT bookings_history_status NOT NULL
);    