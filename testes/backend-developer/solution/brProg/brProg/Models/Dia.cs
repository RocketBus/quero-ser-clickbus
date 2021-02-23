using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace brProg.Models
{
    public class Dia
    {
        public int id { get; set; }
        public DateTime RegistrarDia { get; set; }
        public float valorDia { get; set; }
        public float valorTotal { get; set; }
    }
}