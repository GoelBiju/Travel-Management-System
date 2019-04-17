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
    // Add RoutePrefix to specify the location of this resource.
    [RoutePrefix("api/customers")]
    public class CustomersController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Customers
        [HttpGet]
        [Route("")]
        [Authorize(Roles = "Customer")]
        public IQueryable<CustomerDTO> GetCUSTOMERS()
        {
            var customers = from c in db.CUSTOMERS
                            select new CustomerDTO()
                            {
                                CustomerId = (int)c.CUSTOMER_ID,
                                FirstName = c.FIRST_NAME,
                                LastName = c.LAST_NAME,
                                DateOfBirth = c.DATE_OF_BIRTH,
                                AddressLineOne = c.ADDRESS_LINE_ONE,
                                AddressLineTwo = c.ADDRESS_LINE_TWO,
                                PostCode = c.POSTCODE,
                                MobileNumber = c.MOBILE_NUMBER,
                                EmailAddress = c.EMAIL_ADDRESS
                            };

            return customers;
        }


        // GET: api/Customers/5
        [HttpGet, Authorize(Roles = "Employee")]
        [Route("{id:int}", Name = "GetCustomerDetailsById")]
        [ResponseType(typeof(CustomerDTO))]
        public async Task<IHttpActionResult> GetCUSTOMER(decimal id)
        {
            //CUSTOMER cUSTOMER = db.CUSTOMERS.Find(id);
            // Select the CUSTOMER object from the database model and create a CustomerDTO object from it.
            var customer = await db.CUSTOMERS.Select(c =>
                new CustomerDTO()
                {
                    CustomerId = (int)c.CUSTOMER_ID,
                    FirstName = c.FIRST_NAME,
                    LastName = c.LAST_NAME,
                    DateOfBirth = c.DATE_OF_BIRTH,
                    AddressLineOne = c.ADDRESS_LINE_ONE,
                    AddressLineTwo = c.ADDRESS_LINE_TWO,
                    PostCode = c.POSTCODE,
                    MobileNumber = c.MOBILE_NUMBER,
                    EmailAddress = c.EMAIL_ADDRESS
                }).SingleOrDefaultAsync(c => c.CustomerId == id);

            // If the retrieved customer is null then return a 401 Not Found.
            if (customer == null)
            {
                return NotFound();
            }

            return Ok(customer);
        }


        // PUT: api/Customers/
        [HttpPut]
        [Route("{id:int}")]
        [ResponseType(typeof(void))]
        public IHttpActionResult PutCUSTOMER(int id, CUSTOMER cUSTOMER)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != cUSTOMER.CUSTOMER_ID)
            {
                return BadRequest();
            }

            db.Entry(cUSTOMER).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!CustomerExists(id))
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


        // POST: api/Customers
        // Used to CREATE a new customer and add them to our database.
        [HttpPost]
        [Route("")]
        [ResponseType(typeof(CustomerDTO))]
        public IHttpActionResult PostCUSTOMER([FromBody] CustomerRegistrationBindingModel registrationDetails)
        {
            // Create customer object based on the registration details received.
            CUSTOMER customer = new CUSTOMER()
            {
                CUSTOMER_ID = 0,
                EMAIL_ADDRESS = registrationDetails.emailAddress,
                CUSTOMER_PASSWORD = registrationDetails.customerPassword,
                FIRST_NAME = registrationDetails.firstName,
                LAST_NAME = registrationDetails.lastName,
                DATE_OF_BIRTH = registrationDetails.dateOfBirth,
                ADDRESS_LINE_ONE = registrationDetails.addressLineOne,
                ADDRESS_LINE_TWO = registrationDetails.addressLineTwo,
                POSTCODE = registrationDetails.postCode,
                MOBILE_NUMBER = registrationDetails.mobileNumber
            };

            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            // Check if the customer already exists under the email address provided, 
            // if so return a Conflict HTTP response code.
            if (CustomerExists(customer.EMAIL_ADDRESS))
            {
                return Conflict();
            }

            // Add the customer object to our database as a record.
            db.CUSTOMERS.Add(customer);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                // A DbUpdateException will occur if the data does not match what the database expects
                // i.e. constraints are violated, we can send back a bad request HTTP response.
                //if (CUSTOMERExists(cUSTOMER.CUSTOMER_ID))
                return BadRequest();
            }

            // Find the added user by the email address provided by the client.
            // NOTE: Ensure that when passing in parameters to SingleOrDefault that the statement makes sense otherwise a UriHelper.Link
            //       error may occur on the return.
            CUSTOMER addedCustomer = db.CUSTOMERS.SingleOrDefault(dbCustomer => dbCustomer.EMAIL_ADDRESS == customer.EMAIL_ADDRESS);
            if (addedCustomer == null)
            {
                //throw new HttpResponseException(Request.CreateResponse(HttpStatusCode.NotFound));
                return NotFound();
            }

            // Create a CustomerDTO object from the CUSTOMER object to send back in response.
            CustomerDTO customerDetails = new CustomerDTO()
            {
                CustomerId = (int)addedCustomer.CUSTOMER_ID,
                FirstName = addedCustomer.FIRST_NAME,
                LastName = addedCustomer.LAST_NAME,
                DateOfBirth = addedCustomer.DATE_OF_BIRTH,
                AddressLineOne = addedCustomer.ADDRESS_LINE_ONE,
                AddressLineTwo = addedCustomer.ADDRESS_LINE_TWO,
                PostCode = addedCustomer.POSTCODE,
                MobileNumber = addedCustomer.MOBILE_NUMBER,
                EmailAddress = addedCustomer.EMAIL_ADDRESS
            };

            return CreatedAtRoute("GetCustomerDetailsById", new { id = customerDetails.CustomerId }, customerDetails);
        }


        // POST: api/Customers/Login
        // Used to login a user to the customer application.
        [HttpPost]
        [Route("Login")]
        [ResponseType(typeof(CustomerDTO))]
        public IHttpActionResult PostCUSTOMERLogin([FromBody] CustomerLoginBindingModel loginDetails)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (string.IsNullOrEmpty(loginDetails.emailAddress) || string.IsNullOrEmpty(loginDetails.password))
            {
                // Return 403 Forbidden with error message.
                HttpError err = new HttpError("Ensure that your email address and password are provided.");
                HttpResponseMessage responseMessage = Request.CreateErrorResponse(HttpStatusCode.Forbidden, err);

                return ResponseMessage(responseMessage);
            }

            // Ensure there is a user under this email address.
            if (CustomerExists(loginDetails.emailAddress))
            {
                // Get the customer record from the database that matches the email address provided.
                CUSTOMER dbCustomer = db.CUSTOMERS.SingleOrDefault(customer => customer.EMAIL_ADDRESS == loginDetails.emailAddress);

                if (dbCustomer != null && loginDetails.emailAddress.Equals(dbCustomer.EMAIL_ADDRESS))
                {
                    // TODO: Get the password hash and salt and ensure that the attempted password matches.
                    //       Implement SHA256/SHA512 hasing with a password and salt which is stored in the database.


                    // TODO: Temporarily just checking that the passwords match for now; this needs to be replaced with 
                    //       hasing/security functionality.
                    if (loginDetails.password.Equals(dbCustomer.CUSTOMER_PASSWORD))
                    {
                        // Return the user details as with the Customer Data Transfer Object.
                        CustomerDTO customerDetails = new CustomerDTO()
                        {
                            CustomerId = (int)dbCustomer.CUSTOMER_ID,
                            FirstName = dbCustomer.FIRST_NAME,
                            LastName = dbCustomer.LAST_NAME,
                            DateOfBirth = dbCustomer.DATE_OF_BIRTH,
                            AddressLineOne = dbCustomer.ADDRESS_LINE_ONE,
                            AddressLineTwo = dbCustomer.ADDRESS_LINE_TWO,
                            PostCode = dbCustomer.POSTCODE,
                            MobileNumber = dbCustomer.MOBILE_NUMBER,
                            EmailAddress = dbCustomer.EMAIL_ADDRESS
                        };

                        return Ok(customerDetails);
                    } else
                    {
                        // Return a 403 Forbidden with an error message.
                        HttpError err = new HttpError("Ensure that the email address and password are correct for the account you are trying to access.");
                        HttpResponseMessage responseMessage = Request.CreateErrorResponse(HttpStatusCode.Forbidden, err);

                        return ResponseMessage(responseMessage);
                    }
                } else
                {
                    // In the event that we cannot fetch the customer record based on the provided email address.
                    HttpError err = new HttpError("There was an error processing the login details you have provided on our side.");
                    HttpResponseMessage responseMessage = Request.CreateErrorResponse(HttpStatusCode.NotFound, err);

                    return ResponseMessage(responseMessage);
                }

            } else
            {
                // The email address the user requested is not registered to an account.
                HttpError err = new HttpError("This account is not yet registered with our service.");
                HttpResponseMessage responseMessage = Request.CreateErrorResponse(HttpStatusCode.NotFound, err);

                return ResponseMessage(responseMessage);
            }
        }


        // DELETE: api/Customers/5
        [HttpDelete]
        [Route("{id:int}")]
        [ResponseType(typeof(CUSTOMER))]
        public IHttpActionResult DeleteCUSTOMER(decimal id)
        {
            CUSTOMER cUSTOMER = db.CUSTOMERS.Find(id);
            if (cUSTOMER == null)
            {
                return NotFound();
            }

            db.CUSTOMERS.Remove(cUSTOMER);
            db.SaveChanges();

            return Ok(cUSTOMER);
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
        /// <param name="id"></param>
        /// <returns></returns>
        private bool CustomerExists(decimal id)
        {
            return db.CUSTOMERS.Count(e => e.CUSTOMER_ID == id) > 0;
        }


        /// <summary>
        /// Find if a customer record already exists given an email address.
        /// </summary>
        /// <param name="emailAddress"></param>
        /// <returns></returns>
        private bool CustomerExists(string emailAddress)
        {
            return db.CUSTOMERS.Count(e => e.EMAIL_ADDRESS == emailAddress) > 0;
        }
    }
}