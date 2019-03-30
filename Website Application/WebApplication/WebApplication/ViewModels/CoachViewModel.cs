using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WebApplication.Models
{
    public class CoachViewModel
    {
        [Display(Name = "Coach ID")]
        public int CoachId { get; set; }

        [Display(Name = "Make")]
        [JsonProperty("coachMake")]
        public string CoachMake { get; set; }

        [Display(Name = "Model")]
        [JsonProperty("coachModel")]
        public string CoachModel { get; set; }

        [Display(Name = "Registration")]
        [JsonProperty("registrationPlate")]
        public string RegistrationPlate { get; set; }

        [Display(Name = "Capacity")]
        [JsonProperty("coachCapacity")]
        public int CoachCapacity { get; set; }

        [Display(Name = "Active")]
        [JsonProperty("isActive")]
        public bool IsActive { get; set; }
    }
}