using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WebApplication.ViewModels
{
    public class EmployeeViewModel
    {
        [Display(Name = "Employee ID")]
        public string EmployeeId { get; set; }

        [Display(Name = "First Name")]
        [JsonProperty("firstName")]
        public string FirstName { get; set; }

        [Display(Name = "Last Name")]
        [JsonProperty("lastName")]
        public string LastName { get; set; }

        [Display(Name = "Job Role")]
        [JsonProperty("jobRole")]
        public string JobRole { get; set; }

        [JsonProperty("password")]
        public string Password { get; set; }
    }
}