/*
    PRCS252 - Travel Management System - CREATE TABLE bookings

    Attributes and relations:
    
        - booking_reference;
        
        - customer_id;
        
        - journey_id;
        
        - departing_stop;
        
        - arrival_stop;
        
        - booked_datetime;
        
        - 
        
        - price;
        
        - status;
*/


CREATE TABLE bookings(
    
    -- NOTE: booking_reference should be similar to an e-ticket reference code. 
    --       This could be a VARCHAR2 with an alphanumeric value.
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
        CONSTRAINT bookings_booked_datetime NOT NULL,
    
    --amount_of_people NUMBER,
    
    passengers_senior NUMBER
        CONSTRAINT bookings_passengers_senior_nn NOT NULL,
        
    passengers_adult NUMBER
        CONSTRAINT bookings_passengers_adult_nn NOT NULL,
        
    passengers_teenager NUMBER
        CONSTRAINT bookings_passengers_teenager_nn NOT NULL,
        
    passengers_infant NUMBER
        CONSTRAINT bookings_passengers_infant_nn NOT NULL,
        
    ticket_price NUMBER(6, 2)
        CONSTRAINT bookings_ticket_price_nn NOT NULL,
    
    status VARCHAR2(10) 
        cONSTRAINT bookings_status_nn NOT NULL
        CONSTRAINT bookings_status_chk
            CHECK(status IN ('Confirmed', 'Checked-in', 'Complete'))
);
        
        
    
        
    
        
        