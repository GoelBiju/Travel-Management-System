using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.Mvc;
using WebApplication.ViewModels;

namespace WebApplication.Controllers
{
    public class StopController : Controller
    {
        // GET: Stop
        public ActionResult Index()
        {
            var _Data = new List<StopViewModel>();

            HttpResponseMessage response = GlobalVariables.WebApiClient.GetAsync("stops").Result;
            if (response.IsSuccessStatusCode)
            {
                var JsonString = response.Content.ReadAsStringAsync().Result;
                _Data = JsonConvert.DeserializeObject<List<StopViewModel>>(JsonString);
            }

            return View(_Data);
        }
        
        // GET: Stop/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: Stop/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Stop/Create
        [HttpPost]
        public ActionResult Create(StopViewModel stop)
        {
            try
            {
                try
                {
                    HttpResponseMessage response = GlobalVariables.WebApiClient.PostAsJsonAsync("stops", stop).Result;
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

        // GET: Stop/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: Stop/Edit/5
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

        // GET: Stop/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: Stop/Delete/5
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
