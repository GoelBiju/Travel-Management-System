﻿using System;
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
    [Authorize]
    [RoutePrefix("api/journeys")]
    public class JourneysController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Journeys
        [HttpGet]
        [Route("")]
        [ResponseType(typeof(JourneyDTO))]
        public IQueryable<JourneyDTO> GetJOURNEYS()
        {
            // Customers do not need to see shiftId; needs separate controllers?
            var journeys = from j in db.JOURNEYS
                           select new JourneyDTO()
                           {
                               JourneyId = (int)j.JOURNEY_ID,
                               RouteId = (int)j.ROUTE_ID,
                               CoachId = (int)j.COACH_ID,
                               DepartureDateTime = j.DEPARTURE_DATETIME,
                               ArrivalDateTime = j.ARRIVAL_DATETIME,
                               CurrentStopId = (int)j.CURRENT_STOP,
                               StopArrivalDateTime = j.STOP_ARRIVAL_DATETIME,
                               StopDepartedDateTime = j.STOP_DEPARTED_DATETIME,
                               CoachStatus = j.COACH_STATUS,
                               Route = new RouteDTO()
                               {
                                   RouteId = (int)j.ROUTE.ROUTE_ID,
                                   DepartureStationId = (int)j.ROUTE.STOP1.STOP_ID,
                                   DepartureStation = j.ROUTE.STOP1.STOP_NAME,
                                   ArrivalStationId = (int)j.ROUTE.STOP.STOP_ID,
                                   ArrivalStation = j.ROUTE.STOP.STOP_NAME
                               },
                               CurrentStop = (j.CURRENT_STOP != null) ? db.STOPS.Select(s =>
                               new StopDTO()
                               {
                                   StopId = (int)s.STOP_ID,
                                   StopName = s.STOP_NAME,
                                   IsStation = s.IS_STATION,
                                   StopPostcode = s.STOP_POSTCODE,
                                   StopLatitude = s.STOP_LATITUDE,
                                   StopLongitude = s.STOP_LONGITUDE
                               }).FirstOrDefault(s => s.StopId == j.CURRENT_STOP) : null
                           };

            return journeys;
        }

        // GET: api/Journeys/
        [HttpGet]
        [Route("{id:int}")]
        [ResponseType(typeof(JourneyDTO))]
        public async Task<IHttpActionResult> GetJOURNEY(decimal id)
        {
            //JOURNEY jOURNEY = db.JOURNEYS.Find(id);

            var journey = await db.JOURNEYS.Select(j =>
                new JourneyDTO()
                {
                    JourneyId = (int)j.JOURNEY_ID,
                    RouteId = (int)j.ROUTE_ID,
                    CoachId = (int)j.COACH_ID,
                    DepartureDateTime = j.DEPARTURE_DATETIME,
                    ArrivalDateTime = j.ARRIVAL_DATETIME,
                    CurrentStopId = (int)j.CURRENT_STOP,
                    StopArrivalDateTime = j.STOP_ARRIVAL_DATETIME,
                    StopDepartedDateTime = j.STOP_DEPARTED_DATETIME,
                    CoachStatus = j.COACH_STATUS,
                    Route = new RouteDTO()
                    {
                        RouteId = (int)j.ROUTE.ROUTE_ID,
                        DepartureStationId = (int)j.ROUTE.STOP1.STOP_ID,
                        DepartureStation = j.ROUTE.STOP1.STOP_NAME,
                        ArrivalStationId = (int)j.ROUTE.STOP.STOP_ID,
                        ArrivalStation = j.ROUTE.STOP.STOP_NAME
                    },
                    CurrentStop = (j.CURRENT_STOP != null) ? db.STOPS.Select(s =>
                    new StopDTO()
                    {
                        StopId = (int)s.STOP_ID,
                        IsStation = s.IS_STATION,
                        StopPostcode = s.STOP_POSTCODE,
                        StopLatitude = s.STOP_LATITUDE,
                        StopLongitude = s.STOP_LONGITUDE
                    }).FirstOrDefault(s => s.StopId == j.CURRENT_STOP) : null
                }).SingleOrDefaultAsync(j => j.JourneyId == id);

            if (journey == null)
            {
                return NotFound();
            }

            return Ok(journey);
        }

        // PUT: api/Journeys/5
        [HttpPut, Authorize(Roles = "Employee")]
        [Route("{id:int}")]
        [ResponseType(typeof(void))]
        public IHttpActionResult PutJOURNEY(decimal id, [FromBody] JourneyUpdateBindingModel journey)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            //if (id != jOURNEY.JOURNEY_ID)
            //{
            //    return BadRequest();
            //}

            //db.Entry(jOURNEY).State = EntityState.Modified;

            // Update the relevant attributes from the binding model 
            // with our record in the database.
            if (journey.JourneyId > 0 && id == journey.JourneyId)
            {
                var journeyRecord = db.JOURNEYS.FirstOrDefault(j => j.JOURNEY_ID == id);

                if (journeyRecord != null)
                {
                    journeyRecord.CURRENT_STOP = journey.CurrentStopId;
                    journeyRecord.STOP_ARRIVAL_DATETIME = journey.StopArrivalDateTime;
                    journeyRecord.STOP_DEPARTED_DATETIME = journey.StopDepartedDateTime;
                    journeyRecord.COACH_STATUS = journey.CoachStatus;
                }
                else
                {
                    return NotFound();
                }
            }
            else
            {
                BadRequest();
            }

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
        [HttpPost, Authorize(Roles = "Employee")]
        [Route("")]
        [ResponseType(typeof(JourneyDTO))]
        public IHttpActionResult PostJOURNEY([FromBody] JourneyCreationBindingModel journey)
        {
            // Create a new journey object as "Scheduled".
            JOURNEY jOURNEY = new JOURNEY()
            {
                JOURNEY_ID = 0,
                ROUTE_ID = journey.RouteId,
                COACH_ID = journey.CoachId,
                DEPARTURE_DATETIME = journey.DepartureDateTime,
                ARRIVAL_DATETIME = journey.ArrivalDateTime,
                COACH_STATUS = "SCHEDULED"
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
                //if (JOURNEYExists(jOURNEY.JOURNEY_ID))
                //{
                //    return Conflict();
                //}
                //else
                //{
                //    throw;
                //}

                return BadRequest();
            }

            HttpResponseMessage responseMessage = Request.CreateResponse(HttpStatusCode.Created, journey);
            return ResponseMessage(responseMessage);
            //return Ok();
        }

        //// DELETE: api/Journeys/5
        [HttpDelete, Authorize(Roles = "Employee")]
        [Route("{id:int}")]
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