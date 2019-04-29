using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.BindingModels
{
    public class JourneyCreationBindingModel
    {
        [JsonProperty("routeId")]
        [JsonRequired]
        public int RouteId { get; set; }

        [JsonProperty("shiftId")]
        [JsonRequired]
        public int ShiftId { get; set; }

        [JsonProperty("coachId")]
        [JsonRequired]
        public int CoachId { get; set; }

        [JsonProperty("departureDateTime")]
        [JsonRequired]
        public DateTime DepartureDateTime { get; set; }

        [JsonProperty("arrivalDateTime")]
        [JsonRequired]
        public DateTime ArrivalDateTime { get; set; }


    }
}