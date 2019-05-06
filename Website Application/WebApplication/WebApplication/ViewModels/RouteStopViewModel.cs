using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WebApplication.ViewModels
{
    public class RouteStopViewModel
    {
        [JsonProperty("routeId")]
        public int RouteId { get; set; }

        [JsonProperty("stopId")]
        public int StopId { get; set; }

        [Display(Name = "Stop Name")]
        [JsonProperty("stop")]
        public StopViewModel Stop { get; set; }

        [Display(Name = "Position In Route")]
        [JsonProperty("positionInRoute")]
        public int PositionInRoute { get; set; }
    }
}