﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace api.Models
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Infrastructure;
    
    public partial class Entities : DbContext
    {
        public Entities()
            : base("name=Entities")
        {
        }
    
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            throw new UnintentionalCodeFirstException();
        }
    
        public virtual DbSet<BOOKING> BOOKINGS { get; set; }
        public virtual DbSet<COACH> COACHES { get; set; }
        public virtual DbSet<COACHES_ARCHIVE> COACHES_ARCHIVE { get; set; }
        public virtual DbSet<CUSTOMER> CUSTOMERS { get; set; }
        public virtual DbSet<EMPLOYEE> EMPLOYEES { get; set; }
        public virtual DbSet<EMPLOYEES_ARCHIVE> EMPLOYEES_ARCHIVE { get; set; }
        public virtual DbSet<JOURNEY> JOURNEYS { get; set; }
        public virtual DbSet<REPLACEMENT> REPLACEMENTS { get; set; }
        public virtual DbSet<ROUTE_STOPS> ROUTE_STOPS { get; set; }
        public virtual DbSet<ROUTE> ROUTES { get; set; }
        public virtual DbSet<ROUTES_ARCHIVE> ROUTES_ARCHIVE { get; set; }
        public virtual DbSet<SHIFT> SHIFTS { get; set; }
        public virtual DbSet<STATION> STATIONS { get; set; }
        public virtual DbSet<STOP> STOPS { get; set; }
    }
}
