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
    public class CoachArchiveController : Controller
    {
        // GET: CoachArchive
        public ActionResult Index()
        {
            var coachData = new List<CoachViewModel>();

            HttpResponseMessage response = GlobalVariables.WebApiClient.GetAsync("archive/coaches").Result;
            if (response.IsSuccessStatusCode)
            {
                var JsonString = response.Content.ReadAsStringAsync().Result;
                coachData = JsonConvert.DeserializeObject<List<CoachViewModel>>(JsonString);
            }


            return View(coachData);
        }

        // GET: CoachArchive/Details/5
        //public ActionResult Details(int id)
        //{
        //    return View();
        //}

        // GET: CoachArchive/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: CoachArchive/Create
        [HttpPost]
        public ActionResult Create(FormCollection collection)
        {
            try
            {
                // TODO: Add insert logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: CoachArchive/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: CoachArchive/Edit/5
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

        // GET: CoachArchive/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: CoachArchive/Delete/5
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
