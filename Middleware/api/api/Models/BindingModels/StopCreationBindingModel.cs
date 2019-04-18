using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.BindingModels
{
    public class StopCreationBindingModel
    {
        [JsonProperty("stopName")]
        [JsonRequired]
        public string StopName { get; set; }

        [JsonProperty("isStation")]
        [JsonRequired]
        public bool IsStation { get; set; }

        [JsonProperty("stopPostcode")]
        [JsonRequired]
        public string StopPostcode { get; set; }

        [JsonProperty("stopLatitude")]
        public decimal? StopLatitude { get; set; }

        [JsonProperty("stopLongitude")]
        public decimal? StopLongitude { get; set; }
    }
}