using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace brProg.Models
{
    public class MyContext : DbContext
    {
        public MyContext() 
            : base(ConfigurationManager.ConnectionStrings["conexao"].ConnectionString)
        {

        }
        public DbSet<Login> Login { get; set; }
        public DbSet<Agenda> Agenda { get; set; }
        public DbSet<Dia> Dia { get; set; }
        public DbSet<ResumoDiario> ResumoDiario { get; set; }

    }
}