/*
    CREATE TRIGGER trg_archive_employees
    
    Inserts an employee record into the employees_archive table
    before an employee is removed from the employee table.
*/

CREATE OR REPLACE TRIGGER trg_archive_employees
BEFORE DELETE ON employees FOR EACH ROW
BEGIN
    INSERT INTO employees_archive
        (employee_id, first_name, last_name, job_role)
    VALUES
        (:OLD.employee_id, :OLD.first_name, :OLD.last_name, :OLD.job_role);
END;