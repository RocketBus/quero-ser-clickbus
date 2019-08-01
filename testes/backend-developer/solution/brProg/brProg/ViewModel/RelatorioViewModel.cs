using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace brProg.Models
{
    public class RelatorioViewModel
    {
        [Key]
        public int id { get; set; }

        [Required(ErrorMessage = "Informe a Data Inicial!", AllowEmptyStrings = false)]
        public DateTime? DataInicial { get; set; }

        [Required(ErrorMessage = "Informe a Data Final!", AllowEmptyStrings = false)]
        public DateTime? DataFinal { get; set; }

        public string Funcionario { get; set; }
        public IEnumerable<Login> Logins { get; set; }
        public IEnumerable<ResumoDiario> ResumosDiario { get; set; }

        public IEnumerable<Relatorio> Relatorios { get; set; }

        public float valotTotal { get; set; }
    }
}