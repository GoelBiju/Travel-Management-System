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
    public class EmployeesController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Employees
        public IQueryable<EMPLOYEE> GetEMPLOYEES()
        {
            return db.EMPLOYEES;
        }

        // GET: api/Employees/5
        [ResponseType(typeof(EMPLOYEE))]
        public IHttpActionResult GetEMPLOYEE(string id)
        {
            EMPLOYEE eMPLOYEE = db.EMPLOYEES.Find(id);
            if (eMPLOYEE == null)
            {
                return NotFound();
            }

            return Ok(eMPLOYEE);
        }

        // PUT: api/Employees/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutEMPLOYEE(string id, EMPLOYEE eMPLOYEE)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != eMPLOYEE.EMPLOYEE_ID)
            {
                return BadRequest();
            }

            db.Entry(eMPLOYEE).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!EMPLOYEEExists(id))
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

        // POST: api/Employees
        [ResponseType(typeof(EMPLOYEE))]
        public IHttpActionResult PostEMPLOYEE(EMPLOYEE eMPLOYEE)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.EMPLOYEES.Add(eMPLOYEE);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (EMPLOYEEExists(eMPLOYEE.EMPLOYEE_ID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = eMPLOYEE.EMPLOYEE_ID }, eMPLOYEE);
        }

        // DELETE: api/Employees/5
        [ResponseType(typeof(EMPLOYEE))]
        public IHttpActionResult DeleteEMPLOYEE(string id)
        {
            EMPLOYEE eMPLOYEE = db.EMPLOYEES.Find(id);
            if (eMPLOYEE == null)
            {
                return NotFound();
            }

            db.EMPLOYEES.Remove(eMPLOYEE);
            db.SaveChanges();

            return Ok(eMPLOYEE);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool EMPLOYEEExists(string id)
        {
            return db.EMPLOYEES.Count(e => e.EMPLOYEE_ID == id) > 0;
        }
    }
}