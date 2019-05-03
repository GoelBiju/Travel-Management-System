using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.BindingModels
{
    public class JourneyCreationBindingModel
    {
        //[JsonProperty("journeyId")]
        //[JsonRequired]
        //public int JourneyId { get; set; }

        [JsonProperty("routeId")]
        [JsonRequired]
        public int RouteId { get; set; }

        [JsonProperty("coachId")]
        [JsonRequired]
        public int CoachId { get; set; }

        [JsonProperty("departureDateTime")]
        [JsonRequired]
        public DateTime DepartureDateTime { get; set; }

        [JsonProperty("arrivalDateTime")]
        [JsonRequired]
        public DateTime ArrivalDateTime { get; set; }

        //[JsonProperty("currentStop")]
        //[JsonRequired]
        //public int? CurrentStop { get; set; }

        //[JsonProperty("stopArrivalDateTime")]
        //[JsonRequired]
        //public DateTime? StopArrivalDateTime { get; set; }

        //[JsonProperty("stopDepartedDateTime")]
        //[JsonRequired]
        //public DateTime? StopDepartedDateTime { get; set; }

        //[JsonProperty("coachStatus")]
        //[JsonRequired]
        //public DateTime CoachStatus { get; set; }
    }
}