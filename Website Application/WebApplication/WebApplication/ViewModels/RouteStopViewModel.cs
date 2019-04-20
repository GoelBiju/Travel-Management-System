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
        [Display(Name = "Stop Name")]
        [JsonProperty("stopName")]
        public string StopName { get; set; }

        [Display(Name = "Position In Route")]
        [JsonProperty("positionInRoute")]
        public int PositionInRoute { get; set; }
    }
}