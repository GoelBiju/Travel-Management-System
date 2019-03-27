using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Newtonsoft.Json;
using System.ComponentModel.DataAnnotations;

namespace api.Models.BindingModels
{
    public class EmployeeLoginBindingModel
    {
        //For employee ID entered:
        [JsonProperty("employeeID")]
        [Required]
        public string employeeID { get; set; }

        //For employee password entered:
        [JsonProperty("password")]
        [Required]
        public string password { get; set; }
    }
}