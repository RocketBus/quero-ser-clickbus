namespace brProg.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class Ajusteemcadastro : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Logins", "Permissoes", c => c.String());
        }
        
        public override void Down()
        {
            DropColumn("dbo.Logins", "Permissoes");
        }
    }
}
