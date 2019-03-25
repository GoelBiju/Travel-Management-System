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

        [HttpGet]
        public ActionResult GetById(int id)
        {
            return View();
        }

        [HttpPost]
        public ActionResult Edit(int id, FormCollection collection)
        {
            return View();
        }
    }
}