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
            EMPLOYEE employee = db.EMPLOYEES.FirstOrDefault(e => e.EMPLOYEE_ID == employeeId && e.EMPLOYEE_PASSWORD == password);

            return employee;
        }
    }
}