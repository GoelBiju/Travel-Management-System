using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.BindingModels
{
    public class JourneyUpdateBindingModel
    {
        [JsonProperty("journeyId")]
        [JsonRequired]
        public int JourneyId { get; set; }

        [JsonProperty("currentStopId")]
        [JsonRequired]
        public int CurrentStopId { get; set; }

        [JsonProperty("stopArrivalDateTime")]
        public DateTime? StopArrivalDateTime { get; set; }

        [JsonProperty("stopDepartedDateTime")]
        public DateTime? StopDepartedDateTime { get; set; }

        [JsonProperty("coachStatus")]
        [JsonRequired]
        public string CoachStatus { get; set; }
    }
}