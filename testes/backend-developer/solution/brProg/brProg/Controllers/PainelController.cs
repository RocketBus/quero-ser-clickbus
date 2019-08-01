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
    public class PainelController : Controller
    {
        public ActionResult CadUsu()
        {
            if (Session["usuarioLogadoID"] != null)
            {
                if (Session["tipoUsu"].ToString() == "A")
                {
                    return View();
                }else
                {
                    return RedirectToAction("ListarTodosEventos", "Painel");
                }
                
            }
            else
            {
                return RedirectToAction("Login", "Login");
            }
        }

        public ActionResult CadastrarEvento()
        {
            if (Session["usuarioLogadoID"] != null)
            {
                return View();
            }
            else
            {
                return RedirectToAction("Login", "Login");
            }
        }

        public ActionResult ListarTodosEventos()
        {
            if (Session["usuarioLogadoID"] != null)
            {
                using (var db = new MyContext())
                {
                    DateTime localDate = DateTime.Now;
                    var valorDia = localDate.ToString("dd/MM/yyyy"); 
                    ViewBag.parametro = "Agendamentos de Hoje!";

                    AgendaViewModel agenda = new AgendaViewModel();
                    agenda.Agenda = db.Agenda.Where(x => x.DataIni.Contains(valorDia)).ToList();

                    if (agenda.Agenda.Count() > 0)
                    {
                        agenda.Agenda = agenda.Agenda.OrderBy(c => c.DataIni.ToString()).ThenBy(c => c.DataIni);
                        return View(agenda);
                    }
                    else
                    {

                        ViewBag.parametro = "Nenhum agendamento Hoje, listaremos os próximos a baixo!";
                        agenda.Agenda = db.Agenda.ToList().Where(x => Convert.ToDateTime(x.DataIni) > Convert.ToDateTime(valorDia));
                        agenda.Agenda = agenda.Agenda.OrderBy(c => c.DataIni.ToString()).ThenBy(c => c.DataIni);
                        if (agenda.Agenda.Count() == 0)
                        {
                            ViewBag.parametro = "Não encontramos nenhum registro!";
                            agenda.Agenda = new List<Agenda>();
                            return View(agenda);
                        }
                        else
                        {
                            return View(agenda);
                        }                        
                    }
                    
                   
                }
            }
            else
            {
                return RedirectToAction("Login", "Login");
            }
        }

        [HttpPost]
        public ActionResult CadUsu(LoginViewModel login)
        {
            if (Session["usuarioLogadoID"] != null && Session["tipoUsu"].ToString() == "A")
            {
                Login loginPessoa = new Login();

                loginPessoa.Nome = login.Nome;
                loginPessoa.Permissoes = login.Permissoes;
                loginPessoa.Usuario = login.Usuario;

                var buffer = new byte[10];
                new Random().NextBytes(buffer);
                var password = login.Senha;
                var salt = System.Convert.ToBase64String(buffer);

                loginPessoa.unknown = salt;
                loginPessoa.Senha = Hash(salt + password);

                if (ModelState.IsValid)
                {
                    using (var db = new MyContext())
                    {
                        var EntrarSistema = db.Login.Where(x => x.Usuario == login.Usuario).SingleOrDefault();
                        if (EntrarSistema == null)
                        {
                            db.Login.Add(loginPessoa);
                            db.SaveChanges();
                            ModelState.Clear();
                            ViewBag.Message = "Usuário registrado com sucesso.";

                        }
                        else
                        {
                            ViewBag.Message = "Usuario já cadastrado, porfavor tente outro!";
                        }
                    }
                }
                return View();
            }else
            {
                return RedirectToAction("Login", "Login");
            }
        }

        [HttpPost]
        public ActionResult CadastrarEvento(AgendaViewModel evento)
        {
            if (Session["usuarioLogadoID"] != null)
            {
                Agenda agendaCad = new Agenda();

                agendaCad.DataIni = evento.DataIni;
                agendaCad.Descricao = evento.Descricao;
                agendaCad.Telefone = evento.Telefone;
                agendaCad.NomeCliente = evento.NomeCliente;

                if (ModelState.IsValid)
                {
                    using (var db = new MyContext())
                    {
                        var EntrarSistema = db.Agenda.Where(x => x.DataIni == evento.DataIni).SingleOrDefault();
                        if (EntrarSistema == null)
                        {
                            db.Agenda.Add(agendaCad);
                            db.SaveChanges();
                            ModelState.Clear();
                            ViewBag.Message = "Agendamento realizado com sucesso!";

                        }
                        else
                        {
                            ViewBag.Message = "Data ou Horário Indisponivel.!";
                        }
                    }
                }
                return View();
            }
            else
            {
                return RedirectToAction("Login", "Login");
            }
        }

        public static string Hash(string input)
        {
            var hash = (new SHA1Managed()).ComputeHash(Encoding.UTF8.GetBytes(input));
            return string.Join("", hash.Select(b => b.ToString("x2")).ToArray());
        }

        public ActionResult Deletar(int id)
        {
            if (Session["usuarioLogadoID"] != null && Session["tipoUsu"].ToString() == "A" )
            {
                using (var db = new MyContext())
                {
                    var controle = db.Agenda.Where(x => x.id == id).SingleOrDefault();
                    db.Agenda.Remove(controle);
                    db.SaveChanges();
                }
            }
            else
            {
                return RedirectToAction("ListarTodosEventos", "Painel");
            }
            return RedirectToAction("ListarTodosEventos", "Painel");
        }

        [HttpPost]
        public ActionResult ListarTodosEventos(AgendaViewModel agenda)
        {
            string nome = agenda.NomeCliente;
            string data = agenda.DataIni;

            if (nome == null || nome == "")
            {
                if (data == null || data == "")
                {
                    return RedirectToAction("ListarTodosEventos", "Painel");
                }
                else
                {
                    using (var db = new MyContext())
                    {
                        
                        agenda.Agenda = db.Agenda.Where(x => x.DataIni.Contains(data)).ToList();
                        if (agenda.Agenda.Count() == 0)
                        {
                            ViewBag.parametro = "Pesquisado pela Data: " + data;
                            ViewBag.nada = "Nenhum agendamento realizado nesta data, listaremos todos a baixo.";
                            return View(db.Agenda.ToList());
                        }
                        else
                        {
                            ViewBag.parametro = "Pesquisado pela Data: " + data;
                            agenda.Agenda.OrderBy(c => c.DataIni.ToString()).ThenBy(c => c.DataIni);
                            return View(agenda);
                        }
                    }
                }
            }
            else
            {
                using (var db = new MyContext())
                {
                    agenda.Agenda = db.Agenda.Where(x => x.NomeCliente.Contains(nome)).ToList();
                    if (agenda.Agenda.Count() == 0)
                    {
                        ViewBag.parametro = "Pesquisado por: " + nome;
                        ViewBag.nada = "Nenhum agendamento realizado com este nome, listaremos todos a baixo.";
                        return View(db.Agenda.ToList());
                    }
                    else
                    {
                        ViewBag.parametro = "Pesquisado por: " + nome;
                        agenda.Agenda.OrderBy(c => c.DataIni.ToString()).ThenBy(c => c.DataIni);
                        return View(agenda);
                    }
                }
            }
        }
    }
}