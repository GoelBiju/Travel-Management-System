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
using api.Utilities;

namespace api.Controllers
{
    [Authorize(Roles = "Employee")]
    [RoutePrefix("api/employees")]
    public class EmployeesController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/Employees
        [HttpGet]
        [Route("")]
        public IQueryable<EmployeeDTO> GetEMPLOYEES()
        {
            var employees = from e in db.EMPLOYEES
                            select new EmployeeDTO()
                            {
                                EmployeeId = e.EMPLOYEE_ID,
                                FirstName = e.FIRST_NAME,
                                LastName = e.LAST_NAME,
                                JobRole = e.JOB_ROLE
                            };

            return employees;
        }

        // GET: api/Employees/D1234
        [HttpGet]
        [Route("{id}", Name = "GetEmployeeDetailsById)")]
        [ResponseType(typeof(EmployeeDTO))]
        public async Task<IHttpActionResult> GetEMPLOYEE(string id)
        {
            //EMPLOYEE eMPLOYEE = db.EMPLOYEES.Find(id);

            var employee = await db.EMPLOYEES.Select(e =>
                new EmployeeDTO()
                {
                    EmployeeId = e.EMPLOYEE_ID,
                    FirstName = e.FIRST_NAME,
                    LastName = e.LAST_NAME,
                    JobRole = e.JOB_ROLE
                }).SingleOrDefaultAsync(e => e.EmployeeId == id);

            
            if (employee == null)
            {
                return NotFound();
            }

            return Ok(employee);
        }

        // PUT: api/Employees/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutEMPLOYEE(string id, EMPLOYEE eMPLOYEE)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != eMPLOYEE.EMPLOYEE_ID)
            {
                return BadRequest();
            }

            db.Entry(eMPLOYEE).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!EmployeeExists(id))
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

        // POST: api/Employees
        [HttpPost]
        [Route("")]
        [ResponseType(typeof(void))]
        public IHttpActionResult PostEMPLOYEE([FromBody] EmployeeCreationBindingModel employee)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            //
            string generatedSalt = Security.CreateSalt(32);
            string hashedPassword = Security.GenerateSHA256Hash(employee.Password, generatedSalt);

            // Create EMPLOYEE object from the object we received.
            EMPLOYEE addEmployee = new EMPLOYEE()
            {
                EMPLOYEE_ID = "",
                FIRST_NAME = employee.FirstName,
                LAST_NAME = employee.LastName,
                JOB_ROLE = employee.JobRole,
                EMPLOYEE_HASHED_PASSWORD = hashedPassword,
                PASSWORD_SALT = generatedSalt
            };


            db.EMPLOYEES.Add(addEmployee);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                //if (EMPLOYEEExists(addEmployee.EMPLOYEE_ID))
                //{
                //    return Conflict();
                //}
                //else
                //{
                // throw;
                //}

                return BadRequest();
            }

            // TODO: Get the added employee information, this will require attributes e.g. email to uniquely identify 
            //       other than the primary key.

            //return CreatedAtRoute("DefaultApi", new { id = eMPLOYEE.EMPLOYEE_ID }, eMPLOYEE);
            HttpResponseMessage responseMessage = Request.CreateResponse(HttpStatusCode.Created, employee);
            return ResponseMessage(responseMessage);
        }

        //Login to web app for admin employees:
        [AllowAnonymous]
        [HttpPost]
        [Route("login")]
        [ResponseType(typeof(EmployeeDTO))]
        public IHttpActionResult employeeLogin([FromBody] EmployeeLoginBindingModel loginCredentials)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (string.IsNullOrEmpty(loginCredentials.EmployeeId) || string.IsNullOrEmpty(loginCredentials.Password))
            {
                HttpError errorNoInput = new HttpError("Please enter a valid Employee ID and Password.");
                HttpResponseMessage responseNoInput = Request.CreateErrorResponse(HttpStatusCode.Forbidden, errorNoInput);

                return ResponseMessage(responseNoInput);
            }

            if (EmployeeExists(loginCredentials.EmployeeId))
            {
                EMPLOYEE employeeDb = db.EMPLOYEES.SingleOrDefault(employee => employee.EMPLOYEE_ID == loginCredentials.EmployeeId);

                if (employeeDb != null && loginCredentials.EmployeeId == employeeDb.EMPLOYEE_ID)
                {
                    string attemptedPasswordHash = Security.GenerateSHA256Hash(loginCredentials.Password, employeeDb.PASSWORD_SALT);

                    if (employeeDb.EMPLOYEE_HASHED_PASSWORD.Equals(attemptedPasswordHash))
                    {
                        EmployeeDTO employeeDetails = new EmployeeDTO()
                        {
                            EmployeeId = employeeDb.EMPLOYEE_ID,
                            FirstName = employeeDb.FIRST_NAME,
                            LastName = employeeDb.LAST_NAME,
                            JobRole = employeeDb.JOB_ROLE
                        };

                        //SUCCESS
                        return Ok(employeeDetails);
                    }
                    else
                    {
                        //403 Forbidden
                        HttpError forbiddenError = new HttpError("Incorrect email and or password.");
                        HttpResponseMessage forbiddenResponse = Request.CreateErrorResponse(HttpStatusCode.Forbidden, forbiddenError);

                        return Ok(forbiddenResponse);
                    }
                }
            }
            //Not found within database:

            HttpError errorNotFound = new HttpError("Error finding account.");
            HttpResponseMessage responseNotFound = Request.CreateErrorResponse(HttpStatusCode.NotFound, errorNotFound);

            return ResponseMessage(responseNotFound);
        }

        // DELETE: api/Employees/5
        [HttpDelete]
        [Route("{id}", Name = "DeleteEmployeeById")]
        [ResponseType(typeof(void))]
        public IHttpActionResult DeleteEMPLOYEE(string id)
        {
            EMPLOYEE employee = db.EMPLOYEES.Find(id);
            if (employee == null)
            {
                return NotFound();
            }

            // Remove the employee from the employees table but add them
            // to the archive employees table.
            db.EMPLOYEES.Remove(employee);
            db.SaveChanges();

            return Ok(employee);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool EmployeeExists(string id)
        {
            return db.EMPLOYEES.Count(e => e.EMPLOYEE_ID == id) > 0;
        }
    }
}