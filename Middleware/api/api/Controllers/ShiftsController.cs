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
    public class ShiftsController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Shifts
        public IQueryable<SHIFT> GetSHIFTS()
        {
            return db.SHIFTS;
        }

        // GET: api/Shifts/5
        [ResponseType(typeof(SHIFT))]
        public IHttpActionResult GetSHIFT(decimal id)
        {
            SHIFT sHIFT = db.SHIFTS.Find(id);
            if (sHIFT == null)
            {
                return NotFound();
            }

            return Ok(sHIFT);
        }

        // PUT: api/Shifts/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutSHIFT(decimal id, SHIFT sHIFT)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != sHIFT.SHIFT_ID)
            {
                return BadRequest();
            }

            db.Entry(sHIFT).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!SHIFTExists(id))
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

        // POST: api/Shifts
        [ResponseType(typeof(SHIFT))]
        public IHttpActionResult PostSHIFT(SHIFT sHIFT)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.SHIFTS.Add(sHIFT);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (SHIFTExists(sHIFT.SHIFT_ID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = sHIFT.SHIFT_ID }, sHIFT);
        }

        // DELETE: api/Shifts/5
        [ResponseType(typeof(SHIFT))]
        public IHttpActionResult DeleteSHIFT(decimal id)
        {
            SHIFT sHIFT = db.SHIFTS.Find(id);
            if (sHIFT == null)
            {
                return NotFound();
            }

            db.SHIFTS.Remove(sHIFT);
            db.SaveChanges();

            return Ok(sHIFT);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool SHIFTExists(decimal id)
        {
            return db.SHIFTS.Count(e => e.SHIFT_ID == id) > 0;
        }
    }
}