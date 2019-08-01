using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace brProg.Models
{
    public class DiaViewModel
    {
        [Key]
        public int id { get; set; }

        [Required(ErrorMessage = "Informe o Dia!", AllowEmptyStrings = false)]
        public DateTime RegistrarDia { get; set; }
        public float valorDia { get; set; }
        public float valorTotal { get; set; }
        public List<Login> Login { get; set; }

        public IEnumerable<Dia> Dias { get; set; }
        public string LoginAdicionado { get; set; }
        


        //[Required(ErrorMessage = "Insira uma Data!", AllowEmptyStrings = false)]
        //public DateTime RegistrarDia { get; set; }

        //public float valorDia { get; set; }

    }
}