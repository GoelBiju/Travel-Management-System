create or replace TRIGGER trg_archive_employees
BEFORE DELETE ON employees
FOR EACH ROW
BEGIN
INSERT INTO employees_archive
(
employee_id, first_name, last_name,
job_role
)
VALUES
(
:OLD.employee_id, :OLD.first_name, :OLD.last_name,
:OLD.job_role
);
END;