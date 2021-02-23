using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace brProg.Models
{
    public class AgendaViewModel
    {
        [Key]
        public int id { get; set; }

       
        [Required(ErrorMessage = "Informe o Nome do Cliente(a)!", AllowEmptyStrings = false)]
        public string NomeCliente { get; set; }

       
        [Required(ErrorMessage = "Informe a Data e o Horário!", AllowEmptyStrings = false)]
        public string DataIni { get; set; }

        
        [Required(ErrorMessage = "Informe o Telefone!", AllowEmptyStrings = false)]
        public string Telefone { get; set; }

      
        [Required(ErrorMessage = "Informe o Descrição!", AllowEmptyStrings = false)]
        public string Descricao { get; set; }

        public IEnumerable<Agenda> Agenda{ get; set; }






    }
}