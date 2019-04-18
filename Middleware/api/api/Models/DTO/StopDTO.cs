using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.DTO
{
    public class StopDTO
    {
        [JsonProperty("stopId")]
        public int StopId { get; set; }

        [JsonProperty("stopName")]
        public string StopName { get; set; }

        [JsonProperty("isStation")]
        public bool IsStation { get; set; }

        [JsonProperty("stopPostcode")]
        public string StopPostcode { get; set; }

        [JsonProperty("stopLatitude")] 
        public decimal StopLatitude { get; set; }

        [JsonProperty("stopLongitude")]
        public decimal StopLongitude { get; set; }
    }
}