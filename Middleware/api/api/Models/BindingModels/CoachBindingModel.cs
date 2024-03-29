﻿using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.BindingModels
{
    public class CoachBindingModel
    {
        [JsonProperty("coachMake")]
        [JsonRequired]
        public string CoachMake { get; set; }

        [JsonProperty("coachModel")]
        [JsonRequired]
        public string CoachModel { get; set; }

        [JsonProperty("registrationPlate")]
        [JsonRequired]
        public string RegistratonPlate { get; set; }

        [JsonProperty("coachCapacity")]
        [JsonRequired]
        public int CoachCapacity { get; set; }

        [JsonProperty("isAvailable")]
        [JsonRequired]
        public bool IsAvailable { get; set; }
    }
}