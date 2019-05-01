using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.DTO
{
    public class RouteStopDTO
    {
        [JsonProperty("routeId")]
        public int RouteId { get; set; }

        [JsonProperty("stopId")]
        public int StopId { get; set; }

        [JsonProperty("positionInRoute")]
        public int PositionInRoute { get; set; }

        [JsonProperty("expectedArrivalDateTime")]
        public DateTime ExpectedArrivalDateTime { get; set; }

        [JsonProperty("stop")]
        public StopDTO Stop { get; set; }
    }
}