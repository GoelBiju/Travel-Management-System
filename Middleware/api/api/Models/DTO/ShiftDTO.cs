using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.DTO
{
    public class ShiftDTO
    {
        [JsonProperty("shiftId")]
        public int ShiftId { get; set; }

        [JsonProperty("employeeId")]
        public string EmployeeId { get; set; }

        [JsonProperty("routeId")]
        public string RouteId { get; set; }

        [JsonProperty("coachId")]
        public int CoachId { get; set; }

        [JsonProperty("startDatetime")]
        public DateTime startDatetime { get; set; }

        [JsonProperty("endDatetime")]
        public DateTime endDatetime { get; set; }
    }
}