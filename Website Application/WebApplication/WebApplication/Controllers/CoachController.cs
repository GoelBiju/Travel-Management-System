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

            HttpResponseMessage response = GlobalVariables.WebApiClient.GetAsync("coaches/" + id.ToString()).Result;
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
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return RedirectToAction("Index");
            }

            // Get the coach information from the API based on the coach ID.

            CoachViewModel data = new CoachViewModel();

            HttpResponseMessage message = GlobalVariables.WebApiClient.GetAsync("coaches/" + id.ToString()).Result;

            if (message.IsSuccessStatusCode)
            {
                var JsonString = message.Content.ReadAsStringAsync().Result;
                data = JsonConvert.DeserializeObject<CoachViewModel>(JsonString);
            }

            return View(data);
        }

        // POST: Coach/Edit/5
        [HttpPost]
        public ActionResult Edit(CoachViewModel updatedCoach)
        {
            try
            {
                // TODO: Add update logic to send a PUT request with the updated coach information.
                HttpResponseMessage message = GlobalVariables.WebApiClient.PutAsJsonAsync("coaches/" + updatedCoach.CoachId, updatedCoach).Result;

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Coach/Delete/5
        [HttpGet]
        public ActionResult Delete(int id)
        {
            if (string.IsNullOrEmpty(id.ToString()))
            {
                return RedirectToAction("Details");
            }

            var _Data = new CoachViewModel();

            HttpResponseMessage response = GlobalVariables.WebApiClient.GetAsync("coaches/" + id).Result;
            if (response.IsSuccessStatusCode)
            {
                var JsonString = response.Content.ReadAsStringAsync().Result;
                _Data = JsonConvert.DeserializeObject<CoachViewModel>(JsonString);
            }

            return View(_Data);
        }

        // POST: Coach/Delete/5
        [HttpPost, ActionName("Delete")]
        public ActionResult DeleteConfirmed(int id)
        {
            try
            {
                HttpResponseMessage response = GlobalVariables.WebApiClient.DeleteAsync("coaches/" + id).Result;
                if (response.IsSuccessStatusCode)
                {
                    return RedirectToAction("Index");
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
