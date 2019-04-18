using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WebApplication.ViewModels
{
    public class StopViewModel
    {
        [Display(Name = "Stop No.")]
        [JsonProperty("stopId")]
        public int StopId { get; set; }

        [Display(Name = "Stop Name")]
        [JsonProperty("stopName")]
        public string StopName { get; set; }

        [Display(Name = "Station")]
        [JsonProperty("isStation")]
        public bool IsStation { get; set; }

        [Display(Name = "Stop Postcode")]
        [JsonProperty("stopPostcode")]
        public string StopPostcode { get; set; }

        [Display(Name = "Stop Latitude")]
        [JsonProperty("stopLatitude")]
        [DisplayFormat(NullDisplayText = "None")]
        public decimal? StopLatitude { get; set; }

        [Display(Name = "Stop Longitude")]
        [JsonProperty("stopLongitude")]
        [DisplayFormat(NullDisplayText = "None")]
        public decimal? StopLongitude { get; set; }
    }
}