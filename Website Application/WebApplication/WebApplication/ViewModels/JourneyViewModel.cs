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
        [Display(Name = "Journey ID")]
        [JsonProperty("journeyid")]
        public int JourneyId { get; set; }

        [Display(Name = "Route ID")]
        [JsonProperty("routeid")]
        public int RouteId { get; set; }

        [Display(Name = "Coach ID")]
        [JsonProperty("coachid")]
        public int CoachId { get; set; }

        [Display(Name = "Employee ID")]
        [JsonProperty("employeeid")]
        public string EmployeeId { get; set; }

        [Display(Name = "Departure Time")]
        [JsonProperty("startdatetime")]
        public DateTime DepartureTime { get; set; }

        [Display(Name = "Arrival Time")]
        [JsonProperty("enddatetime")]
        public DateTime ArrivalTime { get; set; }
    }
}