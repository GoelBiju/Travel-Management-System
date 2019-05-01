using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.DTO
{
    public class BookingDTO
    {
        [JsonProperty("bookingReference")]
        public int BookingReference { get; set; }

        [JsonProperty("customerId")]
        public int CustomerId { get; set; }

        [JsonProperty("journeyId")]
        public JourneyDTO Journey { get; set; }

        [JsonProperty("departingStop")]
        public StopDTO DepartingStop { get; set; }

        [JsonProperty("arrivalStop")]
        public StopDTO ArrivalStop { get; set; }

        [JsonProperty("bookedDateTime")]
        public DateTime BookedDateTime { get; set; }

        [JsonProperty("passengersSenior")]
        public int PassengersSenior { get; set; }

        [JsonProperty("passengersAdult")]
        public int PassengersAdult { get; set; }

        [JsonProperty("passengersChildren")]
        public int PassengersChildren { get; set; }

        [JsonProperty("passengersInfant")]
        public int PassengersInfant { get; set; }

        [JsonProperty("amountPaid")]
        public decimal AmountPaid { get; set; }

        [JsonProperty("status")]
        public string Status { get; set; }
    }
}