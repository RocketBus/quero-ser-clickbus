using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace brProg.Models
{
    public class ResumoDiario
    {
        public int id { get; set; }
        public int id_data { get; set; }
        public string NomeCliente { get; set; }
        public string FuncionarioResponsavel { get; set; }
        public string Procedimento { get; set; }
        public Nullable<float> Valor { get; set; }

    }
}