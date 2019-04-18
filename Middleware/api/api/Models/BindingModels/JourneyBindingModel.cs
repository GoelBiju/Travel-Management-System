using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.BindingModels
{
    public class JourneyBindingModel
    {
        //[JsonProperty("journeyid")]
        //[JsonRequired]
        //public int JourneyId { get; set; }

        [JsonProperty("routeId")]
        [JsonRequired]
        public int RouteId { get; set; }

        [JsonProperty("shiftId")]
        [JsonRequired]
        public int ShiftId { get; set; }

        [JsonProperty("coachId")]
        [JsonRequired]
        public int CoachId { get; set; }

        [JsonProperty("startDateTime")]
        [JsonRequired]
        public DateTime DepartureDateTime { get; set; }

        [JsonProperty("endDateTime")]
        [JsonRequired]
        public DateTime ArrivalDateTime { get; set; }
    }
}