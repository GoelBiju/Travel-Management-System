using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace WebsiteApplication.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult About()
        {
            ViewBag.Message = "Your application description page.";

            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "Your contact page.";

            return View();
        }

        public ActionResult Login()
        {
            ViewBag.Message = "Login";

            return View();
        }

        public ActionResult UpdateEmployeeAccount()
        {
            ViewBag.Message = "Update Employee Account";

            return View();
        }
        public ActionResult ViewEmployeeAccount()
        {
            ViewBag.Message = "View Employee Accounts";

            return View();
        }

        public ActionResult ViewCoaches()
        {
            ViewBag.Message = "View Coaches";

            return View();
        }
    }
}
