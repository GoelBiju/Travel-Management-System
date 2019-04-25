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
    // Allow access to only employees.
    [Authorize(Roles = "Employee")]
    [RoutePrefix("api/coaches")]
    public class CoachesController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Coaches
        /// <summary>
        /// 
        /// </summary>
        /// <returns></returns>
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
                              IsAvailable = c.IS_AVAILABLE
                          };

            return coaches;
        }


        // GET: api/Coaches/5
        /// <summary>
        /// 
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
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
                    IsAvailable = c.IS_AVAILABLE
                }).SingleOrDefaultAsync(c => c.CoachId == id);

            
            if (coach == null)
            {
                return NotFound();
            }

            return Ok(coach);
        }


        // PUT: api/Coaches/1
        /// <summary>
        /// 
        /// </summary>
        /// <param name="id"></param>
        /// <param name="coach"></param>
        /// <returns></returns>
        [HttpPut]
        [Route("{id:int}")]
        [ResponseType(typeof(void))]
        public IHttpActionResult PutCOACH(int id, [FromBody] CoachDTO coach)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            //db.Entry(updatedCoach).State = EntityState.Modified;

            if (coach.CoachId > 0)
            {
                // Get the current record with the matching coach id and update it's details.
                var coachRecord = db.COACHES.FirstOrDefault(c => c.COACH_ID == coach.CoachId);

                coachRecord.COACH_MAKE = coach.CoachMake;
                coachRecord.COACH_MODEL = coach.CoachModel;
                coachRecord.COACH_CAPACITY = coach.CoachCapacity;
                coachRecord.REGISTRATION_PLATE = coach.RegistratonPlate;
                coachRecord.IS_AVAILABLE = coach.IsAvailable;
            } else
            {
                return BadRequest();
            }

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

            // Successfully updated and there is no content returned.
            return StatusCode(HttpStatusCode.NoContent);
        }


        // POST: api/Coaches
        /// <summary>
        /// 
        /// </summary>
        /// <param name="coach"></param>
        /// <returns></returns>
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
                IS_AVAILABLE = coach.IsAvailable
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
        /// <summary>
        /// 
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
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
            // The coach is added to the coaches archive by the database.
            db.SaveChanges();

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

        /// <summary>
        /// 
        /// </summary>
        /// <param name="reg"></param>
        /// <returns></returns>
        private bool COACHExists(string reg)
        {
            return db.COACHES.Count(e => e.REGISTRATION_PLATE == reg) > 0;
        }
    }
}