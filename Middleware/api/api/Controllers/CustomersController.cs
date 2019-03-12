using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using api.Models;
using api.Models.DTO;

namespace api.Controllers
{
    // Add RoutePrefix to specify the location of this resource.
    [RoutePrefix("api/customers")]
    public class CustomersController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Customers
        //[HttpGet]
        [Route("")]
        public IQueryable<CUSTOMER> GetCUSTOMERS()
        {
            return db.CUSTOMERS;
        }

        // GET: api/Customers/5
        [Route("{id:int}")]
        [ResponseType(typeof(CUSTOMER))]
        public IHttpActionResult GetCUSTOMER(decimal id)
        {
            CUSTOMER cUSTOMER = db.CUSTOMERS.Find(id);
            if (cUSTOMER == null)
            {
                return NotFound();
            }

            return Ok(cUSTOMER);
        }


        // PUT: api/Customers/
        [Route("{id:int}")]
        [ResponseType(typeof(void))]
        public IHttpActionResult PutCUSTOMER(int id, CUSTOMER cUSTOMER)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != cUSTOMER.CUSTOMER_ID)
            {
                return BadRequest();
            }

            db.Entry(cUSTOMER).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!CustomerExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }


        // POST: api/Customers
        // Used to CREATE a new customer and add them to our database.
        [Route("")]
        public IHttpActionResult PostCUSTOMER([FromBody] CUSTOMER cUSTOMER)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            // Check if the customer already exists under the email address provided, 
            // if so return a Conflict HTTP response code.
            if (CustomerExists(cUSTOMER.EMAIL))
            {
                return Conflict();
            } 

            // Add the customer object to our database as a record.
            db.CUSTOMERS.Add(cUSTOMER);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                // A DbUpdateException will occur if the data does not match what the database expects
                // i.e. constraints are violated, we can send back a bad request HTTP response.
                //if (CUSTOMERExists(cUSTOMER.CUSTOMER_ID))
                return BadRequest();
            }

            // Find the added user by the email address provided by the client.
            CUSTOMER addedCustomer = db.CUSTOMERS.SingleOrDefault(customer => customer.EMAIL == cUSTOMER.EMAIL);
            if (addedCustomer == null)
            {
                //throw new HttpResponseException(Request.CreateResponse(HttpStatusCode.NotFound));
                return NotFound();
            }

            // Return the added customer's ID and object.
            return CreatedAtRoute("DefaultApi", new { id = addedCustomer.CUSTOMER_ID }, addedCustomer);
        }


        // POST: api/Customers
        // Used to CREATE a new customer and add them to our database.
        [Route("Login")]
        [ResponseType(typeof(CustomerDTO))]
        public IHttpActionResult PostCUSTOMERLogin([FromBody] CustomerLoginDetails loginDetails)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (string.IsNullOrEmpty(loginDetails.emailAddress) || string.IsNullOrEmpty(loginDetails.password))
            {
                // Return 403 Forbidden with error message.
                HttpError err = new HttpError("Ensure that your email address and password are provided.");
                HttpResponseMessage responseMessage = Request.CreateErrorResponse(HttpStatusCode.Forbidden, err);

                return ResponseMessage(responseMessage);
            }

            // Ensure there is a user under this email address.
            if (CustomerExists(loginDetails.emailAddress))
            {
                // Get the customer record from the database that matches the email address provided.
                CUSTOMER dbCustomer = db.CUSTOMERS.SingleOrDefault(customer => customer.EMAIL == loginDetails.emailAddress);

                if (dbCustomer != null && loginDetails.emailAddress.Equals(dbCustomer.EMAIL))
                {
                    // TODO: Get the password hash and salt and ensure that the attempted password matches.
                    //       Implement SHA256/SHA512 hasing with a password and salt which is stored in the database.

                    // TODO: Temporarily just checking that the passwords match for now; this needs to be replaced with 
                    //       hasing/security functionality.
                    if (loginDetails.password.Equals(dbCustomer.PASSWORD))
                    {
                        // Return the user details as with the Customer Data Transfer Object.

                        return Ok(dbCustomer);

                    } else
                    {
                        // Return a 403 Forbidden with an error message.
                        HttpError err = new HttpError("Ensure that the email address and password are correct for the account you are trying to access.");
                        HttpResponseMessage responseMessage = Request.CreateErrorResponse(HttpStatusCode.Forbidden, err);

                        return ResponseMessage(responseMessage);
                    }
                } else
                {
                    // In the event that we cannot fetch the customer record based on the provided email address.
                    HttpError err = new HttpError("There was an error processing the login details you have provided on our side.");
                    HttpResponseMessage responseMessage = Request.CreateErrorResponse(HttpStatusCode.NotFound, err);

                    return ResponseMessage(responseMessage);
                }

            } else
            {
                // The email address the user requested is not registered to an account.
                HttpError err = new HttpError("This account is not yet registered with our service.");
                HttpResponseMessage responseMessage = Request.CreateErrorResponse(HttpStatusCode.NotFound, err);

                return ResponseMessage(responseMessage);
            }
        }


        // DELETE: api/Customers/5
        [Route("{id:int}")]
        [ResponseType(typeof(CUSTOMER))]
        public IHttpActionResult DeleteCUSTOMER(decimal id)
        {
            CUSTOMER cUSTOMER = db.CUSTOMERS.Find(id);
            if (cUSTOMER == null)
            {
                return NotFound();
            }

            db.CUSTOMERS.Remove(cUSTOMER);
            db.SaveChanges();

            return Ok(cUSTOMER);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }


        /// <summary>
        /// 
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        private bool CustomerExists(decimal id)
        {
            return db.CUSTOMERS.Count(e => e.CUSTOMER_ID == id) > 0;
        }


        /// <summary>
        /// Find if a customer record already exists given an email address.
        /// </summary>
        /// <param name="emailAddress"></param>
        /// <returns></returns>
        private bool CustomerExists(string emailAddress)
        {
            return db.CUSTOMERS.Count(e => e.EMAIL == emailAddress) > 0;
        }
    }
}