using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Threading.Tasks;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using Newtonsoft.Json;
using WebApplication.Models;
using WebApplication.Utilities;
using System.Web.Mvc;

namespace WebApplication.Controllers
{
    public class EmployeeController : Controller
    {
        //https://www.tutorialsteacher.com/mvc/model-binding-in-asp.net-mvc


        // GET: Employee
        public ActionResult Index()
        {
            var _Data = new List<Employee>();
            HttpClient client = new HttpClient();
            APIConnection.RunAsync(client).Wait();

            HttpResponseMessage response = client.GetAsync("employees").Result;
            if (response.IsSuccessStatusCode)
            {
                var JsonString = response.Content.ReadAsStringAsync().Result;
                _Data = JsonConvert.DeserializeObject<List<Employee>>(JsonString);
            }

            return View(_Data);
        }

        public ActionResult Create()
        {
            return View();
        }

        [HttpPost]
        public ActionResult Create(Employee employee)
        {
            return RedirectToAction("Index");
        }

        [HttpGet]
        public ActionResult Details(string id)
        {
            return View();
        }

        [HttpPost]
        public ActionResult Edit(string id, FormCollection collection)
        {
            return View();
        }

        public ActionResult Delete(string id)
        {
            var _Data = new Employee();
            HttpClient client = new HttpClient();
            APIConnection.RunAsync(client).Wait();

            HttpResponseMessage response = client.GetAsync("employees" + id).Result;
            if (response.IsSuccessStatusCode)
            {
                var JsonString = response.Content.ReadAsStringAsync().Result;
                _Data = JsonConvert.DeserializeObject<Employee>(JsonString);
            }
            return View(_Data);
        }

        [HttpPost]
        public ActionResult Delete(string id, FormCollection collection)
        {
            try
            {
                // TODO: Add delete logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}