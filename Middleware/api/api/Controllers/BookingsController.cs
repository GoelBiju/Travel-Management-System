using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Security.Claims;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http;
using System.Web.Http.Description;
using api.Models;
using api.Models.DTO;
using Microsoft.AspNet.Identity;


namespace api.Controllers
{
    [Authorize]
    [RoutePrefix("api/bookings")]
    public class BookingsController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Bookings
        //public iqueryable<booking> getbookings()
        //{
        //    var bookings = from b in db.bookings
        //                   select new
        //    return db.bookings;
        //}

        // TODO: Get all bookings for a specific customer.
        [HttpGet, Authorize(Roles = "Customer")]
        [Route("customer/{id:int:}")]
        [ResponseType(typeof(BookingDTO))]
        public IQueryable<BookingDTO> GetBookings(int id)
        {
            var bookings = from b in db.BOOKINGS.Where(b => b.CUSTOMER_ID == id)
                           select new BookingDTO()
                           {
                               BookingReference = (int)b.BOOKING_REFERENCE,
                               CustomerId = (int)b.CUSTOMER_ID,
                               Journey = new JourneyDTO()
                               {
                                   JourneyId = (int)b.JOURNEY.JOURNEY_ID,
                                   RouteId = (int)b.JOURNEY.ROUTE_ID,
                                   CoachId = (int)b.JOURNEY.COACH_ID,
                                   DepartureDateTime = b.JOURNEY.DEPARTURE_DATETIME,
                                   ArrivalDateTime = b.JOURNEY.ARRIVAL_DATETIME,
                                   CurrentStop = (b.JOURNEY.CURRENT_STOP != null) ? new StopDTO() : null,
                                   StopArrivalDateTime = b.JOURNEY.STOP_ARRIVAL_DATETIME,
                                   StopDepartedDateTime = b.JOURNEY.STOP_DEPARTED_DATETIME,
                                   CoachStatus = b.JOURNEY.COACH_STATUS
                               },
                               DepartingStop = new StopDTO()
                               {
                                   StopId = (int)b.STOP.STOP_ID,
                                   StopName = b.STOP.STOP_NAME,
                                   IsStation = b.STOP.IS_STATION,
                                   StopPostcode = b.STOP.STOP_POSTCODE,
                                   StopLatitude = b.STOP.STOP_LATITUDE,
                                   StopLongitude = b.STOP.STOP_LONGITUDE
                               },
                               ArrivalStop = new StopDTO()
                               {
                                   StopId = (int)b.STOP1.STOP_ID,
                                   StopName = b.STOP.STOP_NAME,
                                   IsStation = b.STOP.IS_STATION,
                                   StopPostcode = b.STOP.STOP_POSTCODE,
                                   StopLatitude = b.STOP.STOP_LATITUDE,
                                   StopLongitude = b.STOP.STOP_LONGITUDE
                               },
                               BookedDateTime = b.BOOKED_DATETIME,
                               PassengersSenior = (int)b.PASSENGERS_SENIOR,
                               PassengersAdult = (int)b.PASSENGERS_ADULT,
                               PassengersChildren = (int)b.PASSENGERS_CHILDREN,
                               PassengersInfant = (int)b.PASSENGERS_INFANT,
                               AmountPaid = b.AMOUNT_PAID,
                               Status = b.STATUS
                           };

            return bookings;
        }

        // GET: api/Bookings/5
        [HttpGet, Authorize(Roles = "Customer, Employee")]
        [Route("{id:int}")]
        [ResponseType(typeof(BookingDTO))]
        public async Task<IHttpActionResult> GetBOOKING(decimal id)
        {
            //ClaimsPrincipal principal = Request.GetRequestContext().Principal as ClaimsPrincipal;
            //var identification = User.Identity.GetUserId();

            //BOOKING bOOKING = db.BOOKINGS.Find(id);

            var booking = await db.BOOKINGS.Select(b =>
                new BookingDTO()
                {
                    BookingReference = (int)b.BOOKING_REFERENCE,
                    CustomerId = (int)b.CUSTOMER_ID,
                    Journey = new JourneyDTO()
                    {
                        JourneyId = (int)b.JOURNEY.JOURNEY_ID,
                        RouteId = (int)b.JOURNEY.ROUTE_ID,
                        CoachId = (int)b.JOURNEY.COACH_ID,
                        DepartureDateTime = b.JOURNEY.DEPARTURE_DATETIME,
                        ArrivalDateTime = b.JOURNEY.ARRIVAL_DATETIME,
                        CurrentStopId = (int)b.JOURNEY.CURRENT_STOP,
                        StopArrivalDateTime = b.JOURNEY.STOP_ARRIVAL_DATETIME,
                        StopDepartedDateTime = b.JOURNEY.STOP_DEPARTED_DATETIME,
                        CoachStatus = b.JOURNEY.COACH_STATUS,
                        CurrentStop = (b.JOURNEY.CURRENT_STOP != null) ? db.STOPS.Select(s =>
                        new StopDTO()
                        {
                            StopId = (int)s.STOP_ID,
                            StopName = s.STOP_NAME,
                            IsStation = s.IS_STATION,
                            StopPostcode = s.STOP_POSTCODE,
                            StopLatitude = s.STOP_LATITUDE,
                            StopLongitude = s.STOP_LONGITUDE
                        }).FirstOrDefault(s => s.StopId == b.JOURNEY.CURRENT_STOP) : null
                    },
                    DepartingStop = new StopDTO()
                    {
                        StopId = (int)b.STOP.STOP_ID,
                        StopName = b.STOP.STOP_NAME,
                        IsStation = b.STOP.IS_STATION,
                        StopPostcode = b.STOP.STOP_POSTCODE,
                        StopLatitude = b.STOP.STOP_LATITUDE,
                        StopLongitude = b.STOP.STOP_LONGITUDE
                    },
                    ArrivalStop = new StopDTO()
                    {
                        StopId = (int)b.STOP1.STOP_ID,
                        StopName = b.STOP.STOP_NAME,
                        IsStation = b.STOP.IS_STATION,
                        StopPostcode = b.STOP.STOP_POSTCODE,
                        StopLatitude = b.STOP.STOP_LATITUDE,
                        StopLongitude = b.STOP.STOP_LONGITUDE
                    },
                    BookedDateTime = b.BOOKED_DATETIME,
                    PassengersSenior = (int)b.PASSENGERS_SENIOR,
                    PassengersAdult = (int)b.PASSENGERS_ADULT,
                    PassengersChildren = (int)b.PASSENGERS_CHILDREN,
                    PassengersInfant = (int)b.PASSENGERS_INFANT,
                    AmountPaid = b.AMOUNT_PAID,
                    Status = b.STATUS
                }).SingleOrDefaultAsync(b => b.BookingReference == id);

            if (booking == null)
            {
                return NotFound();
            }

            return Ok(booking);
        }

        // PUT: api/Bookings/5
        //[ResponseType(typeof(void))]
        //public IHttpActionResult PutBOOKING(decimal id, BOOKING bOOKING)
        //{
        //    if (!ModelState.IsValid)
        //    {
        //        return BadRequest(ModelState);
        //    }

        //    if (id != bOOKING.BOOKING_REFERENCE)
        //    {
        //        return BadRequest();
        //    }

        //    db.Entry(bOOKING).State = EntityState.Modified;

        //    try
        //    {
        //        db.SaveChanges();
        //    }
        //    catch (DbUpdateConcurrencyException)
        //    {
        //        if (!BOOKINGExists(id))
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

        // POST: api/Bookings
        [HttpPost, Authorize(Roles = "Customer")]
        [Route("")]
        [ResponseType(typeof(void))]
        public IHttpActionResult PostBOOKING(BOOKING bOOKING)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.BOOKINGS.Add(bOOKING);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (BOOKINGExists(bOOKING.BOOKING_REFERENCE))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = bOOKING.BOOKING_REFERENCE }, bOOKING);
        }

        // DELETE: api/Bookings/5
        [ResponseType(typeof(BOOKING))]
        public IHttpActionResult DeleteBOOKING(decimal id)
        {
            BOOKING bOOKING = db.BOOKINGS.Find(id);
            if (bOOKING == null)
            {
                return NotFound();
            }

            db.BOOKINGS.Remove(bOOKING);
            db.SaveChanges();

            return Ok(bOOKING);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool BOOKINGExists(decimal id)
        {
            return db.BOOKINGS.Count(e => e.BOOKING_REFERENCE == id) > 0;
        }
    }
}