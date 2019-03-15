using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebsiteApplication.Models
{
    public class employee
    {
        public string id { get; set; }
        public string firstName { get; set; }
        public string lastName { get; set; }
        public string jobRole { get; set; }
        public string password { get; set; }

        public void Employee()
        {
            id = "1";
            firstName = "Default";
            lastName = "Name";
            jobRole = "Job";
            password = "password1";

        }

        public string GetFirstName()
        {
            return firstName;
        }
    }
}