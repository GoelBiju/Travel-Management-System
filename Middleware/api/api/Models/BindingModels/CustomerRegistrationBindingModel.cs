using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace api.Models.BindingModels
{
    public class CustomerRegistrationBindingModel
    {
        [JsonProperty("emailAddress")]
        [JsonRequired]
        public string emailAddress { get; set; }

        [JsonProperty("customerPassword")]
        [JsonRequired]
        public string customerPassword { get; set; }

        [JsonProperty("firstName")]
        [JsonRequired]
        public string firstName { get; set; }

        [JsonProperty("lastName")]
        [JsonRequired]
        public string lastName { get; set; }

        [JsonProperty("dateOfBirth")]
        [JsonRequired]
        public DateTime dateOfBirth { get; set; }

        [JsonProperty("addressLineOne")]
        [JsonRequired]
        public string addressLineOne { get; set; }

        [JsonProperty("addressLineTwo")]
        public string addressLineTwo { get; set; }

        [JsonProperty("postCode")]
        [JsonRequired]
        public string postCode { get; set; }

        [JsonProperty("mobileNumber")]
        [JsonRequired]
        public string mobileNumber { get; set; }
    }
}