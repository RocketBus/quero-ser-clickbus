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
    public class RegistroDiaController : Controller
    {
        public ActionResult Index()
        {
            if (Session["usuarioLogadoID"] != null)
            {
                using (var db = new MyContext())
                {
                    //var controle = db.Dia.ToList();

                    DiaViewModel dias = new DiaViewModel();
                    //var valorTotal = db.ResumoDiario.GroupBy(x => x.id_data).ToList();
                    //float? valorTotalMesmo = 0;
                    //foreach (var valor in valorTotal)
                    //    valorTotalMesmo += valor.Sum(x => x.Valor);

                    dias.Dias = (from p in db.Dia
                                    orderby p.RegistrarDia
                              select p).Take(30).ToList();

                    float total = dias.Dias.Sum(x => x.valorDia);

                    dias.valorTotal = total;

                    //DiaViewModel diaControler = new DiaViewModel();

                    //diaControler.Login = controle;


                    return View(dias);

                }
            }
            else
            {
                return RedirectToAction("Login", "Login");
            }
        }

        [HttpPost]
        public ActionResult RegistrarDiaAcao(ResumoDiarioViewModel resumoDiario)
        {
            ResumoDiario resumoDiarioModel = new ResumoDiario();

            resumoDiarioModel.NomeCliente = resumoDiario.NomeCliente;
            resumoDiarioModel.Valor = resumoDiario.Valor;
            resumoDiarioModel.Procedimento = resumoDiario.Procedimento;
            resumoDiarioModel.FuncionarioResponsavel = resumoDiario.FuncionarioResponsavel;
            resumoDiarioModel.id_data = resumoDiario.id_data;

            if (resumoDiarioModel.FuncionarioResponsavel == "default")
            {
                TempData["Mensagem"] = "Selecione um Funcionário!";
                return RedirectToAction("ResumoDia", "RegistroDia", new { id = resumoDiarioModel.id_data });
            }
            else if(resumoDiarioModel.Valor <= 0)
            {
                TempData["Mensagem"] = "O valor não pode ser 0 ou negativo!";
                return RedirectToAction("ResumoDia", "RegistroDia", new { id = resumoDiarioModel.id_data });
            }

            if (ModelState.IsValid)
            {
                using (var db = new MyContext())
                {
                    db.ResumoDiario.Add(resumoDiarioModel);
                    TempData["Mensagem"] = "Ação cadastrada com Sucesso!";

                    var alterarValor = db.Dia.FirstOrDefault(x => x.id == resumoDiario.id_data);
                    
                    alterarValor.valorDia += resumoDiario.Valor;

                    db.SaveChanges();
                    ModelState.Clear();
                }
                return RedirectToAction("ResumoDia", "RegistroDia", new { id = resumoDiarioModel.id_data });
            }

            TempData["Mensagem"] = "Informe todos os campos!";
            return RedirectToAction("ResumoDia", "RegistroDia", new { id = resumoDiarioModel.id_data });
        }


        [HttpPost]
        public ActionResult Index(DiaViewModel dia)
        {
            if (Session["usuarioLogadoID"] != null)
            {
                Dia diaBanco = new Dia();
                dia.RegistrarDia = dia.RegistrarDia;

                diaBanco.RegistrarDia = dia.RegistrarDia;
                diaBanco.valorDia = dia.valorDia;

                if (ModelState.IsValid)
                {
                    using (var db = new MyContext())
                    {
                        var diaBancoComparar = dia.RegistrarDia.Date;
                        var RegistrarDia = db.Dia.AsEnumerable().Where(x => x.RegistrarDia.Date == diaBancoComparar).FirstOrDefault(); ;
                        if (RegistrarDia == null)
                        {
                            db.Dia.Add(diaBanco);
                            db.SaveChanges();
                            ModelState.Clear();
                            ViewBag.Message = "Dia Registrado com Sucesso!";
                        }
                        else
                        {
                            ViewBag.Message = "Este Dia já foi cadastrado!";
                        }
                    }
                }

                using (var db = new MyContext())
                {

                    dia.Dias = db.Dia.ToList();
                    return View(dia);
                }

            }
            else
            {
                return RedirectToAction("Login", "Login");
            }
        }

        public ActionResult Deletar(int id)
        {
            if (Session["usuarioLogadoID"] != null && Session["tipoUsu"].ToString() == "A")
            {
                using (var db = new MyContext())
                {
                    var controle = db.Dia.Where(x => x.id == id).SingleOrDefault();
                    db.Dia.Remove(controle);

                    var controle2 = db.ResumoDiario.Where(x => x.id_data == id).ToList();

                    foreach (var item in controle2)
                        db.ResumoDiario.Remove(item);
                    

                    db.SaveChanges();
                }
            }
            else
            {
                return RedirectToAction("Index", "RegistroDia");
            }
            return RedirectToAction("Index", "RegistroDia");
        }

        public ActionResult DeletarResumoDia(int id)
        {
            ResumoDiarioViewModel resumo = new ResumoDiarioViewModel();

            if (Session["usuarioLogadoID"] != null && Session["tipoUsu"].ToString() == "A")
            {
                using (var db = new MyContext())
                {
                    var controle = db.ResumoDiario.Where(x => x.id == id).SingleOrDefault();

                    db.ResumoDiario.Remove(controle);

                    

                    var alterarValor = db.Dia.FirstOrDefault(x => x.id == controle.id_data);
                    alterarValor.valorDia -= (float)controle.Valor;

                    db.SaveChanges();
                }
            }
            else
            {
                return RedirectToAction("Index", "RegistroDia");
            }
            return RedirectToAction("Index", "RegistroDia");
        }

        public ActionResult ResumoDia(int id)
        {
            if (Session["usuarioLogadoID"] != null)
            {
                using (var db = new MyContext())
                {
                    ResumoDiarioViewModel resumosDiarios = new ResumoDiarioViewModel();

                    var controle = db.ResumoDiario.Where(x => x.id_data == id).ToList();
                    resumosDiarios.ResumosDiario = controle;
                    resumosDiarios.id_data = id;

                    var logins = db.Login.ToList();
                    resumosDiarios.Logins = logins;

                    return View(resumosDiarios);
                }
            }
            else
            {
                return RedirectToAction("Login", "Login");
            }
        }

        public ActionResult Relatorio()
        {
            if (Session["usuarioLogadoID"] != null)
            {
                using (var db = new MyContext())
                {
                    RelatorioViewModel carregarLogins = new RelatorioViewModel();
                    carregarLogins.Relatorios = new List<Relatorio>();

                    if (Session["tipoUsu"].ToString() == "F")
                    {
                        List<Login> list = new List<Login>();
                        Login a = new Login();
                        string valorNome = Session["nomeUsuarioLogado"].ToString();
                        a.Nome = valorNome;
                        list.Add(a);
                        carregarLogins.Logins = list;
                    }else
                    {
                        var logins = db.Login.ToList();
                        carregarLogins.Logins = logins;
                    }
                    

                    return View(carregarLogins);
                }
            }
            else
            {
                return RedirectToAction("Login", "Login");
            }
        }

        [HttpPost]
        public ActionResult Relatorio(RelatorioViewModel modelRelatorio)
        {
            if (Session["usuarioLogadoID"] != null)
            {
                using (var db = new MyContext())
                {

                    // puxar todos os resumos where usuario responsavel = usuario e join data

                    Relatorio relatorios = new Relatorio();
                 

                    if (modelRelatorio.Funcionario == "Todos")
                    {
                        var teste = (from r in db.ResumoDiario
                                     join d in db.Dia on r.id_data equals d.id
                                     where d.RegistrarDia >= modelRelatorio.DataInicial && d.RegistrarDia <= modelRelatorio.DataFinal
                                     select new Relatorio
                                     {
                                         Funcionario = r.FuncionarioResponsavel,
                                         Data = d.RegistrarDia,
                                         valor = (float)r.Valor,
                                         Descricao = r.Procedimento,
                                     }).ToList();

                        modelRelatorio.Relatorios = new List<Relatorio>();
                    modelRelatorio.Relatorios = teste;
                    var logins = db.Login.ToList();
                    modelRelatorio.Logins = logins;


                        float total = modelRelatorio.Relatorios.Sum(x => x.valor);
                        modelRelatorio.valotTotal = total;

                        return View(modelRelatorio);
                }
                     else
                    {
                        var teste = (from r in db.ResumoDiario
                                     join d in db.Dia on r.id_data equals d.id
                                     where d.RegistrarDia >= modelRelatorio.DataInicial && d.RegistrarDia <= modelRelatorio.DataFinal && r.FuncionarioResponsavel == modelRelatorio.Funcionario
                                     select new Relatorio
                                     {
                                         Funcionario = r.FuncionarioResponsavel,
                                         Data = d.RegistrarDia,
                                         valor = (float)r.Valor,
                                         Descricao = r.Procedimento,
                                         valorTotal = (float)r.Valor,
                                     }).ToList();

                        modelRelatorio.Relatorios = new List<Relatorio>();
                        modelRelatorio.Relatorios = teste;

                        var logins = db.Login.ToList();
                        modelRelatorio.Logins = logins;


                        float total = modelRelatorio.Relatorios.Sum(x => x.valor);
                        modelRelatorio.valotTotal = total; 

                        return View(modelRelatorio);
                    }
                }

                    
            }
            else
            {
                return RedirectToAction("Login", "Login");
            }
        }
    }
}