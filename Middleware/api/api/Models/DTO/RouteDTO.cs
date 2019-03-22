using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.DTO
{
    public class RouteDTO
    {
        [JsonProperty("routeId")]
        public string RouteId { get; set; }

        [JsonProperty("departureStation")]
        public string DepartureStation { get; set; }

        [JsonProperty("arrivalStation")]
        public string ArrivalStation { get; set; }
    }
}