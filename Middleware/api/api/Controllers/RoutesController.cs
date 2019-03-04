using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using api.Models;

namespace api.Controllers
{
    public class RoutesController : Controller
    {
        private Entities db = new Entities();

        // GET: Routes
        public ActionResult Index()
        {
            return View(db.ROUTES.ToList());
        }

        // GET: Routes/Details/5
        public ActionResult Details(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            ROUTE rOUTE = db.ROUTES.Find(id);
            if (rOUTE == null)
            {
                return HttpNotFound();
            }
            return View(rOUTE);
        }

        // GET: Routes/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Routes/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "ROUTE_ID,START_DESTINATION,END_DESTINATION")] ROUTE rOUTE)
        {
            if (ModelState.IsValid)
            {
                db.ROUTES.Add(rOUTE);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(rOUTE);
        }

        // GET: Routes/Edit/5
        public ActionResult Edit(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            ROUTE rOUTE = db.ROUTES.Find(id);
            if (rOUTE == null)
            {
                return HttpNotFound();
            }
            return View(rOUTE);
        }

        // POST: Routes/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "ROUTE_ID,START_DESTINATION,END_DESTINATION")] ROUTE rOUTE)
        {
            if (ModelState.IsValid)
            {
                db.Entry(rOUTE).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(rOUTE);
        }

        // GET: Routes/Delete/5
        public ActionResult Delete(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            ROUTE rOUTE = db.ROUTES.Find(id);
            if (rOUTE == null)
            {
                return HttpNotFound();
            }
            return View(rOUTE);
        }

        // POST: Routes/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(string id)
        {
            ROUTE rOUTE = db.ROUTES.Find(id);
            db.ROUTES.Remove(rOUTE);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
