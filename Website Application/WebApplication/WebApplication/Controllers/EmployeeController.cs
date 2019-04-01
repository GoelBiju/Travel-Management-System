using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Threading.Tasks;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using Newtonsoft.Json;
using WebApplication.ViewModels;
using WebApplication.Utilities;
using System.Web.Mvc;

namespace WebApplication.Controllers
{
    public class EmployeeController : Controller
    {
        //https://www.tutorialsteacher.com/mvc/model-binding-in-asp.net-mvc

        // GET: Employee/Details
        [HttpGet]
        public ActionResult Details()
        {
            var _Data = new List<EmployeeViewModel>();
            //HttpClient client = new HttpClient();
            //APIConnection.RunAsync(client).Wait();

            HttpResponseMessage response = GlobalVariables.WebApiClient.GetAsync("employees").Result;
            if (response.IsSuccessStatusCode)
            {
                var JsonString = response.Content.ReadAsStringAsync().Result;
                _Data = JsonConvert.DeserializeObject<List<EmployeeViewModel>>(JsonString);
            }

            return View(_Data);
        }

        [HttpGet]
        public ActionResult Create(string id = "")
        {
            return View(new EmployeeViewModel());
        }

        [HttpPost]
        public ActionResult Create(EmployeeViewModel employee)
        {
            HttpResponseMessage response = GlobalVariables.WebApiClient.PostAsJsonAsync("employees", employee).Result;
            //if (response.IsSuccessStatusCode)
            //{
            //    TempData["SuccessMessage"] = "Created Successfully";
            //}

            return RedirectToAction("Details");
        }

        [HttpGet]
        public ActionResult Edit(string id = "")
        {
            EmployeeViewModel employeeData = null;

            if (string.IsNullOrEmpty(id))
            {
                // GET the employee details using the id.

            }

            return View(employeeData);
        }

        [HttpPost]
        public ActionResult Edit(EmployeeViewModel employee)
        {
            // HTTP PUT request with the updated employee object in the JSON request body.

            return RedirectToAction("Details");
        }

        [HttpGet]
        public ActionResult Delete(string id)
        {
            // TODO: Check id is not null.
            if (string.IsNullOrEmpty(id))
            {
                return RedirectToAction("Details");
            }

            var _Data = new EmployeeViewModel();

            HttpResponseMessage response = GlobalVariables.WebApiClient.GetAsync("employees/" + id).Result;
            if (response.IsSuccessStatusCode)
            {
                var JsonString = response.Content.ReadAsStringAsync().Result;
                _Data = JsonConvert.DeserializeObject<EmployeeViewModel>(JsonString);
            }
            return View(_Data);
        }

        [HttpPost, ActionName("Delete")]
        public ActionResult DeleteConfirmed(string id)
        {
            try
            {
                HttpResponseMessage response = GlobalVariables.WebApiClient.DeleteAsync("employees/" + id).Result;
                if (response.IsSuccessStatusCode)
                {
                    return RedirectToAction("Details");
                }

                return View();
                
            }
            catch
            {
                return View();
            }
        }
    }
}