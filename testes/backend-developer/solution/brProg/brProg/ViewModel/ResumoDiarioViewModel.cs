using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace brProg.Models
{
    public class ResumoDiarioViewModel
    {
        [Key]
        public int id { get; set; }



        [Required(ErrorMessage = "Informe o Nome do Cliente(a)!", AllowEmptyStrings = false)]
        public string NomeCliente { get; set; }
        public IEnumerable<Login> Logins { get; set; }

        [Required(ErrorMessage = "Informe o Funcionario Responsavel!", AllowEmptyStrings = false)]
        public string FuncionarioResponsavel { get; set; }

        [Required(ErrorMessage = "Informe o Procedimento!", AllowEmptyStrings = false)]
        public string Procedimento { get; set; }

        [Required(ErrorMessage = "Informe o valor!", AllowEmptyStrings = false)]
        public float Valor { get; set; }
        public int id_data { get; set; }
        public IEnumerable<ResumoDiario> ResumosDiario { get; set; }
    }
}