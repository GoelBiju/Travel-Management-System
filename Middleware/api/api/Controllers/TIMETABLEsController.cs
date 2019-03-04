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
    public class TimetablesController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Timetables
        public IQueryable<TIMETABLE> GetTIMETABLES()
        {
            return db.TIMETABLES;
        }

        // GET: api/Timetables/5
        [ResponseType(typeof(TIMETABLE))]
        public IHttpActionResult GetTIMETABLE(DateTime id)
        {
            TIMETABLE tIMETABLE = db.TIMETABLES.Find(id);
            if (tIMETABLE == null)
            {
                return NotFound();
            }

            return Ok(tIMETABLE);
        }

        // PUT: api/Timetables/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutTIMETABLE(DateTime id, TIMETABLE tIMETABLE)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != tIMETABLE.DEPARTURE_TIME)
            {
                return BadRequest();
            }

            db.Entry(tIMETABLE).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!TIMETABLEExists(id))
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

        // POST: api/Timetables
        [ResponseType(typeof(TIMETABLE))]
        public IHttpActionResult PostTIMETABLE(TIMETABLE tIMETABLE)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.TIMETABLES.Add(tIMETABLE);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (TIMETABLEExists(tIMETABLE.DEPARTURE_TIME))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = tIMETABLE.DEPARTURE_TIME }, tIMETABLE);
        }

        // DELETE: api/Timetables/5
        [ResponseType(typeof(TIMETABLE))]
        public IHttpActionResult DeleteTIMETABLE(DateTime id)
        {
            TIMETABLE tIMETABLE = db.TIMETABLES.Find(id);
            if (tIMETABLE == null)
            {
                return NotFound();
            }

            db.TIMETABLES.Remove(tIMETABLE);
            db.SaveChanges();

            return Ok(tIMETABLE);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool TIMETABLEExists(DateTime id)
        {
            return db.TIMETABLES.Count(e => e.DEPARTURE_TIME == id) > 0;
        }
    }
}