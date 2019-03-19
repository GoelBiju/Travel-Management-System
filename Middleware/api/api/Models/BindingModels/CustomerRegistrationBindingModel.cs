using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace api.Models.BindingModels
{
    public class CustomerRegistrationBindingModel
    {
        [Required]
        public string emailAddress { get; set; }

        [Required]
        public string customerPassword { get; set; }

        [Required]
        public string firstName { get; set; }

        [Required]
        public string lastName { get; set; }

        [Required]
        public DateTime dateOfBirth { get; set; }

        [Required]
        public string addressLineOne { get; set; }

        public string addressLineTwo { get; set; }

        [Required]
        public string postCode { get; set; }

        [Required]
        public string mobileNumber { get; set; }
    }
}