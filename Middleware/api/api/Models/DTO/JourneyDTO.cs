using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Newtonsoft.Json;

namespace api.Models.DTO
{
    public class JourneyDTO
    {
        [JsonProperty("journeyId")]
        public int JourneyId { get; set; }

        [JsonProperty("routeId")]
        public int RouteId { get; set; }

        [JsonProperty("shiftId")]
        public int ShiftId { get; set; }
        
        [JsonProperty("coachId")]
        public int CoachId { get; set; }
        
        [JsonProperty("departureDateTime")]
        public DateTime DepartureDateTime { get; set; }

        [JsonProperty("arrivalDateTime")]
        public DateTime ArrivalDateTime { get; set; }

        [JsonProperty("currentStopId")]
        public int? CurrentStopId { get; set; }

        [JsonProperty("stopArrivalDateTime")]
        public DateTime? StopArrivalDateTime { get; set; }

        [JsonProperty("stopDepartedDateTime")]
        public DateTime? StopDepartedDateTime { get; set; }

        [JsonProperty("coachStatus")]
        public string CoachStatus { get; set; }

        [JsonProperty("route")]
        public RouteDTO Route { get; set; }

        [JsonProperty("currentStop")]
        public StopDTO CurrentStop { get; set; }
    }
}