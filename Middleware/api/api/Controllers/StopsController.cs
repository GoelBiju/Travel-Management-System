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
    [Authorize]
    [RoutePrefix("api/stops")]
    public class StopsController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Stops
        [HttpGet]
        [Route("")]
        [ResponseType(typeof(StopDTO))]
        public IQueryable<StopDTO> GetSTOPS()
        {
            var stops = from s in db.STOPS
                        select new StopDTO()
                        {
                            StopId = (int)s.STOP_ID,
                            StopName = s.STOP_NAME,
                            IsStation = s.IS_STATION,
                            StopPostcode = s.STOP_POSTCODE,
                            StopLatitude = s.STOP_LATITUDE,
                            StopLongitude = s.STOP_LONGITUDE
                        };

            return stops;
        }

        // GET: api/Stops/5
        [HttpGet]
        [Route("{id:int}")]
        [ResponseType(typeof(StopDTO))]
        public async Task<IHttpActionResult> GetSTOP(decimal id)
        {
            //STOP sTOP = db.STOPS.Find(id);

            var stop = await db.STOPS.Select(s =>
                new StopDTO()
                {
                    StopId = (int)s.STOP_ID,
                    StopName = s.STOP_NAME,
                    IsStation = s.IS_STATION,
                    StopPostcode = s.STOP_POSTCODE,
                    StopLatitude = s.STOP_LATITUDE,
                    StopLongitude = s.STOP_LONGITUDE
                }).SingleOrDefaultAsync(s => s.StopId == id);

            if (stop == null)
            {
                return NotFound();
            }

            return Ok(stop);
        }

        // PUT: api/Stops/5
        //[HttpPut, Authorize(Roles = "Employee")]
        //[Route("{id:int}")]
        //[ResponseType(typeof(void))]
        //public IHttpActionResult PutSTOP(decimal id, STOP sTOP)
        //{
        //    if (!ModelState.IsValid)
        //    {
        //        return BadRequest(ModelState);
        //    }

        //    if (id != sTOP.STOP_ID)
        //    {
        //        return BadRequest();
        //    }

        //    db.Entry(sTOP).State = EntityState.Modified;

        //    try
        //    {
        //        db.SaveChanges();
        //    }
        //    catch (DbUpdateConcurrencyException)
        //    {
        //        if (!STOPExists(id))
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

        // POST: api/Stops
        [HttpPost, Authorize(Roles = "Employee")]
        [Route("")]
        [ResponseType(typeof(StopDTO))]
        public IHttpActionResult PostSTOP([FromBody] StopCreationBindingModel stop)
        {
            STOP addStop = new STOP()
            {
                STOP_ID = 0,
                STOP_NAME = stop.StopName,
                IS_STATION = stop.IsStation,
                STOP_POSTCODE = stop.StopPostcode,
                STOP_LATITUDE = stop.StopLatitude,
                STOP_LONGITUDE = stop.StopLongitude
            };

            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.STOPS.Add(addStop);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                //if (STOPExists(sTOP.STOP_ID))
                //{
                //    return Conflict();
                //}
                //else
                //{
                //    throw;
                //}

                return BadRequest();
            }

            //return CreatedAtRoute("DefaultApi", new { id = sTOP.STOP_ID }, sTOP);
            HttpResponseMessage responseMessage = Request.CreateResponse(HttpStatusCode.Created, stop);
            return ResponseMessage(responseMessage);
        }

        // DELETE: api/Stops/5
        [HttpDelete, Authorize(Roles = "Employee")]
        [Route("{id:int}")]
        [ResponseType(typeof(STOP))]
        public IHttpActionResult DeleteSTOP(decimal id)
        {
            STOP sTOP = db.STOPS.Find(id);
            if (sTOP == null)
            {
                return NotFound();
            }

            db.STOPS.Remove(sTOP);
            db.SaveChanges();

            return Ok(sTOP);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool STOPExists(decimal id)
        {
            return db.STOPS.Count(e => e.STOP_ID == id) > 0;
        }
    }
}