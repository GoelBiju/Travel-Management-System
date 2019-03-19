using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.DTO
{
    public class EmployeeDTO
    {
        [JsonProperty("employeeId")]
        public string EmployeeId { get; set; }

        [JsonProperty("firstName")]
        public string FirstName { get; set; }

        [JsonProperty("lastName")]
        public string LastName { get; set; }

        [JsonProperty("jobRole")]
        public string JobRole { get; set; }

        [JsonProperty("shifts")]
        public ICollection<SHIFT> Shifts { get; set; }
    }
}