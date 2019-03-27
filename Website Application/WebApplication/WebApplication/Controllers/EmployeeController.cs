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


        // GET: Employee/Index
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

        public ActionResult CreateOrEdit(string id = "")
        {
            return View(new EmployeeViewModel());
        }

        [HttpPost]
        public ActionResult CreateOrEdit()
        {
            //HttpResponseMessage response = 
            return View();
        }


        //public ActionResult Create()
        //{
        //    return View();
        //}

        //[HttpPost]
        //public ActionResult Create(EmployeeViewModel employee)
        //{
        //    return RedirectToAction("Details");
        //}

        //[HttpPost]
        //public ActionResult Edit(string id, FormCollection collection)
        //{
        //    return View();
        //}

        public ActionResult Delete(string id)
        {
            // TODO: Check id is not null.
            if (string.IsNullOrEmpty(id))
            {
                return RedirectToAction("Details");
            }

            var _Data = new EmployeeViewModel();
            //HttpClient client = new HttpClient();
            //APIConnection.RunAsync(client).Wait();

            HttpResponseMessage response = GlobalVariables.WebApiClient.GetAsync("employees/" + id).Result;
            if (response.IsSuccessStatusCode)
            {
                var JsonString = response.Content.ReadAsStringAsync().Result;
                _Data = JsonConvert.DeserializeObject<EmployeeViewModel>(JsonString);
            }
            return View(_Data);
        }

        [HttpDelete]
        public ActionResult Delete(string id, FormCollection collection)
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