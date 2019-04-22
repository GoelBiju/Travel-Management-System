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
    [RoutePrefix("api/routestops")]
    public class RouteStopsController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/RouteStops
        //public IQueryable<ROUTE_STOPS> GetROUTE_STOPS()
        //{
        //    return db.ROUTE_STOPS;
        //}

        // GET: api/RouteStops/5
        [HttpGet]
        [Route("{routeId:int}", Name = "GetRouteStopsByRouteId")]
        [ResponseType(typeof(RouteStopDTO))]
        public async Task<IHttpActionResult> GetROUTE_STOPS(int routeId)
        {
            //ROUTE_STOPS rOUTE_STOPS = await db.ROUTE_STOPS.FindAsync(id);

            // Retrieve all stops for a specific route based on the route id. 
            // Stop names are also retrieved.
            var stops = await db.ROUTE_STOPS
                .Include(s => s.ROUTE)
                .Include(s => s.STOP)
                .Where(s => s.ROUTE_ID == routeId)
                .Select(s =>
                new RouteStopDTO()
                {
                    RouteId = (int)s.ROUTE_ID,
                    StopId = (int)s.STOP_ID,
                    StopName = s.STOP.STOP_NAME,
                    StopPostcode = s.STOP.STOP_POSTCODE,
                    StopLatitude = s.STOP.STOP_LATITUDE,
                    StopLongitude = s.STOP.STOP_LONGITUDE,
                    PositionInRoute = (int)s.POSITION_IN_ROUTE
                }).ToListAsync();

            if (stops.Count == 0)
            {
                return NotFound();
            }

            return Ok(stops);
        }

        [HttpGet]
        [Route("{routeId:int}/{stopId:int}")]
        [ResponseType(typeof(RouteStopDTO))]
        public async Task<IHttpActionResult> GetRouteStop(int routeId, int stopId)
        {
            var stop = await db.ROUTE_STOPS
                .Include(s => s.ROUTE)
                .Include(s => s.STOP)
                .Where(s => s.ROUTE_ID == routeId && s.STOP_ID == stopId)
                .Select(s =>
                new RouteStopDTO()
                {
                    RouteId = (int)s.ROUTE_ID,
                    StopId = (int)s.STOP_ID,
                    StopName = s.STOP.STOP_NAME,
                    PositionInRoute = (int)s.POSITION_IN_ROUTE
                }).FirstOrDefaultAsync();

            if (stop == null)
            {
                return NotFound();
            }

            return Ok(stop);
        }

        // PUT: api/RouteStops/5
        //[ResponseType(typeof(void))]
        //public async Task<IHttpActionResult> PutROUTE_STOPS(decimal id, ROUTE_STOPS rOUTE_STOPS)
        //{
        //    if (!ModelState.IsValid)
        //    {
        //        return BadRequest(ModelState);
        //    }

        //    if (id != rOUTE_STOPS.ROUTE_ID)
        //    {
        //        return BadRequest();
        //    }

        //    db.Entry(rOUTE_STOPS).State = EntityState.Modified;

        //    try
        //    {
        //        await db.SaveChangesAsync();
        //    }
        //    catch (DbUpdateConcurrencyException)
        //    {
        //        if (!ROUTE_STOPSExists(id))
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

        // POST: api/RouteStops
        [HttpPost, Authorize(Roles = "Employee")]
        [Route("")]
        [ResponseType(typeof(void))]
        public async Task<IHttpActionResult> PostROUTE_STOPS(RouteStopCreationBindingModel routeStop)
        {
            ROUTE_STOPS addRouteStop = new ROUTE_STOPS()
            {
                ROUTE_ID = routeStop.RouteId,
                STOP_ID = routeStop.StopId,
                POSITION_IN_ROUTE = routeStop.PositionInRoute
            };

            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.ROUTE_STOPS.Add(addRouteStop);

            try
            {
                await db.SaveChangesAsync();
            }
            catch (DbUpdateException)
            {
                if (ROUTE_STOPSExists(addRouteStop.ROUTE_ID, addRouteStop.STOP_ID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = addRouteStop.ROUTE_ID }, addRouteStop);
        }

        // DELETE: api/RouteStops/5
        [HttpPost]
        [Route("Delete")]
        [ResponseType(typeof(void))]
        public async Task<IHttpActionResult> DeleteROUTE_STOPS([FromBody] RouteStopsDeletionBindingModel deleteRouteStop)
        {
            ROUTE_STOPS routeStop = await db.ROUTE_STOPS.SingleOrDefaultAsync(r => r.ROUTE_ID == deleteRouteStop.RouteId 
            && r.STOP_ID == deleteRouteStop.StopId && r.POSITION_IN_ROUTE == deleteRouteStop.PositionInRoute);

            if (routeStop == null)
            {
                return NotFound();
            }

            db.ROUTE_STOPS.Remove(routeStop);
            await db.SaveChangesAsync();

            return Ok(routeStop);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool ROUTE_STOPSExists(decimal routeId, decimal stopId)
        {
            return db.ROUTE_STOPS.Count(e => e.ROUTE_ID == routeId && e.STOP_ID == stopId) > 0;
        }
    }
}