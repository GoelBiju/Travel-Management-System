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
using api.Models.BindingModels;
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
        [Route("customer/{id:int}")]
        [ResponseType(typeof(BookingDTO))]
        public IQueryable<BookingDTO> GetBookingsByCustomer(int id)
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
                                   //CurrentStop = null,
                                   StopArrivalDateTime = b.JOURNEY.STOP_ARRIVAL_DATETIME,
                                   StopDepartedDateTime = b.JOURNEY.STOP_DEPARTED_DATETIME,
                                   CoachStatus = b.JOURNEY.COACH_STATUS
                               },
                               DepartingStop = new StopDTO()
                               {
                                   StopId = (int)b.STOP1.STOP_ID,
                                   StopName = b.STOP1.STOP_NAME,
                                   IsStation = b.STOP.IS_STATION,
                                   StopPostcode = b.STOP.STOP_POSTCODE,
                                   StopLatitude = b.STOP.STOP_LATITUDE,
                                   StopLongitude = b.STOP.STOP_LONGITUDE
                               },
                               ArrivalStop = new StopDTO()
                               {
                                   StopId = (int)b.STOP.STOP_ID,
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

        [HttpGet, Authorize(Roles = "Employee")]
        [Route("journey/{id:int}")]
        [ResponseType(typeof(BookingDTO))]
        public IQueryable<BookingDTO> GetBookingsByJourney(int id)
        {
            var bookings = from b in db.BOOKINGS.Where(b => b.JOURNEY_ID == id)
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
                                   CurrentStop = (b.JOURNEY.CURRENT_STOP != null) ? db.STOPS.Select(s => 
                                   new StopDTO()
                                   {
                                       StopId = (int)s.STOP_ID,
                                       StopName = s.STOP_NAME,
                                       IsStation = s.IS_STATION,
                                       StopPostcode = s.STOP_POSTCODE,
                                       StopLatitude = s.STOP_LATITUDE,
                                       StopLongitude = s.STOP_LONGITUDE
                                   }).FirstOrDefault(s => s.StopId == b.JOURNEY.CURRENT_STOP) : null,

                                   StopArrivalDateTime = b.JOURNEY.STOP_ARRIVAL_DATETIME,
                                   StopDepartedDateTime = b.JOURNEY.STOP_DEPARTED_DATETIME,
                                   CoachStatus = b.JOURNEY.COACH_STATUS
                               },
                               DepartingStop = new StopDTO()
                               {
                                   StopId = (int)b.STOP1.STOP_ID,
                                   StopName = b.STOP1.STOP_NAME,
                                   IsStation = b.STOP.IS_STATION,
                                   StopPostcode = b.STOP.STOP_POSTCODE,
                                   StopLatitude = b.STOP.STOP_LATITUDE,
                                   StopLongitude = b.STOP.STOP_LONGITUDE
                               },
                               ArrivalStop = new StopDTO()
                               {
                                   StopId = (int)b.STOP.STOP_ID,
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
                        StopId = (int)b.STOP1.STOP_ID,
                        StopName = b.STOP1.STOP_NAME,
                        IsStation = b.STOP.IS_STATION,
                        StopPostcode = b.STOP.STOP_POSTCODE,
                        StopLatitude = b.STOP.STOP_LATITUDE,
                        StopLongitude = b.STOP.STOP_LONGITUDE
                    },
                    ArrivalStop = new StopDTO()
                    {
                        StopId = (int)b.STOP.STOP_ID,
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
        [HttpPut, Authorize(Roles = "Employee")]
        [Route("{id:int}")]
        [ResponseType(typeof(void))]
        public IHttpActionResult PutBOOKING(decimal id, [FromBody] BookingUpdateBindingModel booking)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != booking.BookingReference)
            {
                return BadRequest();
            }

            //db.Entry(bOOKING).State = EntityState.Modified;

            var bookingRecord = db.BOOKINGS.FirstOrDefault(b => b.BOOKING_REFERENCE == id);

            if (bookingRecord != null)
            {
                bookingRecord.STATUS = booking.Status;
            }
            else
            {
                return NotFound();
            }

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!BOOKINGExists(id))
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

        // POST: api/Bookings
        [HttpPost, Authorize(Roles = "Customer")]
        [Route("")]
        [ResponseType(typeof(BookingDTO))]
        public async Task<IHttpActionResult> PostBOOKING([FromBody]BookingCreationBindingModel booking)
        {
            BOOKING newBooking = new BOOKING()
            {
                BOOKING_REFERENCE = 0,
                CUSTOMER_ID = booking.CustomerId,
                JOURNEY_ID = booking.JourneyId,
                DEPARTING_STOP = booking.DepartingStopId,
                ARRIVAL_STOP = booking.ArrivalStopId,
                BOOKED_DATETIME = booking.BookedDateTime,
                PAYMENT_ID = booking.PaymentId,
                PASSENGERS_SENIOR = booking.PassengersSenior,
                PASSENGERS_ADULT = booking.PassengersAdult,
                PASSENGERS_CHILDREN = booking.PassengersChildren,
                PASSENGERS_INFANT = booking.PassengersInfant,
                AMOUNT_PAID = booking.AmountPaid,
                STATUS = "Confirmed"
            };

            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.BOOKINGS.Add(newBooking);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                //if (BOOKINGExists(bOOKING.BOOKING_REFERENCE))
                //{
                //    return Conflict();
                //}
                //else
                //{
                //    throw;
                //}

                return BadRequest();
            }

            // Get the latest booking by this customer; due to the fact that the booking reference is only updated by database.
            var latestBooking = await db.BOOKINGS.OrderByDescending(b => b.BOOKED_DATETIME)
                .Select(b => new BookingDTO()
                {
                    BookingReference = (int)b.BOOKING_REFERENCE,
                    CustomerId = (int)b.CUSTOMER_ID,
                    Journey = new JourneyDTO()
                    {
                        JourneyId = (int)b.JOURNEY.JOURNEY_ID
                    },
                    DepartingStop = new StopDTO()
                    {
                        StopId = (int)b.STOP.STOP_ID
                    },
                    ArrivalStop = new StopDTO()
                    {
                        StopId = (int)b.STOP1.STOP_ID
                    },
                    BookedDateTime = b.BOOKED_DATETIME,
                    PassengersSenior = (int)b.PASSENGERS_SENIOR,
                    PassengersAdult = (int)b.PASSENGERS_ADULT,
                    PassengersChildren = (int)b.PASSENGERS_CHILDREN,
                    PassengersInfant = (int)b.PASSENGERS_INFANT,
                    AmountPaid = b.AMOUNT_PAID,
                    Status = b.STATUS
                }).FirstOrDefaultAsync(b => b.CustomerId == booking.CustomerId);

            //return CreatedAtRoute("DefaultApi", new { id = bOOKING.BOOKING_REFERENCE }, bOOKING);
            HttpResponseMessage responseMessage = Request.CreateResponse(HttpStatusCode.Created, latestBooking);
            return ResponseMessage(responseMessage);
        }

        // DELETE: api/Bookings/5
        [HttpDelete, Authorize(Roles = "Customer")]
        [Route("{id:int}")]
        [ResponseType(typeof(JOURNEY))]
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