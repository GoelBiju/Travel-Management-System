using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace api.Models.BindingModels
{
    public class EmployeeCreationBindingModel
    {
        [JsonProperty("firstName")]
        [JsonRequired]
        public string FirstName { get; set; }

        [JsonProperty("LastName")]
        [JsonRequired]
        public string LastName { get; set; }

        [JsonProperty("jobRole")]
        [JsonRequired]
        public string JobRole { get; set; }

        [JsonProperty("password")]
        [JsonRequired]
        public string Password { get; set; }
    }
}