/*
    PRCS252E - Travel Management System - CREATE customers
    
    Attributes and relations:
        
        - customer_id;
        
        - email_address;
        
        - customer_hashed_password;
        
        - password_salt;
        
        - first_name;
        
        - last_name;
        
        - date_of_birth;
        
        - address_line_one;
        
        - address_line_two;
        
        - postcode;
        
        - mobile_number;

        

    Changes 0.3:
        
        * Changed password attribute to customer_password
        
        * Changed phone_number to mobile_number
        
        * Renamed address_line_1 to address_line_one
        
        * Renamed address_line_2 to address_line_two
*/


CREATE TABLE customers(

    customer_id NUMBER 
        CONSTRAINT customers_customer_id_pk PRIMARY KEY,
        
    email_address VARCHAR2(62)
        CONSTRAINT customers_email_address_un UNIQUE
        CONSTRAINT customers_email_address_nn NOT NULL
        CONSTRAINT customers_email_address_chk
            CHECK (REGEXP_LIKE(email_address, '^\w+(\.\w+)*@\w+(\.\w+)+$')),
    
    customer_hashed_password VARCHAR2(100) 
        CONSTRAINT customers_customer_hashed_password_nn NOT NULL,
--        CONSTRAINT customers_customer_password_chk
--            CHECK (REGEXP_LIKE(customer_password, '^[a-z0-9A-Z]{7,16}$')),

    password_salt VARCHAR2(100)
        CONSTRAINT customers_password_salt_nn NOT NULL,
    
    first_name VARCHAR2(35) 
        CONSTRAINT customers_first_name_nn NOT NULL
        CONSTRAINT customers_first_name_chk_alpha
            CHECK (REGEXP_LIKE(first_name, '^[A-Za-z-'']+$'))
        CONSTRAINT customers_first_name_chk_initcap
            CHECK (first_name = INITCAP(first_name)),
    
    last_name VARCHAR2(35) 
        CONSTRAINT customers_last_name_nn NOT NULL
        CONSTRAINT customers_last_name_chk_alpha
            CHECK (REGEXP_LIKE(last_name, '^[A-Za-z-'']+$'))
        CONSTRAINT customers_last_name_chk_initcap
            CHECK (last_name = INITCAP(last_name)),
    
    date_of_birth DATE 
        CONSTRAINT customers_date_of_birth_nn NOT NULL,
    
    address_line_one VARCHAR2(50) 
        CONSTRAINT customers_address_line_one_nn NOT NULL
        CONSTRAINT customers_address_line_one_chk 
            CHECK (REGEXP_LIKE(address_line_one, '^[A-Za-z0-9 -]+$')),
    
    address_line_two VARCHAR2(50)
        CONSTRAINT customers_address_line_two_chk 
            CHECK (REGEXP_LIKE(address_line_two, '^[A-Za-z0-9 -]+$')),
    
    postcode VARCHAR2(8)
        CONSTRAINT customers_postcode_nn NOT NULL
        CONSTRAINT customers_postcode_chk
            CHECK (REGEXP_LIKE(postcode, '^[A-Z0-9 ]+$')),
    
    mobile_number VARCHAR2(11)
        CONSTRAINT customers_mobile_number_nn NOT NULL
        CONSTRAINT customers_mobile_number_chk
            CHECK (REGEXP_LIKE(mobile_number, '^07[0-9]{9}$'))
);

