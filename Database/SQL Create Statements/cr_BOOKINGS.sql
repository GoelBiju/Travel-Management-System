CREATE TABLE bookings(
    
    booking_id NUMBER NOT NULL
        CONSTRAINT bookings_booking_id_pk PRIMARY KEY,
        
    customer_id NUMBER NOT NULL
        CONSTRAINT bookings_customer_id_fk REFERENCES customers (customer_id),
        
    journey_id NUMBER NOT NULL
        CONSTRAINT bookings_journeys_id_fk REFERENCES journeys (journey_id),
    
    starting_stop NUMBER NOT NULL
        CONSTRAINT bookings_starting_stop REFERENCES stations (station_id),
    
    ending_stop NUMBER NOT NULL
        CONSTRAINT bookings_ending_stop REFERENCES stations (station_id),
    
    date_time DATE NOT NULL,
        
    price NUMBER(6, 2) NOT NULL,
    
    amount_of_people NUMBER,
    
    status NUMBER(1)
        CONSTRAINT status_active NOT NULL
        CONSTRAINT status_check
            CHECK(status IN (0, 1))

);
        
        
    
        
    
        
        