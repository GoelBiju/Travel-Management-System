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

        [JsonProperty("departingStop")]
        public int DepartingStop { get; set; }

        [JsonProperty("arrivalStop")]
        public int ArrivalStop { get; set; }

        [JsonProperty("bookedDateTime")]
        public DateTime BookedDateTime { get; set; }

        [JsonProperty("passengersSenior")]
        public int PassengersSenior { get; set; }

        [JsonProperty("passengersTeenager")]
        public int PassengersTeenager { get; set; }

        [JsonProperty("passengersInfant")]
        public int PassengersInfant { get; set; }

        [JsonProperty("ticketPrice")]
        public int TicketPrice { get; set; }

        [JsonProperty("status")]
        public int Status { get; set; }
    }
}