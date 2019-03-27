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
                if (!EMPLOYEEExists(id))
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
        [ResponseType(typeof(EMPLOYEE))]
        public IHttpActionResult PostEMPLOYEE(EMPLOYEE eMPLOYEE)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.EMPLOYEES.Add(eMPLOYEE);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (EMPLOYEEExists(eMPLOYEE.EMPLOYEE_ID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = eMPLOYEE.EMPLOYEE_ID }, eMPLOYEE);
        }

        //Login to web app for admin employees:
        [HttpPost]
        [Route("webLogin")]
        [ResponseType(typeof(EmployeeDTO))]
        public IHttpActionResult employeeLogin([FromBody] EmployeeLoginBindingModel loginCredentials)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (string.IsNullOrEmpty(loginCredentials.employeeID) || string.IsNullOrEmpty(loginCredentials.password))
            {
                HttpError errorNoInput = new HttpError("Please enter a valid Employee ID and Password.");
                HttpResponseMessage responseNoInput = Request.CreateErrorResponse(HttpStatusCode.Forbidden, errorNoInput);

                return ResponseMessage(responseNoInput);
            }

            if (EMPLOYEEExists(loginCredentials.employeeID))
            {
                EMPLOYEE employeeDb = db.EMPLOYEES.SingleOrDefault(employee => employee.EMPLOYEE_ID == loginCredentials.employeeID);

                if (employeeDb != null && loginCredentials.employeeID == employeeDb.EMPLOYEE_ID)
                {
                    if (loginCredentials.password == employeeDb.EMPLOYEE_PASSWORD)
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
        [ResponseType(typeof(EMPLOYEE))]
        public IHttpActionResult DeleteEMPLOYEE(string id)
        {
            EMPLOYEE eMPLOYEE = db.EMPLOYEES.Find(id);
            if (eMPLOYEE == null)
            {
                return NotFound();
            }

            db.EMPLOYEES.Remove(eMPLOYEE);
            db.SaveChanges();

            return Ok(eMPLOYEE);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool EMPLOYEEExists(string id)
        {
            return db.EMPLOYEES.Count(e => e.EMPLOYEE_ID == id) > 0;
        }
    }
}