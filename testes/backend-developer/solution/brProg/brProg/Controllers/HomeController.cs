using brProg.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Web;
using System.Web.Mvc;

namespace brProg.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
                using (var db = new MyContext())
                {
                    ViewBag.parametro = "Nossa agenda hoje!";
                    return View(db.Agenda.ToList());
                }
        }
    }
}