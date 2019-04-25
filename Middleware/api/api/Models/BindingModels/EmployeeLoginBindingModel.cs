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
        [JsonRequired]
        public string EmployeeId { get; set; }

        //For employee password entered:
        [JsonProperty("password")]
        [JsonRequired]
        public string Password { get; set; }
    }
}