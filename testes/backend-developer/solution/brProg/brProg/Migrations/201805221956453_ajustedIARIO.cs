namespace brProg.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class ajustedIARIO : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.ResumoDiarios", "id_data", c => c.Int(nullable: false));
        }
        
        public override void Down()
        {
            DropColumn("dbo.ResumoDiarios", "id_data");
        }
    }
}
