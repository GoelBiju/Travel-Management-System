using api.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Services
{
    public class EmployeeService
    {
        private Entities db = new Entities();

        public EMPLOYEE ValidateEmployee(string employeeId, string password)
        {
            EMPLOYEE employee = db.EMPLOYEES.FirstOrDefault(e => e.EMPLOYEE_ID == employeeId);

            if (employee != null)
            {
                string attemptedHash = Utilities.Security.GenerateSHA256Hash(password, employee.PASSWORD_SALT);
                if (attemptedHash.Equals(employee.EMPLOYEE_HASHED_PASSWORD))
                    return employee;
            }
            return null;
        }
    }
}