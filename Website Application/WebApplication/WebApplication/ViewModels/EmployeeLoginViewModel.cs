using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WebApplication.ViewModels
{
    public class EmployeeLoginViewModel
    {
        [Display(Name = "Employee ID")]
        [JsonProperty("employeeID")]
        
        public string EmployeeID { get; set; }

        [Display(Name = "Password")]
        [JsonProperty("password")]
        
        public string Password { get; set; }
    }
}