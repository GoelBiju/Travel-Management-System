/*
    PRCS252 - Travel Management System - CREATE TABLE bookings

    Attributes and relations:
    
        - booking_reference;
        
        - customer_id;
        
        - journey_id;
        
        - departing_stop;
        
        - arrival_stop;
        
        - booked_datetime;
        
        - payment_id;
        
        - passengers_senior;
        
        - passengers_adult;
        
        - passengers_children;
        
        - passengers_infant;
        
        - amount_paid;
        
        - status;
*/


CREATE TABLE bookings(
    
    booking_reference NUMBER NOT NULL
        CONSTRAINT bookings_booking_reference_pk PRIMARY KEY,
        
    customer_id NUMBER NOT NULL
        CONSTRAINT bookings_customer_id_fk REFERENCES customers (customer_id),
        
    journey_id NUMBER NOT NULL
        CONSTRAINT bookings_journeys_id_fk REFERENCES journeys (journey_id),
    
    departing_stop NUMBER NOT NULL
        CONSTRAINT bookings_departing_stop REFERENCES stops (stop_id),
    
    arrival_stop NUMBER NOT NULL
        CONSTRAINT bookings_arrival_stop REFERENCES stops (stop_id),
    
    booked_datetime DATE 
        CONSTRAINT bookings_booked_datetime_nn NOT NULL,
        
    payment_id VARCHAR2(100)
        CONSTRAINT bookings_payment_id_nn NOT NULL,
    
    passengers_senior NUMBER
        CONSTRAINT bookings_passengers_senior_nn NOT NULL,
        
    passengers_adult NUMBER
        CONSTRAINT bookings_passengers_adult_nn NOT NULL,
        
    passengers_children NUMBER
        CONSTRAINT bookings_passengers_children_nn NOT NULL,
        
    passengers_infant NUMBER
        CONSTRAINT bookings_passengers_infant_nn NOT NULL,
        
    amount_paid NUMBER(6, 2)
        CONSTRAINT bookings_amount_paid_nn NOT NULL,
    
    status VARCHAR2(10) 
        cONSTRAINT bookings_status_nn NOT NULL
        CONSTRAINT bookings_status_chk
            CHECK(status IN ('Confirmed', 'Checked-in', 'Complete'))
);
        
        
    
        
    
        
        