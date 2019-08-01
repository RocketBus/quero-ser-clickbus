using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Web;
using System.Web.Mvc;
using brProg.Models;

namespace brProg.Controllers
{
    public class DeslogarPartialController : Controller
    {
        [HttpGet]
        public ActionResult DeslogarPartial()
        {
           
        if (Session["usuarioLogadoID"] != null)
            {
                Session.Clear();
                return RedirectToAction("Login", "Login");
            }
            else
            {
                return RedirectToAction("Login", "Login");
            }
        }
    }
}