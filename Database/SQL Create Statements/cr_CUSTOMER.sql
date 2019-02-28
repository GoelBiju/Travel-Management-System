CREATE TABLE CUSTOMERS(
    customer_id int NOT NULL, PRIMARY KEY (customer_id),
    first_name varchar(35) NOT NULL,
    last_name varchar(35) NOT NULL,
    date_of_birth date NOT NULL,
    address_line_1 varchar(50) NOT NULL,
    address_line_2 varchar(50),
    postcode varchar(8) NOT NULL,
    phone_number varchar(12),
    email varchar(62) NOT NULL,
    password varchar(16) NOT NULL
);

