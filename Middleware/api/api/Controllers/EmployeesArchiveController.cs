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
    public class EmployeesArchiveController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/EmployeesArchive
        public IQueryable<EMPLOYEES_ARCHIVE> GetEMPLOYEES_ARCHIVE()
        {
            return db.EMPLOYEES_ARCHIVE;
        }

        // GET: api/EmployeesArchive/5
        [ResponseType(typeof(EMPLOYEES_ARCHIVE))]
        public async Task<IHttpActionResult> GetEMPLOYEES_ARCHIVE(string id)
        {
            EMPLOYEES_ARCHIVE eMPLOYEES_ARCHIVE = await db.EMPLOYEES_ARCHIVE.FindAsync(id);
            if (eMPLOYEES_ARCHIVE == null)
            {
                return NotFound();
            }

            return Ok(eMPLOYEES_ARCHIVE);
        }

        // PUT: api/EmployeesArchive/5
        [ResponseType(typeof(void))]
        public async Task<IHttpActionResult> PutEMPLOYEES_ARCHIVE(string id, EMPLOYEES_ARCHIVE eMPLOYEES_ARCHIVE)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != eMPLOYEES_ARCHIVE.EMPLOYEE_ID)
            {
                return BadRequest();
            }

            db.Entry(eMPLOYEES_ARCHIVE).State = EntityState.Modified;

            try
            {
                await db.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!EMPLOYEES_ARCHIVEExists(id))
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

        // POST: api/EmployeesArchive
        [ResponseType(typeof(EMPLOYEES_ARCHIVE))]
        public async Task<IHttpActionResult> PostEMPLOYEES_ARCHIVE(EMPLOYEES_ARCHIVE eMPLOYEES_ARCHIVE)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.EMPLOYEES_ARCHIVE.Add(eMPLOYEES_ARCHIVE);

            try
            {
                await db.SaveChangesAsync();
            }
            catch (DbUpdateException)
            {
                if (EMPLOYEES_ARCHIVEExists(eMPLOYEES_ARCHIVE.EMPLOYEE_ID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = eMPLOYEES_ARCHIVE.EMPLOYEE_ID }, eMPLOYEES_ARCHIVE);
        }

        // DELETE: api/EmployeesArchive/5
        [ResponseType(typeof(EMPLOYEES_ARCHIVE))]
        public async Task<IHttpActionResult> DeleteEMPLOYEES_ARCHIVE(string id)
        {
            EMPLOYEES_ARCHIVE eMPLOYEES_ARCHIVE = await db.EMPLOYEES_ARCHIVE.FindAsync(id);
            if (eMPLOYEES_ARCHIVE == null)
            {
                return NotFound();
            }

            db.EMPLOYEES_ARCHIVE.Remove(eMPLOYEES_ARCHIVE);
            await db.SaveChangesAsync();

            return Ok(eMPLOYEES_ARCHIVE);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool EMPLOYEES_ARCHIVEExists(string id)
        {
            return db.EMPLOYEES_ARCHIVE.Count(e => e.EMPLOYEE_ID == id) > 0;
        }
    }
}