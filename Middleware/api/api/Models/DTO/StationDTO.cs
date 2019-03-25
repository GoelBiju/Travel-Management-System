using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.DTO
{
    public class StationDTO
    {
        [JsonProperty("stationId")]
        public int StationId { get; set; }

        [JsonProperty("stationName")]
        public string StationName { get; set; }
    }
}