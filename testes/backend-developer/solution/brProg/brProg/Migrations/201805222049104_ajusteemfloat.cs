namespace brProg.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class ajusteemfloat : DbMigration
    {
        public override void Up()
        {
            AlterColumn("dbo.ResumoDiarios", "Valor", c => c.Single());
        }
        
        public override void Down()
        {
            AlterColumn("dbo.ResumoDiarios", "Valor", c => c.String());
        }
    }
}
