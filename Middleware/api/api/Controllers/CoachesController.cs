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
    public class CoachesController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Coaches
        public IQueryable<COACH> GetCOACHES()
        {
            return db.COACHES;
        }

        // GET: api/Coaches/5
        [ResponseType(typeof(COACH))]
        public IHttpActionResult GetCOACH(decimal id)
        {
            COACH cOACH = db.COACHES.Find(id);
            if (cOACH == null)
            {
                return NotFound();
            }

            return Ok(cOACH);
        }

        // PUT: api/Coaches/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutCOACH(decimal id, COACH cOACH)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != cOACH.COACH_ID)
            {
                return BadRequest();
            }

            db.Entry(cOACH).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!COACHExists(id))
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

        // POST: api/Coaches
        [ResponseType(typeof(COACH))]
        public IHttpActionResult PostCOACH(COACH cOACH)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.COACHES.Add(cOACH);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (COACHExists(cOACH.COACH_ID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = cOACH.COACH_ID }, cOACH);
        }

        // DELETE: api/Coaches/5
        [ResponseType(typeof(COACH))]
        public IHttpActionResult DeleteCOACH(decimal id)
        {
            COACH cOACH = db.COACHES.Find(id);
            if (cOACH == null)
            {
                return NotFound();
            }

            db.COACHES.Remove(cOACH);
            db.SaveChanges();

            return Ok(cOACH);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool COACHExists(decimal id)
        {
            return db.COACHES.Count(e => e.COACH_ID == id) > 0;
        }
    }
}