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
using api.Models.BindingModels;
using api.Models.DTO;

namespace api.Controllers
{
    public class JourneysController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Journeys
        public IQueryable<JourneyDTO> GetJOURNEYS()
        {
            var journeys = from j in db.JOURNEYS
                           select new JourneyDTO
                           {
                               JourneyId = (int)j.JOURNEY_ID,
                               RouteId = (int)j.ROUTE_ID,
                               CoachId = (int)j.COACH_ID,
                               EmployeeId = j.EMPLOYEE_ID,
                               DepartureTime = j.START_DATE_TIME.ToString(),
                               ArrivalTime = j.END_DATE_TIME.ToString()

                           };
            return journeys;
        }

        // GET: api/Journeys/5
        [ResponseType(typeof(JOURNEY))]
        public IHttpActionResult GetJOURNEY(decimal id)
        {
            JOURNEY jOURNEY = db.JOURNEYS.Find(id);
            if (jOURNEY == null)
            {
                return NotFound();
            }

            return Ok(jOURNEY);
        }

        // PUT: api/Journeys/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutJOURNEY(decimal id, JOURNEY jOURNEY)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != jOURNEY.JOURNEY_ID)
            {
                return BadRequest();
            }

            db.Entry(jOURNEY).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!JOURNEYExists(id))
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

        // POST: api/Journeys
        [ResponseType(typeof(JOURNEY))]
        public IHttpActionResult PostJOURNEY(JourneyBindingModel journey)
        {
            JOURNEY jOURNEY = new JOURNEY()
            {
                JOURNEY_ID = 0,
                ROUTE_ID = journey.RouteId,
                COACH_ID = journey.CoachId,
                EMPLOYEE_ID = journey.EmployeeId,
                START_DATE_TIME = journey.DepartureTime,
                END_DATE_TIME = journey.ArrivalTime
            };

            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.JOURNEYS.Add(jOURNEY);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (JOURNEYExists(jOURNEY.JOURNEY_ID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            HttpResponseMessage responseMessage = Request.CreateResponse(HttpStatusCode.Created, journey);
            return ResponseMessage(responseMessage);
        }

        // DELETE: api/Journeys/5
        [ResponseType(typeof(JOURNEY))]
        public IHttpActionResult DeleteJOURNEY(decimal id)
        {
            JOURNEY jOURNEY = db.JOURNEYS.Find(id);
            if (jOURNEY == null)
            {
                return NotFound();
            }

            db.JOURNEYS.Remove(jOURNEY);
            db.SaveChanges();

            return Ok(jOURNEY);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool JOURNEYExists(decimal id)
        {
            return db.JOURNEYS.Count(e => e.JOURNEY_ID == id) > 0;
        }
    }
}