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
    public class StationsController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Stations
        public IQueryable<STATION> GetSTATIONS()
        {
            return db.STATIONS;
        }

        // GET: api/Stations/5
        [ResponseType(typeof(STATION))]
        public IHttpActionResult GetSTATION(decimal id)
        {
            STATION sTATION = db.STATIONS.Find(id);
            if (sTATION == null)
            {
                return NotFound();
            }

            return Ok(sTATION);
        }

        // PUT: api/Stations/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutSTATION(decimal id, STATION sTATION)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != sTATION.STATION_ID)
            {
                return BadRequest();
            }

            db.Entry(sTATION).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!STATIONExists(id))
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

        // POST: api/Stations
        [ResponseType(typeof(STATION))]
        public IHttpActionResult PostSTATION(STATION sTATION)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.STATIONS.Add(sTATION);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (STATIONExists(sTATION.STATION_ID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = sTATION.STATION_ID }, sTATION);
        }

        // DELETE: api/Stations/5
        [ResponseType(typeof(STATION))]
        public IHttpActionResult DeleteSTATION(decimal id)
        {
            STATION sTATION = db.STATIONS.Find(id);
            if (sTATION == null)
            {
                return NotFound();
            }

            db.STATIONS.Remove(sTATION);
            db.SaveChanges();

            return Ok(sTATION);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool STATIONExists(decimal id)
        {
            return db.STATIONS.Count(e => e.STATION_ID == id) > 0;
        }
    }
}