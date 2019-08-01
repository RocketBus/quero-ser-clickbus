namespace brProg.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class Agendamento : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Agenda", "NomeCliente", c => c.String());
            AddColumn("dbo.Agenda", "Telefone", c => c.String());
            DropColumn("dbo.Agenda", "TituloEvento");
            DropColumn("dbo.Agenda", "TempoDuracao");
            DropColumn("dbo.Agenda", "Endereco");
        }
        
        public override void Down()
        {
            AddColumn("dbo.Agenda", "Endereco", c => c.String());
            AddColumn("dbo.Agenda", "TempoDuracao", c => c.String());
            AddColumn("dbo.Agenda", "TituloEvento", c => c.String());
            DropColumn("dbo.Agenda", "Telefone");
            DropColumn("dbo.Agenda", "NomeCliente");
        }
    }
}
