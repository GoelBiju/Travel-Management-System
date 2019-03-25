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
    public class RoutesController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Routes
        public IQueryable<ROUTE> GetROUTES()
        {
            return db.ROUTES;
        }

        // GET: api/Routes/5
        [ResponseType(typeof(ROUTE))]
        public IHttpActionResult GetROUTE(string id)
        {
            ROUTE rOUTE = db.ROUTES.Find(id);   
            if (rOUTE == null)
            {
                return NotFound();
            }

            return Ok(rOUTE);
        }

        // PUT: api/Routes/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutROUTE(decimal id, ROUTE rOUTE)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != rOUTE.ROUTE_ID)
            {
                return BadRequest();
            }

            db.Entry(rOUTE).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ROUTEExists(id))
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

        // POST: api/Routes
        [ResponseType(typeof(ROUTE))]
        public IHttpActionResult PostROUTE(ROUTE rOUTE)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.ROUTES.Add(rOUTE);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (ROUTEExists(rOUTE.ROUTE_ID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = rOUTE.ROUTE_ID }, rOUTE);
        }

        // DELETE: api/Routes/5
        [ResponseType(typeof(ROUTE))]
        public IHttpActionResult DeleteROUTE(string id)
        {
            ROUTE rOUTE = db.ROUTES.Find(id);
            if (rOUTE == null)
            {
                return NotFound();
            }

            db.ROUTES.Remove(rOUTE);
            db.SaveChanges();

            return Ok(rOUTE);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool ROUTEExists(decimal id)
        {
            return db.ROUTES.Count(e => e.ROUTE_ID == id) > 0;
        }
    }
}