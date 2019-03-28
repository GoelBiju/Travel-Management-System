using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.BindingModels
{
    public class EmployeeCreationBindingModel
    {
        [JsonProperty("firstName")]
        public string FirstName { get; set; }

        [JsonProperty("LastName")]
        public string LastName { get; set; }

        [JsonProperty("jobRole")]
        public string JobRole { get; set; }

        [JsonProperty("password")]
        public string Password { get; set; }
    }
}