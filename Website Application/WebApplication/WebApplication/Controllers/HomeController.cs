using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace WebApplication.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            // TODO: Check to ensure that the user is already logged in, if not then re-direct to login page (sessionStorage?).

            //return View();
            return RedirectToAction("Login");
        }

        //public ActionResult About()
        //{
        //    ViewBag.Message = "Your application description page.";

        //    return View();
        //}

        public ActionResult Login()
        {
            ViewBag.Message = "Login page.";

            return View();
        }
    }
}