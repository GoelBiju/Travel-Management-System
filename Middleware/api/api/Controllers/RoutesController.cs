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
    [Authorize]
    [RoutePrefix("api/routes")]
    public class RoutesController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Routes
        [HttpGet]
        [Route("")]
        [ResponseType(typeof(RouteDTO))]
        public IQueryable<RouteDTO> GetROUTES()
        {
            var routes = from r in db.ROUTES
                         select new RouteDTO()
                         {
                             RouteId = (int)r.ROUTE_ID,
                             DepartureStationId = (int)r.DEPARTURE_STATION,
                             DepartureStation = r.STOP.STOP_NAME,
                             ArrivalStationId = (int)r.ARRIVAL_STATION,
                             ArrivalStation = r.STOP1.STOP_NAME
                         };

            return routes;
        }

        // GET: api/Routes/5
        [HttpGet]
        [Route("{id:int}", Name = "GetRouteDetailsById")]
        [ResponseType(typeof(RouteDTO))]
        public async Task<IHttpActionResult> GetROUTE(int id)
        {
            //ROUTE rOUTE = db.ROUTES.Find(id);   

            var route = await db.ROUTES.Select(r =>
                new RouteDTO()
                {
                    RouteId = (int)r.ROUTE_ID,
                    DepartureStationId = (int)r.DEPARTURE_STATION,
                    DepartureStation = r.STOP.STOP_NAME,
                    ArrivalStationId = (int)r.ARRIVAL_STATION,
                    ArrivalStation = r.STOP1.STOP_NAME
                }).SingleOrDefaultAsync(r => r.RouteId == id);


            if (route == null)
            {
                return NotFound();
            }

            return Ok(route);
        }

        // PUT: api/Routes/5
        //[ResponseType(typeof(void))]
        //public IHttpActionResult PutROUTE(decimal id, ROUTE rOUTE)
        //{
        //    if (!ModelState.IsValid)
        //    {
        //        return BadRequest(ModelState);
        //    }

        //    if (id != rOUTE.ROUTE_ID)
        //    {
        //        return BadRequest();
        //    }

        //    db.Entry(rOUTE).State = EntityState.Modified;

        //    try
        //    {
        //        db.SaveChanges();
        //    }
        //    catch (DbUpdateConcurrencyException)
        //    {
        //        if (!ROUTEExists(id))
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

        // POST: api/Routes
        [HttpPost, Authorize(Roles = "Employees")]
        [Route("")]
        [ResponseType(typeof(ROUTE))]
        public IHttpActionResult PostROUTE(ROUTE rOUTE)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.ROUTES.Add(rOUTE);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (ROUTEExists(rOUTE.ROUTE_ID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = rOUTE.ROUTE_ID }, rOUTE);
        }

        // DELETE: api/Routes/5
        [HttpDelete, Authorize(Roles = "Employee")]
        [Route("{id:int}")]
        [ResponseType(typeof(ROUTE))]
        public IHttpActionResult DeleteROUTE(string id)
        {
            ROUTE rOUTE = db.ROUTES.Find(id);
            if (rOUTE == null)
            {
                return NotFound();
            }

            db.ROUTES.Remove(rOUTE);
            db.SaveChanges();

            return Ok(rOUTE);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool ROUTEExists(decimal id)
        {
            return db.ROUTES.Count(e => e.ROUTE_ID == id) > 0;
        }
    }
}