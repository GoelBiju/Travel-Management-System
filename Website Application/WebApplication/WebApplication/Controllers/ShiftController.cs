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
    public class ShiftController : Controller
    {
        // GET: Shift
        [HttpGet]
        public ActionResult Details()
        {
            var _Data = new List<ShiftViewModel>();


            HttpResponseMessage response = GlobalVariables.WebApiClient.GetAsync("shifts").Result;
            if (response.IsSuccessStatusCode)
            {
                var JsonString = response.Content.ReadAsStringAsync().Result;
                _Data = JsonConvert.DeserializeObject<List<ShiftViewModel>>(JsonString);
            }


            return View(_Data);
        }

        [HttpGet]
        public ActionResult Delete(int id)
        {
            if (string.IsNullOrEmpty(id.ToString()))
            {
                return RedirectToAction("Details");
            }

            var _Data = new ShiftViewModel();

            HttpResponseMessage response = GlobalVariables.WebApiClient.GetAsync("shifts/" + id).Result;
            if (response.IsSuccessStatusCode)
            {
                var JsonString = response.Content.ReadAsStringAsync().Result;
                _Data = JsonConvert.DeserializeObject<ShiftViewModel>(JsonString);
            }
            return View(_Data);
        }


    }
}
