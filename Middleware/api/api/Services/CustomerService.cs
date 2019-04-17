using api.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace api.Services
{
    public class CustomerService
    {
        private Entities db = new Entities();

        public CUSTOMER ValidateCustomer(string emailAddress, string password)
        {
            // Get the first entry in the database for the mathcing email and password.
            CUSTOMER customer = db.CUSTOMERS.FirstOrDefault(c => c.EMAIL_ADDRESS == emailAddress && c.CUSTOMER_PASSWORD == password);

            return customer;
        }
    }
}