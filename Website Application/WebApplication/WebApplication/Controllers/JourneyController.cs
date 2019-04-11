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
using System.Web.Mvc;

namespace WebApplication.Controllers
{
    public class JourneyController : Controller
    {
        // GET: Journey
        [HttpGet]
        public ActionResult Index()
        {
            var _Data = new List<JourneyViewModel>();

            HttpResponseMessage response = GlobalVariables.WebApiClient.GetAsync("journeys").Result;
            if (response.IsSuccessStatusCode)
            {
                var JsonString = response.Content.ReadAsStringAsync().Result;
                _Data = JsonConvert.DeserializeObject<List<JourneyViewModel>>(JsonString);
            }
            return View(_Data);
        }

        // GET: Journey/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: Journey/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Journey/Create
        [HttpPost]
        public ActionResult Create(JourneyViewModel journey)
        {
            try
            {
                try
                {
                    HttpResponseMessage response = GlobalVariables.WebApiClient.PostAsJsonAsync("coaches", journey).Result;
                    return RedirectToAction("Index");
                }
                catch
                {
                    return View();
                }
            }
            catch
            {
                return View();
            }
        }

        // GET: Journey/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: Journey/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add update logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Journey/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: Journey/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
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
