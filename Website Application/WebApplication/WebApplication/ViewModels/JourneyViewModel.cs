using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;
using Newtonsoft.Json;

namespace WebApplication.ViewModels
{
    public class JourneyViewModel
    {
        [Display(Name = "Journey No.")]
        [JsonProperty("journeyId")]
        public int JourneyId { get; set; }

        [Display(Name = "Route No.")]
        [JsonProperty("routeId")]
        public int RouteId { get; set; }

        [Display(Name = "Coach No.")]
        [JsonProperty("coachId")]
        public int CoachId { get; set; }

        [Display(Name = "Shift No.")]
        [JsonProperty("shiftId")]
        public int ShiftId { get; set; }

        [Display(Name = "Departure Time")]
        [JsonProperty("startDateTime")]
        public DateTime DepartureDateTime { get; set; }

        [Display(Name = "Arrival Time")]
        [JsonProperty("endDateTime")]
        public DateTime ArrivalDateTime { get; set; }
    }
}