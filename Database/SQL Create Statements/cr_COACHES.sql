CREATE TABLE COACHES
(
    coach_id INT NOT NULL PRIMARY KEY,
    coach_make VARCHAR(35) NOT NULL,
    coach_model VARCHAR(35) NOT NULL,
    registration_plate VARCHAR(8) NOT NULL,
    capacity INT NOT NULL
    status VARCHAR(12) NOT NULL
);