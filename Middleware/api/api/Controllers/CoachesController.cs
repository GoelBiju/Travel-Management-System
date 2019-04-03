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
    [RoutePrefix("api/coaches")]
    public class CoachesController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Coaches
        [HttpGet]
        [Route("")]
        [ResponseType(typeof(CoachDTO))]
        public IQueryable<CoachDTO> GetCOACHES()
        {
            var coaches = from c in db.COACHES
                          select new CoachDTO()
                          {
                              CoachId = (int)c.COACH_ID,
                              CoachMake = c.COACH_MAKE,
                              CoachModel = c.COACH_MODEL,
                              RegistratonPlate = c.REGISTRATION_PLATE,
                              CoachCapacity = (int)c.COACH_CAPACITY,
                              IsActive = c.IS_ACTIVE
                          };

            return coaches;
        }

        // GET: api/Coaches/5
        [HttpGet]
        [Route("{id:int}", Name = "GetCoachesDetailsById")]
        [ResponseType(typeof(CoachDTO))]
        public async Task<IHttpActionResult> GetCOACH(decimal id)
        {
            //COACH cOACH = db.COACHES.Find(id);

            var coach = await db.COACHES.Select(c =>
                new CoachDTO()
                {
                    CoachId = (int)c.COACH_ID,
                    CoachMake = c.COACH_MAKE,
                    CoachModel = c.COACH_MODEL,
                    RegistratonPlate = c.REGISTRATION_PLATE,
                    CoachCapacity = (int)c.COACH_CAPACITY,
                    IsActive = c.IS_ACTIVE
                }).SingleOrDefaultAsync(c => c.CoachId == id);

            
            if (coach == null)
            {
                return NotFound();
            }

            return Ok(coach);
        }

        // PUT: api/Coaches/
        [HttpPut]
        [Route("")]
        [ResponseType(typeof(void))]
        public IHttpActionResult PutCOACH([FromBody] CoachDTO coach)
        {
            //COACH updatedCoach = new COACH()
            //{
            //    COACH_ID = id,
            //    COACH_CAPACITY = coach.CoachCapacity,
            //    COACH_MAKE = coach.CoachMake,
            //    COACH_MODEL = coach.CoachModel,
            //    IS_ACTIVE = coach.IsActive,
            //    REGISTRATION_PLATE = coach.RegistratonPlate
            //};

            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            //db.Entry(updatedCoach).State = EntityState.Modified;

            var coachRecord = db.COACHES.FirstOrDefault(c => c.COACH_ID == coach.CoachId);

            coachRecord.COACH_MAKE = coach.CoachMake;
            coachRecord.COACH_MODEL = coach.CoachModel;
            coachRecord.COACH_CAPACITY = coach.CoachCapacity;
            coachRecord.REGISTRATION_PLATE = coach.RegistratonPlate;
            coachRecord.IS_ACTIVE = coach.IsActive;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!COACHExists(coach.RegistratonPlate))
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
        [HttpPost]
        [Route("")]
        public IHttpActionResult PostCOACH(CoachBindingModel coach)
        {
            COACH cOACH = new COACH()
            {
                COACH_ID = 0,
                COACH_MAKE = coach.CoachMake,
                COACH_MODEL = coach.CoachModel,
                REGISTRATION_PLATE = coach.RegistratonPlate,
                COACH_CAPACITY = coach.CoachCapacity,
                IS_ACTIVE = coach.IsActive
            };

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
                if (COACHExists(cOACH.REGISTRATION_PLATE))
                {
                    return Conflict();
                }   
                else
                {
                    throw;
                }
            }

            HttpResponseMessage responseMessage = Request.CreateResponse(HttpStatusCode.Created, coach);
            return ResponseMessage(responseMessage);
        }

        // DELETE: api/Coaches/5
        [HttpDelete]
        [Route("{id:int}", Name = "DeleteCoachById")]
        [ResponseType(typeof(void))]
        public IHttpActionResult DeleteCOACH(int id)
        {
            COACH coach = db.COACHES.Find(id);
            if (coach == null)
            {
                return NotFound();
            }

            // Remove the coach from the coaches table.
            db.COACHES.Remove(coach);
            db.SaveChanges();

            // TODO: Add the record into the COACHES_ARCHIVE.

            return Ok(coach);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool COACHExists(string reg)
        {
            return db.COACHES.Count(e => e.REGISTRATION_PLATE == reg) > 0;
        }
    }
}