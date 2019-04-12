using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WebApplication.ViewModels
{
    public class RoutesModel
    {
        [Display(Name = "route id")]
        public int routeId { get; set; }

        [Display(Name = "departure station")]
        public int departureStation { get; set; }

        [Display(Name = "arrival station")]
        public int arrivalStation { get; set; }

    }
}