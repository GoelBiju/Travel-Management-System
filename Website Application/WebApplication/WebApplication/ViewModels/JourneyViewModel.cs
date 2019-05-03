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

        [Display(Name = "Departure Time")]
        [JsonProperty("departureDateTime")]
        public DateTime DepartureDateTime { get; set; }

        [Display(Name = "Arrival Time")]
        [JsonProperty("arrivalDateTime")]
        public DateTime ArrivalDateTime { get; set; }

        [Display(Name = "Current Stop")]
        [JsonProperty("currentStopId")]
        [DisplayFormat(NullDisplayText = "In-active")]
        public int? CurrentStopId { get; set; }

        [Display(Name = "Current Stop - Arrival")]
        [JsonProperty("stopArrivalDateTime")]
        [DisplayFormat(NullDisplayText = "In-active")]
        public DateTime? StopArrivalDateTime { get; set; }

        [Display(Name = "Current Stop - Departure")]
        [JsonProperty("stopDepartedDateTime")]
        [DisplayFormat(NullDisplayText = "In-active")]
        public DateTime? StopDepartedDateTime { get; set; }

        [Display(Name = "Coach Status")]
        [JsonProperty("coachStatus")]
        public string CoachStatus { get; set; }
    }
}