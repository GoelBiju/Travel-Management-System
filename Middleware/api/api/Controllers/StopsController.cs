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
    public class StopsController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Stops
        public IQueryable<STOP> GetSTOPS()
        {
            return db.STOPS;
        }

        // GET: api/Stops/5
        [ResponseType(typeof(STOP))]
        public IHttpActionResult GetSTOP(decimal id)
        {
            STOP sTOP = db.STOPS.Find(id);
            if (sTOP == null)
            {
                return NotFound();
            }

            return Ok(sTOP);
        }

        // PUT: api/Stops/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutSTOP(decimal id, STOP sTOP)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != sTOP.STOP_ID)
            {
                return BadRequest();
            }

            db.Entry(sTOP).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!STOPExists(id))
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

        // POST: api/Stops
        [ResponseType(typeof(STOP))]
        public IHttpActionResult PostSTOP(STOP sTOP)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.STOPS.Add(sTOP);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (STOPExists(sTOP.STOP_ID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = sTOP.STOP_ID }, sTOP);
        }

        // DELETE: api/Stops/5
        [ResponseType(typeof(STOP))]
        public IHttpActionResult DeleteSTOP(decimal id)
        {
            STOP sTOP = db.STOPS.Find(id);
            if (sTOP == null)
            {
                return NotFound();
            }

            db.STOPS.Remove(sTOP);
            db.SaveChanges();

            return Ok(sTOP);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool STOPExists(decimal id)
        {
            return db.STOPS.Count(e => e.STOP_ID == id) > 0;
        }
    }
}