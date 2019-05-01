/*
    PRCS252 - Travel Management System - CREATE TABLE bookings_history
    
    Attributes and relations:
*/

CREATE TABLE bookings_history(
    
    booking_reference NUMBER
        CONSTRAINT bookings_history_booking_reference_pk PRIMARY KEY,
        
    customer_id NUMBER NOT NULL
        CONSTRAINT bookings_history_customer_id_fk REFERENCES customers (customer_id),
        
    journey_id NUMBER
        CONSTRAINT bookings_history_journey_id_nn NOT NULL,
        
    departing_stop NUMBER
        CONSTRAINT bookings_history_departing_stop_nn NOT NULL,
        
    arrival_stop NUMBER
        CONSTRAINT bookings_history_arrival_stop_nn NOT NULL,
        
    booked_datetime DATE
        CONSTRAINT bookings_history_booked_datetime_nn NOT NULL,
        
    payment_id VARCHAR2(100)
        CONSTRAINT bookings_history_payment_id_nn NOT NULL,
        
    passengers_senior NUMBER
        CONSTRAINT bookings_history_passengers_senior_nn NOT NULL,
        
    passengers_adult NUMBER
        CONSTRAINT bookings_history_passengers_adult_nn NOT NULL,
        
    passengers_children NUMBER
        CONSTRAINT bookings_history_passengers_children_nn NOT NULL,
        
    passengers_infant NUMBER
        CONSTRAINT bookings_history_passengers_infant_nn NOT NULL,
        
    amount_paid NUMBER(6, 2) 
        CONSTRAINT bookings_history_amount_paid_nn NOT NULL,
     
    status NUMBER(1)
        CONSTRAINT bookings_history_status NOT NULL
);    