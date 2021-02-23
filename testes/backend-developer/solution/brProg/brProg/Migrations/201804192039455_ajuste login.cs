namespace brProg.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class ajustelogin : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Logins", "unknown", c => c.String());
        }
        
        public override void Down()
        {
            DropColumn("dbo.Logins", "unknown");
        }
    }
}
