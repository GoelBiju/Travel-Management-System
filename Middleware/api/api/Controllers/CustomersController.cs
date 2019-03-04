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

        // PUT: api/Customers/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutCUSTOMER(decimal id, CUSTOMER cUSTOMER)
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
        [ResponseType(typeof(CUSTOMER))]
        public IHttpActionResult PostCUSTOMER(CUSTOMER cUSTOMER)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.CUSTOMERS.Add(cUSTOMER);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (CUSTOMERExists(cUSTOMER.CUSTOMER_ID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = cUSTOMER.CUSTOMER_ID }, cUSTOMER);
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

        private bool CUSTOMERExists(decimal id)
        {
            return db.CUSTOMERS.Count(e => e.CUSTOMER_ID == id) > 0;
        }
    }
}