using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Newtonsoft.Json;

namespace api.Models.DTO
{
    public class JourneyDTO
    {
        [JsonProperty("journeyid")]
        public int JourneyId { get; set; }

        
        [JsonProperty("routeid")]
        public int RouteId { get; set; }

        
        [JsonProperty("coachid")]
        public int CoachId { get; set; }

        
        [JsonProperty("employeeid")]
        public int EmployeeId { get; set; }

        
        [JsonProperty("startdatetime")]
        public string DepartureTime { get; set; }

        
        [JsonProperty("enddatetime")]
        public string ArrivalTime { get; set; }
    }
}