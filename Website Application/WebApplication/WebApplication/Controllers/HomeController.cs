using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.Mvc;
using WebApplication.ViewModels;

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

        [HttpPost]
        public ActionResult Login(EmployeeLoginViewModel employee)
        {
            try
            {
                //ViewData["username"] = employee.EmployeeID;
                //ViewData["password"] = employee.Password;

                //HttpResponseMessage message = GlobalVariables.WebApiClient.PostAsJsonAsync("employees/login", employee).Result;
                bool loginSuccess = GlobalVariables.WebLogin(employee.EmployeeID, employee.Password);
                //int status = (int) message.StatusCode;

                //if (status >= 200 && status < 300)
                if (loginSuccess)
                {
                    return RedirectToAction("Details", "Employee");
                }

                return View();
            }
            catch
            {
                return View();
            }
        }

        [HttpGet]
        public ActionResult SignOut()
        {
            GlobalVariables.ResetHeaders();
            return RedirectToAction("Login", "Home");
        }
    }
}