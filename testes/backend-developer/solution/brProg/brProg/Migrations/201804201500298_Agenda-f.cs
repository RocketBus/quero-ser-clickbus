namespace brProg.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class Agendaf : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Agenda",
                c => new
                    {
                        id = c.Int(nullable: false, identity: true),
                        TituloEvento = c.String(),
                        DataIni = c.String(),
                        TempoDuracao = c.String(),
                        Endereco = c.String(),
                        Descricao = c.String(),
                    })
                .PrimaryKey(t => t.id);
            
        }
        
        public override void Down()
        {
            DropTable("dbo.Agenda");
        }
    }
}
