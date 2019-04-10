using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WebApplication.ViewModels
{
    public class ShiftViewModel
    {
        
        [Display(Name = "Shift ID")]
        public int ShiftId { get; set; }

        [Display(Name = "Employee ID")]
        [JsonProperty("employeeid")]
        public int EmployeeId { get; set; }

        [Display(Name = "Route ID")]
        [JsonProperty("routeid")]
        public int RouteId { get; set; }


        [Display(Name = "Coach ID")]
        [JsonProperty("coachid")]
        public int CoachId { get; set; }


        [Display(Name = "Start of Shift")]
        [JsonProperty("startofshift")]
        public int StartOfShift { get; set; }

        [Display(Name = "End of Shift")]
        [JsonProperty("endofshift")]
        public int EndOfShift { get; set; }

    }
}