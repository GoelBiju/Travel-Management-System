using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.Http.Description;
using api.Models;

namespace api.Controllers
{
    public class RoutesArchiveController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/RoutesArchive
        public IQueryable<ROUTES_ARCHIVE> GetROUTES_ARCHIVE()
        {
            return db.ROUTES_ARCHIVE;
        }

        // GET: api/RoutesArchive/5
        [ResponseType(typeof(ROUTES_ARCHIVE))]
        public async Task<IHttpActionResult> GetROUTES_ARCHIVE(decimal id)
        {
            ROUTES_ARCHIVE rOUTES_ARCHIVE = await db.ROUTES_ARCHIVE.FindAsync(id);
            if (rOUTES_ARCHIVE == null)
            {
                return NotFound();
            }

            return Ok(rOUTES_ARCHIVE);
        }

        // PUT: api/RoutesArchive/5
        [ResponseType(typeof(void))]
        public async Task<IHttpActionResult> PutROUTES_ARCHIVE(decimal id, ROUTES_ARCHIVE rOUTES_ARCHIVE)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != rOUTES_ARCHIVE.ROUTE_ID)
            {
                return BadRequest();
            }

            db.Entry(rOUTES_ARCHIVE).State = EntityState.Modified;

            try
            {
                await db.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ROUTES_ARCHIVEExists(id))
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

        // POST: api/RoutesArchive
        [ResponseType(typeof(ROUTES_ARCHIVE))]
        public async Task<IHttpActionResult> PostROUTES_ARCHIVE(ROUTES_ARCHIVE rOUTES_ARCHIVE)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.ROUTES_ARCHIVE.Add(rOUTES_ARCHIVE);

            try
            {
                await db.SaveChangesAsync();
            }
            catch (DbUpdateException)
            {
                if (ROUTES_ARCHIVEExists(rOUTES_ARCHIVE.ROUTE_ID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = rOUTES_ARCHIVE.ROUTE_ID }, rOUTES_ARCHIVE);
        }

        // DELETE: api/RoutesArchive/5
        [ResponseType(typeof(ROUTES_ARCHIVE))]
        public async Task<IHttpActionResult> DeleteROUTES_ARCHIVE(decimal id)
        {
            ROUTES_ARCHIVE rOUTES_ARCHIVE = await db.ROUTES_ARCHIVE.FindAsync(id);
            if (rOUTES_ARCHIVE == null)
            {
                return NotFound();
            }

            db.ROUTES_ARCHIVE.Remove(rOUTES_ARCHIVE);
            await db.SaveChangesAsync();

            return Ok(rOUTES_ARCHIVE);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool ROUTES_ARCHIVEExists(decimal id)
        {
            return db.ROUTES_ARCHIVE.Count(e => e.ROUTE_ID == id) > 0;
        }
    }
}