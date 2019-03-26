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
using api.Models.DTO;

namespace api.Controllers
{
    [RoutePrefix("api/shifts")]
    public class ShiftsController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Shifts
        [HttpGet]
        [Route("")]
        public IQueryable<ShiftDTO> GetSHIFTS()
        {
            var shifts = from s in db.SHIFTS
                         select new ShiftDTO()
                         {
                             ShiftId = (int)s.SHIFT_ID,
                             EmployeeId = s.EMPLOYEE_ID,
                             RouteId = s.ROUTE_ID,
                             CoachId = (int)s.COACH_ID
                         };

            return shifts;
        }

        // GET: api/Shifts/5
        [HttpGet]
        [Route("{id:int}", Name = "GetShiftDetailsById")]
        [ResponseType(typeof(ShiftDTO))]
        public async Task<IHttpActionResult> GetSHIFT(decimal id)
        {
            // Select a specific shift based on its id attribute.
            //SHIFT sHIFT = db.SHIFTS.Find(id);

            var shift = await db.SHIFTS.Select(s =>
                new ShiftDTO()
                {
                    ShiftId = (int)s.SHIFT_ID,
                    EmployeeId = s.EMPLOYEE_ID,
                    RouteId = s.ROUTE_ID,
                    CoachId = (int)s.COACH_ID
                }).SingleOrDefaultAsync(s => s.ShiftId == id);


            if (shift == null)
            {
                return NotFound();
            }

            return Ok(shift);
        }

        // GET: Shifts by employee id.
        // Return shifts only on the day that the service supposed to run.
        [HttpGet]
        [Route("employee/{employeeId}", Name = "GetShiftsByEmployeeId")]
        [ResponseType(typeof(ShiftDTO))]
        public IHttpActionResult GetEmployeeShifts(string employeeId)
        {
            IList<ShiftDTO> employeeShifts = db.SHIFTS.Select(s =>
                new ShiftDTO()
                {
                    ShiftId = (int)s.SHIFT_ID,
                    EmployeeId = s.EMPLOYEE_ID,
                    RouteId = s.ROUTE_ID,
                    CoachId = (int)s.COACH_ID
                }).Where(s => s.EmployeeId == employeeId).ToList();

            if (employeeShifts.Count == 0)
            {
                return NotFound();
            }

            return Ok(employeeShifts);
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