using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WebApplication.ViewModels
{
    public class RoutesViewModel
    {
        [Display(Name = "route id")]
        [JsonProperty("routeId")]
        public int routeId { get; set; }

        [Display(Name = "departure station")]
        [JsonProperty("departureStation")]
        public int departureStation { get; set; }

        [Display(Name = "arrival station")]
        [JsonProperty("arrivalStation")]
        public int arrivalStation { get; set; }

    }
}