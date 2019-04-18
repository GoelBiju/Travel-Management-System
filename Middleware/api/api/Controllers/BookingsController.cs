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
    public class BookingsController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Bookings
        public IQueryable<BOOKING> GetBOOKINGS()
        {
            return db.BOOKINGS;
        }

        // GET: api/Bookings/5
        [ResponseType(typeof(BOOKING))]
        public IHttpActionResult GetBOOKING(decimal id)
        {
            BOOKING bOOKING = db.BOOKINGS.Find(id);
            if (bOOKING == null)
            {
                return NotFound();
            }

            return Ok(bOOKING);
        }

        // PUT: api/Bookings/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutBOOKING(decimal id, BOOKING bOOKING)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != bOOKING.BOOKING_REFERENCE)
            {
                return BadRequest();
            }

            db.Entry(bOOKING).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!BOOKINGExists(id))
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

        // POST: api/Bookings
        [ResponseType(typeof(BOOKING))]
        public IHttpActionResult PostBOOKING(BOOKING bOOKING)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.BOOKINGS.Add(bOOKING);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (BOOKINGExists(bOOKING.BOOKING_REFERENCE))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = bOOKING.BOOKING_REFERENCE }, bOOKING);
        }

        // DELETE: api/Bookings/5
        [ResponseType(typeof(BOOKING))]
        public IHttpActionResult DeleteBOOKING(decimal id)
        {
            BOOKING bOOKING = db.BOOKINGS.Find(id);
            if (bOOKING == null)
            {
                return NotFound();
            }

            db.BOOKINGS.Remove(bOOKING);
            db.SaveChanges();

            return Ok(bOOKING);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool BOOKINGExists(decimal id)
        {
            return db.BOOKINGS.Count(e => e.BOOKING_REFERENCE == id) > 0;
        }
    }
}