using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Newtonsoft.Json;

namespace WebApplication.ViewModels
{
    public class JourneyViewModel
    {
        [Display(Name = "Journey ID")]
        public int JourneyId { get; set; }

        [Display(Name = "Route ID")]
        [JsonProprty("routeid")]
        public int routeId { get; set; }

        [Display(Name = "Coach ID")]
        [JsonProprty("coachid")]
        public int CoachId { get; set; }

        [Display(Name = "Employee ID")]
        [JsonProprty("employeeid")]
        public int EmployeeId { get; set; }

        [Display(Name = "Departure Time")]
        [JsonProperty("startdatetime")]
        public string DepartureTime { get; set; }

        [Display(Name = "Arrival Time")]
        [JsonProperty("enddatetime")]
        public string ArrivalTime { get; set; }
    }
}