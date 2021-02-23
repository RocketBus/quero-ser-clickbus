using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace brProg.Models
{
    public class Login
    {
        public int id { get; set; }
        public string Nome { get; set; }
        public string Senha { get; set; }
        public string Usuario { get; set; }
        public string unknown { get; set; }

        public string Permissoes { get; set; }
    }
}