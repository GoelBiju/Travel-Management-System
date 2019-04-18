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
using api.Models.DTO;

namespace api.Controllers
{
    [Authorize]
    [RoutePrefix("api/archive/coaches")]
    public class CoachesArchiveController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/CoachesArchive
        /// <summary>
        /// 
        /// </summary>
        /// <returns></returns>
        [HttpGet, Authorize(Roles = "Employee")]
        [Route("")]
        [ResponseType(typeof(CoachDTO))]
        public IQueryable<CoachDTO> GetCOACHES_ARCHIVE()
        {
            var archivedCoaches = from c in db.COACHES_ARCHIVE
                                  select new CoachDTO()
                                  {
                                      CoachId = (int)c.COACH_ID,
                                      CoachMake = c.COACH_MAKE,
                                      CoachModel = c.COACH_MODEL,
                                      RegistratonPlate = c.REGISTRATION_PLATE,
                                      CoachCapacity = (int)c.COACH_CAPACITY
                                  };

            return archivedCoaches;
        }

        // GET: api/CoachesArchive/5
        //[ResponseType(typeof(COACHES_ARCHIVE))]
        //public IHttpActionResult GetCOACHES_ARCHIVE(decimal id)
        //{
        //    COACHES_ARCHIVE cOACHES_ARCHIVE = db.COACHES_ARCHIVE.Find(id);
        //    if (cOACHES_ARCHIVE == null)
        //    {
        //        return NotFound();
        //    }

        //    return Ok(cOACHES_ARCHIVE);
        //}

        // PUT: api/CoachesArchive/5
        //[ResponseType(typeof(void))]
        //public IHttpActionResult PutCOACHES_ARCHIVE(decimal id, COACHES_ARCHIVE cOACHES_ARCHIVE)
        //{
        //    if (!ModelState.IsValid)
        //    {
        //        return BadRequest(ModelState);
        //    }

        //    if (id != cOACHES_ARCHIVE.COACH_ID)
        //    {
        //        return BadRequest();
        //    }

        //    db.Entry(cOACHES_ARCHIVE).State = EntityState.Modified;

        //    try
        //    {
        //        db.SaveChanges();
        //    }
        //    catch (DbUpdateConcurrencyException)
        //    {
        //        if (!COACHES_ARCHIVEExists(id))
        //        {
        //            return NotFound();
        //        }
        //        else
        //        {
        //            throw;
        //        }
        //    }

        //    return StatusCode(HttpStatusCode.NoContent);
        //}

        // POST: api/CoachesArchive
        //[ResponseType(typeof(COACHES_ARCHIVE))]
        //public IHttpActionResult PostCOACHES_ARCHIVE(COACHES_ARCHIVE cOACHES_ARCHIVE)
        //{
        //    if (!ModelState.IsValid)
        //    {
        //        return BadRequest(ModelState);
        //    }

        //    db.COACHES_ARCHIVE.Add(cOACHES_ARCHIVE);

        //    try
        //    {
        //        db.SaveChanges();
        //    }
        //    catch (DbUpdateException)
        //    {
        //        if (COACHES_ARCHIVEExists(cOACHES_ARCHIVE.COACH_ID))
        //        {
        //            return Conflict();
        //        }
        //        else
        //        {
        //            throw;
        //        }
        //    }

        //    return CreatedAtRoute("DefaultApi", new { id = cOACHES_ARCHIVE.COACH_ID }, cOACHES_ARCHIVE);
        //}

        // DELETE: api/CoachesArchive/5
        //[ResponseType(typeof(COACHES_ARCHIVE))]
        //public IHttpActionResult DeleteCOACHES_ARCHIVE(decimal id)
        //{
        //    COACHES_ARCHIVE cOACHES_ARCHIVE = db.COACHES_ARCHIVE.Find(id);
        //    if (cOACHES_ARCHIVE == null)
        //    {
        //        return NotFound();
        //    }

        //    db.COACHES_ARCHIVE.Remove(cOACHES_ARCHIVE);
        //    db.SaveChanges();

        //    return Ok(cOACHES_ARCHIVE);
        //}

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        //private bool COACHES_ARCHIVEExists(decimal id)
        //{
        //    return db.COACHES_ARCHIVE.Count(e => e.COACH_ID == id) > 0;
        //}
    }
}