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

            var stops = await db.ROUTE_STOPS
                .Include(s => s.ROUTE)
                .Include(s => s.STOP)
                .Where(s => s.ROUTE_ID == routeId)
                .Select(s =>
                new RouteStopDTO()
                {
                    StopId = (int)s.STOP_ID,
                    StopName = s.STOP.STOP_NAME,
                    PositionInRoute = (int)s.POSITION_IN_ROUTE
                }).ToListAsync();

            if (stops.Count == 0)
            {
                return NotFound();
            }

            return Ok(stops);
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
        public async Task<IHttpActionResult> PostROUTE_STOPS(ROUTE_STOPS rOUTE_STOPS)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.ROUTE_STOPS.Add(rOUTE_STOPS);

            try
            {
                await db.SaveChangesAsync();
            }
            catch (DbUpdateException)
            {
                if (ROUTE_STOPSExists(rOUTE_STOPS.ROUTE_ID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = rOUTE_STOPS.ROUTE_ID }, rOUTE_STOPS);
        }

        // DELETE: api/RouteStops/5
        //[ResponseType(typeof(ROUTE_STOPS))]
        //public async Task<IHttpActionResult> DeleteROUTE_STOPS(decimal id)
        //{
        //    ROUTE_STOPS rOUTE_STOPS = await db.ROUTE_STOPS.FindAsync(id);
        //    if (rOUTE_STOPS == null)
        //    {
        //        return NotFound();
        //    }

        //    db.ROUTE_STOPS.Remove(rOUTE_STOPS);
        //    await db.SaveChangesAsync();

        //    return Ok(rOUTE_STOPS);
        //}

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool ROUTE_STOPSExists(decimal id)
        {
            return db.ROUTE_STOPS.Count(e => e.ROUTE_ID == id) > 0;
        }
    }
}