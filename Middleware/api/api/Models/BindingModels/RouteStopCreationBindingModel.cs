using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.BindingModels
{
    public class RouteStopCreationBindingModel
    {
        [JsonProperty("routeId")]
        [JsonRequired]
        public int RouteId { get; set; }

        [JsonProperty("stopId")]
        [JsonRequired]
        public int StopId { get; set; }

        [JsonProperty("positionInRoute")]
        [JsonRequired]
        public int PositionInRoute { get; set; }

        [JsonProperty("expectedArrivalDateTime")]
        [JsonRequired]
        public DateTime ExpectedArrivalDateTime { get; set; }
    }
}