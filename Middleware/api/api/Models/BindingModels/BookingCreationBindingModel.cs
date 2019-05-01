using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Models.BindingModels
{
    public class BookingCreationBindingModel
    {
        [JsonProperty("customerId")]
        public int CustomerId { get; set; }

        [JsonProperty("journeyId")]
        public int JourneyId { get; set; }

        [JsonProperty("departingStopId")]
        public int DepartingStopId { get; set; }

        [JsonProperty("arrivalStopId")]
        public int ArrivalStopId { get; set; }

        [JsonProperty("bookedDateTime")]
        public DateTime BookedDateTime { get; set; }

        [JsonProperty("paymentId")]
        public string PaymentId { get; set; }

        [JsonProperty("passengersSenior")]
        public int PassengersSenior { get; set; }

        [JsonProperty("passengersAdult")]
        public int PassengersAdult { get; set; }

        [JsonProperty("passengersChildren")]
        public int PassengersChildren { get; set; }

        [JsonProperty("passengersInfant")]
        public int PassengersInfant { get; set; }

        [JsonProperty("amountPaid")]
        public decimal AmountPaid;
    }
}