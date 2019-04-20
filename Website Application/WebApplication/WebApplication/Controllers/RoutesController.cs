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
    public class RoutesController : Controller
    {
        // GET: Routes
        public ActionResult Index()
        {
            return View();
        }

        [HttpGet]
        public ActionResult Details()
        {
            var data = new List<RoutesViewModel>();

            HttpResponseMessage responseMessage = GlobalVariables.WebApiClient.GetAsync("routes").Result;
            if (responseMessage.IsSuccessStatusCode)
            {
                var JsonString = responseMessage.Content.ReadAsStringAsync().Result;
                data = JsonConvert.DeserializeObject<List<RoutesViewModel>>(JsonString);
            }

            return View(data);
        }
    }
}