using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace brProg.Models
{
    public class Agenda
    {
        public int id { get; set; }
        public string NomeCliente { get; set; }
        public string Telefone { get; set; }
        public string DataIni { get; set; }
        public string Descricao { get; set; }
    }
}