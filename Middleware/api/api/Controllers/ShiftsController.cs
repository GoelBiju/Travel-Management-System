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
using api.Models.BindingModels;
using api.Models.DTO;

namespace api.Controllers
{
    [Authorize(Roles = "Employee")]
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
                             StartDateTime = s.START_DATETIME,
                             EndDateTime = s.END_DATETIME
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
                    StartDateTime = s.START_DATETIME,
                    EndDateTime = s.END_DATETIME
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
                    StartDateTime = s.START_DATETIME,
                    EndDateTime = s.END_DATETIME
                }).Where(s => s.EmployeeId == employeeId).ToList();

            if (employeeShifts.Count == 0)
            {
                return NotFound();
            }

            return Ok(employeeShifts);
        }

        //// PUT: api/Shifts/5
        //[ResponseType(typeof(void))]
        //public IHttpActionResult PutSHIFT(decimal id, SHIFT sHIFT)
        //{
        //    if (!ModelState.IsValid)
        //    {
        //        return BadRequest(ModelState);
        //    }

        //    if (id != sHIFT.SHIFT_ID)
        //    {
        //        return BadRequest();
        //    }

        //    db.Entry(sHIFT).State = EntityState.Modified;

        //    try
        //    {
        //        db.SaveChanges();
        //    }
        //    catch (DbUpdateConcurrencyException)
        //    {
        //        if (!SHIFTExists(id))
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

        // POST: api/Shifts
        [HttpPost]
        [Route("")]
        [ResponseType(typeof(void))]
        public IHttpActionResult PostSHIFT([FromBody] ShiftCreationBindingModel shift)
        {
            SHIFT addShift = new SHIFT()
            {
                SHIFT_ID = 0,
                EMPLOYEE_ID = shift.EmployeeId,
                START_DATETIME = shift.StartDateTime,
                END_DATETIME = shift.EndDateTime
            };

            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.SHIFTS.Add(addShift);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                //if (SHIFTExists(sHIFT.SHIFT_ID))
                //{
                //    return Conflict();
                //}
                //else
                //{
                //    throw;
                //}

                return BadRequest();
            }

            //return CreatedAtRoute("DefaultApi", new { id = sHIFT.SHIFT_ID }, sHIFT);
            HttpResponseMessage responseMessage = Request.CreateResponse(HttpStatusCode.Created, shift);
            return ResponseMessage(responseMessage);
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