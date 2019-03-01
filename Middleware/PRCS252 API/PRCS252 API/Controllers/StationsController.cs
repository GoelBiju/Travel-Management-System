using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using PRCS252_API.Models;


namespace PRCS252_API.Controllers
{
    public class StationsController : Controller
    {
        private Models.Database db = new Models.Database();

        // GET: Stations
        public ActionResult Index()
        {
            return View(db.STATIONS.ToList());
        }

        // GET: Stations/Details/5
        public ActionResult Details(decimal id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            STATION sTATION = db.STATIONS.Find(id);
            if (sTATION == null)
            {
                return HttpNotFound();
            }
            return View(sTATION);
        }

        // GET: Stations/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Stations/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "STATION_ID,STATION_NAME")] STATION sTATION)
        {
            if (ModelState.IsValid)
            {
                db.STATIONS.Add(sTATION);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(sTATION);
        }

        // GET: Stations/Edit/5
        public ActionResult Edit(decimal id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            STATION sTATION = db.STATIONS.Find(id);
            if (sTATION == null)
            {
                return HttpNotFound();
            }
            return View(sTATION);
        }

        // POST: Stations/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "STATION_ID,STATION_NAME")] STATION sTATION)
        {
            if (ModelState.IsValid)
            {
                db.Entry(sTATION).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(sTATION);
        }

        // GET: Stations/Delete/5
        public ActionResult Delete(decimal id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            STATION sTATION = db.STATIONS.Find(id);
            if (sTATION == null)
            {
                return HttpNotFound();
            }
            return View(sTATION);
        }

        // POST: Stations/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(decimal id)
        {
            STATION sTATION = db.STATIONS.Find(id);
            db.STATIONS.Remove(sTATION);
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
