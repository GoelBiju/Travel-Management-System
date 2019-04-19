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
        [Display(Name = "Route No.")]
        [JsonProperty("routeId")]
        public int routeId { get; set; }

        [Display(Name = "Departure Station")]
        [JsonProperty("departureStation")]
        public string departureStation { get; set; }


        [Display(Name = "Arrival Station")]
        [JsonProperty("arrivalStation")]
        public string arrivalStation { get; set; }

    }
}