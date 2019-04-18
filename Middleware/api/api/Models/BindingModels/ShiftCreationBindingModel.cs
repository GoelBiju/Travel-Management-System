using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.BindingModels
{
    public class ShiftCreationBindingModel
    {
        [JsonProperty("employeeId")]
        [JsonRequired]
        public string EmployeeId { get; set; }

        [JsonProperty("startDateTime")]
        [JsonRequired]
        public DateTime StartDateTime { get; set; }

        [JsonProperty("endDateTime")]
        [JsonRequired]
        public DateTime EndDateTime { get; set; }
    }
}