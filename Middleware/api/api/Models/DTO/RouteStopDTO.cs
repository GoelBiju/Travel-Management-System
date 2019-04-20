using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.DTO
{
    public class RouteStopDTO
    {
        [JsonProperty("stopId")]
        public int StopId { get; set; }

        [JsonProperty("stopName")]
        public string StopName { get; set; }

        [JsonProperty("positionInRoute")]
        public int PositionInRoute { get; set; }
    }
}