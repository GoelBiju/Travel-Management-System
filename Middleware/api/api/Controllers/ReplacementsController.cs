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
    public class ReplacementsController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Replacements
        public IQueryable<REPLACEMENT> GetREPLACEMENTS()
        {
            return db.REPLACEMENTS;
        }

        // GET: api/Replacements/5
        [ResponseType(typeof(REPLACEMENT))]
        public IHttpActionResult GetREPLACEMENT(decimal id)
        {
            REPLACEMENT rEPLACEMENT = db.REPLACEMENTS.Find(id);
            if (rEPLACEMENT == null)
            {
                return NotFound();
            }

            return Ok(rEPLACEMENT);
        }

        // PUT: api/Replacements/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutREPLACEMENT(decimal id, REPLACEMENT rEPLACEMENT)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != rEPLACEMENT.REPLACEMENT_ID)
            {
                return BadRequest();
            }

            db.Entry(rEPLACEMENT).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!REPLACEMENTExists(id))
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

        // POST: api/Replacements
        [ResponseType(typeof(REPLACEMENT))]
        public IHttpActionResult PostREPLACEMENT(REPLACEMENT rEPLACEMENT)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.REPLACEMENTS.Add(rEPLACEMENT);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (REPLACEMENTExists(rEPLACEMENT.REPLACEMENT_ID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = rEPLACEMENT.REPLACEMENT_ID }, rEPLACEMENT);
        }

        // DELETE: api/Replacements/5
        [ResponseType(typeof(REPLACEMENT))]
        public IHttpActionResult DeleteREPLACEMENT(decimal id)
        {
            REPLACEMENT rEPLACEMENT = db.REPLACEMENTS.Find(id);
            if (rEPLACEMENT == null)
            {
                return NotFound();
            }

            db.REPLACEMENTS.Remove(rEPLACEMENT);
            db.SaveChanges();

            return Ok(rEPLACEMENT);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool REPLACEMENTExists(decimal id)
        {
            return db.REPLACEMENTS.Count(e => e.REPLACEMENT_ID == id) > 0;
        }
    }
}