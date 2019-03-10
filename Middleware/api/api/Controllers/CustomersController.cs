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

namespace api.Controllers
{
    public class CustomersController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Customers
        public IQueryable<CUSTOMER> GetCUSTOMERS()
        {
            
            return db.CUSTOMERS;
        }

        // GET: api/Customers/5
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
                if (!CUSTOMERExists(id))
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
        public IHttpActionResult PostCUSTOMER([FromBody] CUSTOMER cUSTOMER)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            // Check if the customer already exists under the email address provided, 
            // if so return a Conflict HTTP response code.
            if (CUSTOMERExists(cUSTOMER.EMAIL))
            {
                return Conflict();
            } else
            {
                db.CUSTOMERS.Add(cUSTOMER);
            }

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

        // DELETE: api/Customers/5
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
        private bool CUSTOMERExists(decimal id)
        {
            return db.CUSTOMERS.Count(e => e.CUSTOMER_ID == id) > 0;
        }


        /// <summary>
        /// Find if a customer record already exists given an email address.
        /// </summary>
        /// <param name="emailAddress"></param>
        /// <returns></returns>
        private bool CUSTOMERExists(string emailAddress)
        {
            return db.CUSTOMERS.Count(e => e.EMAIL == emailAddress) > 0;
        }
    }
}