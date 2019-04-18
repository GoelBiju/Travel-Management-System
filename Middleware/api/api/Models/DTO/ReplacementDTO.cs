using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.DTO
{
    public class ReplacementDTO
    {
        [JsonProperty("replacementId")]
        public int ReplacementId { get; set; }

        [JsonProperty("journeyId")]
        public int JourneyId { get; set; }

        [JsonProperty("coachId")]
        public int CoachId { get; set; }

        [JsonProperty("shiftId")]
        public int ShiftId { get; set; }

        [JsonProperty("requestedDateTime")]
        public DateTime RequestedDateTime { get; set; }

        [JsonProperty("completedDateTime")]
        public DateTime CompletedDateTime { get; set; }

        [JsonProperty("status")]
        public string Status { get; set; }
    }
}