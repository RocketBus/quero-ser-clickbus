namespace brProg.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class InitialCreate : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Logins",
                c => new
                    {
                        id = c.Int(nullable: false, identity: true),
                        Nome = c.String(),
                        Senha = c.String(),
                        Usuario = c.String(),
                    })
                .PrimaryKey(t => t.id);
            
        }
        
        public override void Down()
        {
            DropTable("dbo.Logins");
        }
    }
}
