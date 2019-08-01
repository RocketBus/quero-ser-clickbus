namespace brProg.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class insercaodedia : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Dias",
                c => new
                    {
                        id = c.Int(nullable: false, identity: true),
                        RegistrarDia = c.DateTime(nullable: false),
                        valorDia = c.Single(nullable: false),
                        valorTotal = c.Single(nullable: false),
                    })
                .PrimaryKey(t => t.id);
            
        }
        
        public override void Down()
        {
            DropTable("dbo.Dias");
        }
    }
}
