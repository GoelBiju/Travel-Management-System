﻿using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.DTO
{
    public class CoachDTO
    {
        [JsonProperty("coachId")]
        public int CoachId { get; set; }

        [JsonProperty("coachMake")]
        public string CoachMake { get; set; }

        [JsonProperty("coachModel")]
        public string CoachModel { get; set; }

        [JsonProperty("registrationPlate")]
        public string RegistratonPlate { get; set; }

        [JsonProperty("capacity")]
        public int capacity { get; set; }

        [JsonProperty("status")]
        public string status { get; set; }
    }
}