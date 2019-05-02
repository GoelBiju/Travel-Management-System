using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.BindingModels
{
    public class BookingUpdateBindingModel
    {
        // TODO: Include other properties which can be updated.
        [JsonProperty("bookingReference")]
        public int BookingReference { get; set; }

        [JsonProperty("status")]
        public string Status { get; set; }
    }
}