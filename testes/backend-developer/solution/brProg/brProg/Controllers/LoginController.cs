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
    public class LoginController : Controller
    {
        public ActionResult Login()
        {
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Login(LoginViewModel u)
        {
            ModelState.Remove("Nome");

            ModelState.Remove("Permissoes"); 
            // esta action trata o post (login)
            if (ModelState.IsValid) //verifica se é válido
            {
                using (var db = new MyContext())
                {
                    var v = db.Login.Where(p => p.Usuario.Equals(u.Usuario)).FirstOrDefault();
                    if (v != null)
                    {
                        string hashSenha = Hash(v.unknown + u.Senha);
                        if (v.Senha.Equals(hashSenha))
                        {
                            Session["usuarioLogadoID"] = v.id.ToString();
                            Session["nomeUsuarioLogado"] = v.Nome.ToString();
                            Session["tipoUsu"] = v.Permissoes.ToString();
                            return RedirectToAction("ListarTodosEventos", "Painel");
                        }
                        else
                        {
                            ViewBag.mensagem = "Usuario ou Senha Ínvalido!";
                        }
                    }
                    else
                    {
                        ViewBag.mensagem = "Usuario ou Senha Ínvalido!";

                    }
                }
            }
            return View();
        }

        public static string Hash(string input)
        {
            var hash = (new SHA1Managed()).ComputeHash(Encoding.UTF8.GetBytes(input));
            return string.Join("", hash.Select(b => b.ToString("x2")).ToArray());
        }
    }
}