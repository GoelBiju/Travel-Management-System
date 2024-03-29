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
    [RoutePrefix("api/stations")]
    public class StationsController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Stations
        [HttpGet]
        [Route("")]
        public IQueryable<StationDTO> GetSTATIONS()
        {
            var stations = from s in db.STATIONS
                           select new StationDTO()
                           {
                               StationId = (int)s.STATION_ID,
                               StationName = s.STATION_NAME
                           };

            return stations;
        }

        // GET: api/Stations/5
        [HttpGet]
        [Route("{id:int}", Name = "GetStationDetailsById")]
        [ResponseType(typeof(StationDTO))]
        public async Task<IHttpActionResult> GetSTATION(int id)
        {
            //STATION sTATION = db.STATIONS.Find(id);
            var station = await db.STATIONS.Select(s =>
                new StationDTO()
                {
                    StationId = (int)s.STATION_ID,
                    StationName = s.STATION_NAME
                }).SingleOrDefaultAsync(s => s.StationId == id);


            if (station == null)
            {
                return NotFound();
            }

            return Ok(station);
        }

        // PUT: api/Stations/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutSTATION(decimal id, STATION sTATION)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != sTATION.STATION_ID)
            {
                return BadRequest();
            }

            db.Entry(sTATION).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!STATIONExists(id))
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

        // POST: api/Stations
        [ResponseType(typeof(STATION))]
        public IHttpActionResult PostSTATION(STATION sTATION)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.STATIONS.Add(sTATION);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (STATIONExists(sTATION.STATION_ID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = sTATION.STATION_ID }, sTATION);
        }

        // DELETE: api/Stations/5
        [ResponseType(typeof(STATION))]
        public IHttpActionResult DeleteSTATION(decimal id)
        {
            STATION sTATION = db.STATIONS.Find(id);
            if (sTATION == null)
            {
                return NotFound();
            }

            db.STATIONS.Remove(sTATION);
            db.SaveChanges();

            return Ok(sTATION);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool STATIONExists(decimal id)
        {
            return db.STATIONS.Count(e => e.STATION_ID == id) > 0;
        }
    }
}