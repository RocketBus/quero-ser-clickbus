namespace brProg.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class ResumoDiario : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.ResumoDiarios",
                c => new
                    {
                        id = c.Int(nullable: false, identity: true),
                        NomeCliente = c.String(),
                        FuncionarioResponsavel = c.String(),
                        Procedimento = c.String(),
                        Valor = c.String(),
                    })
                .PrimaryKey(t => t.id);
            
        }
        
        public override void Down()
        {
            DropTable("dbo.ResumoDiarios");
        }
    }
}
