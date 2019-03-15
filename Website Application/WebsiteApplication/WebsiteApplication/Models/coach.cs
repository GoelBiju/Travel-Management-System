using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebsiteApplication.Models
{
    public class Coach
    {
        public int coachID { get; set; }

        public string coachMake { get; set; }

        public string coachModel { get; set; }

        public string registrationPlate { get; set; }

        public int capacity { get; set; }

        public string status { get; set; }


    }
}