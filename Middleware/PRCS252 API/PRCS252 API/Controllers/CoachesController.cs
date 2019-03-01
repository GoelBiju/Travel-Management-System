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
    public class CoachesController : Controller
    {
        private Database db = new Database();

        // GET: Coaches
        public ActionResult Index()
        {
            return View(db.COACHES.ToList());
        }

        // GET: Coaches/Details/5
        public ActionResult Details(decimal id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            COACH cOACH = db.COACHES.Find(id);
            if (cOACH == null)
            {
                return HttpNotFound();
            }
            return View(cOACH);
        }

        // GET: Coaches/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Coaches/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "COACH_ID,COACH_MAKE,COACH_MODEL,REGISTRATION_PLATE,CAPACITY")] COACH cOACH)
        {
            if (ModelState.IsValid)
            {
                db.COACHES.Add(cOACH);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(cOACH);
        }

        // GET: Coaches/Edit/5
        public ActionResult Edit(decimal id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            COACH cOACH = db.COACHES.Find(id);
            if (cOACH == null)
            {
                return HttpNotFound();
            }
            return View(cOACH);
        }

        // POST: Coaches/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "COACH_ID,COACH_MAKE,COACH_MODEL,REGISTRATION_PLATE,CAPACITY")] COACH cOACH)
        {
            if (ModelState.IsValid)
            {
                db.Entry(cOACH).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(cOACH);
        }

        // GET: Coaches/Delete/5
        public ActionResult Delete(decimal id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            COACH cOACH = db.COACHES.Find(id);
            if (cOACH == null)
            {
                return HttpNotFound();
            }
            return View(cOACH);
        }

        // POST: Coaches/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(decimal id)
        {
            COACH cOACH = db.COACHES.Find(id);
            db.COACHES.Remove(cOACH);
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
