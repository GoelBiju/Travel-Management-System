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
    public class RouteStopsController : Controller
    {
        // GET: RouteStops
        public ActionResult Details(int? id)
        {
            if (id != null)
            {
                ViewBag.RouteId = id;
                var data = new List<RouteStopViewModel>();

                HttpResponseMessage responseMessage = GlobalVariables.WebApiClient.GetAsync("routestops/" + id).Result;
                if (responseMessage.IsSuccessStatusCode)
                {
                    var jsonString = responseMessage.Content.ReadAsStringAsync().Result;
                    data = JsonConvert.DeserializeObject<List<RouteStopViewModel>>(jsonString);
                }

                return View(data);
            }

            return RedirectToAction("Details", "Routes");
        }

        // GET: RouteStops/Create
        //public ActionResult Create()
        //{
        //    return View();
        //}

        // POST: RouteStops/Create
        //[HttpPost]
        //public ActionResult Create(FormCollection collection)
        //{
        //    try
        //    {
        //        // TODO: Add insert logic here

        //        return RedirectToAction("Index");
        //    }
        //    catch
        //    {
        //        return View();
        //    }
        //}

        // GET: RouteStops/Edit/5
        //public ActionResult Edit(int id)
        //{
        //    return View();
        //}

        // POST: RouteStops/Edit/5
        //[HttpPost]
        //public ActionResult Edit(int id, FormCollection collection)
        //{
        //    try
        //    {
        //        // TODO: Add update logic here

        //        return RedirectToAction("Index");
        //    }
        //    catch
        //    {
        //        return View();
        //    }
        //}

        // GET: RouteStops/Delete/5
        public ActionResult Delete(int? id, int stopId)
        {
            if (id == null)
            {
                return RedirectToAction("Details", "RouteStops");
            }

            var data = new RouteStopViewModel();

            HttpResponseMessage response = GlobalVariables.WebApiClient.GetAsync("routestops/" + id + "/" + stopId).Result;
            if (response.IsSuccessStatusCode)
            {
                var jsonString = response.Content.ReadAsStringAsync().Result;
                data = JsonConvert.DeserializeObject<RouteStopViewModel>(jsonString);
            }

            ViewBag.RouteId = id;

            return View(data);
        }

        // POST: RouteStops/Delete/5
        [HttpPost]
        public ActionResult Delete(RouteStopViewModel deleteRouteStop)
        {
            try
            {
                HttpResponseMessage response = GlobalVariables.WebApiClient.PostAsJsonAsync("routestops/Delete", deleteRouteStop).Result;
                if (response.IsSuccessStatusCode)
                {
                    return RedirectToAction("Details/" + deleteRouteStop.RouteId);
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
