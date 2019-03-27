using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WebApplication.Models
{
    public class EmployeeViewModel
    {
        [Display(Name = "Employee ID")]
        public string EmployeeId { get; set; }

        [Display(Name = "First Name")]
        public string FirstName { get; set; }

        [Display(Name = "Last Name")]
        public string LastName { get; set; }

        [Display(Name = "Job Role")]
        public string JobRole { get; set; }

        public string Password { get; set; }
    }
}