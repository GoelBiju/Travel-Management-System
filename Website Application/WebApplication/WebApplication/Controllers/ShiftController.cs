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
        public ActionResult Index()
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

        //GET: Shift/Details/shiftId
        public ActionResult Details(int id)
        {
            var _Data = new CoachViewModel();

            HttpResponseMessage response = GlobalVariables.WebApiClient.GetAsync("shifts/" + id.ToString()).Result;
            if (response.IsSuccessStatusCode)
            {
                var JsonString = response.Content.ReadAsStringAsync().Result;
                _Data = JsonConvert.DeserializeObject<CoachViewModel>(JsonString);
            }
            return View(_Data);
        }
    }
}
