using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.BindingModels
{
    public class JourneyBindingModel
    {
        [JsonProperty("journeyid")]
        [JsonRequired]
        public int JourneyId { get; set; }


        [JsonProperty("routeid")]
        [JsonRequired]
        public int RouteId { get; set; }


        [JsonProperty("coachid")]
        [JsonRequired]
        public int CoachId { get; set; }


        [JsonProperty("employeeid")]
        [JsonRequired]
        public string EmployeeId { get; set; }


        [JsonProperty("startdatetime")]
        [JsonRequired]
        public DateTime DepartureTime { get; set; }


        [JsonProperty("enddatetime")]
        [JsonRequired]
        public DateTime ArrivalTime { get; set; }
    }
}