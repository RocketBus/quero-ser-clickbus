using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace brProg.Models
{
    public class Relatorio
    {
        public int id { get; set; }
        public string Funcionario { get; set; }
        public DateTime Data { get; set; }
        public string Descricao { get; set; }
        public float valor { get; set; }
        public float valorTotal { get; set; }
    }
}