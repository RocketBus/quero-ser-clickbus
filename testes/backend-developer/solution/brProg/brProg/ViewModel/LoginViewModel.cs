using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace brProg.Models
{
    public class LoginViewModel
    {
        [Key]
        public int id { get; set; }

        [MaxLength(15)]
        [Required(ErrorMessage = "Informe o Usuário!", AllowEmptyStrings = false)]
        public string Usuario { get; set; }

        [Required(ErrorMessage = "Informe a Senha!", AllowEmptyStrings = false)]
        [DataType(System.ComponentModel.DataAnnotations.DataType.Password)]
        public string Senha { get; set; }

        [Required(ErrorMessage = "Informe o Nome!", AllowEmptyStrings = false)]
        public string Nome { get; set; }
        public string unknown { get; set; }

        [Required(ErrorMessage = "Informe o Tipo de Usuário!", AllowEmptyStrings = false)]
        public string Permissoes { get; set; }


    }
}