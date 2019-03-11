using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebsiteApplication.Models
{
    public class employee
    {
        private string id { get; set; }
        public string firstName { get; set; }
        private string lastName { get; set; }
        private string jobRole { get; set; }
        private string password { get; set; }

        public string GetFirstName()
        {
            return firstName;
        }
    }
}