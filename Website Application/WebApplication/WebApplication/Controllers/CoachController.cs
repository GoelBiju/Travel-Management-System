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
    public class CoachController : Controller
    {
        // GET: Coach
        public ActionResult Index()
        {
            var _Data = new List<CoachViewModel>();

            HttpResponseMessage response = GlobalVariables.WebApiClient.GetAsync("coaches").Result;
            if (response.IsSuccessStatusCode)
            {
                var JsonString = response.Content.ReadAsStringAsync().Result;
                _Data = JsonConvert.DeserializeObject<List<CoachViewModel>>(JsonString);
            }

            return View(_Data);
        }

        // GET: Coach/Details/5
        public ActionResult Details(int id) 
        {

            var _Data = new CoachViewModel();

            HttpResponseMessage response = GlobalVariables.WebApiClient.GetAsync("coaches/" + id).Result;
            if (response.IsSuccessStatusCode)
            {
                var JsonString = response.Content.ReadAsStringAsync().Result;
                _Data = JsonConvert.DeserializeObject<CoachViewModel>(JsonString);
            }
            return View(_Data); 

        }

        // GET: Coach/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Coach/Create
        [HttpPost]
        public ActionResult Create(CoachViewModel coach)
        {
            try
            {
                HttpResponseMessage response = GlobalVariables.WebApiClient.PostAsJsonAsync("coaches", coach).Result;

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Coach/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: Coach/Edit/5
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

        // GET: Coach/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: Coach/Delete/5
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
