//------------------------------------------------------------------------------
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
    using System.Collections.Generic;
    
    public partial class TIMETABLE
    {
        public string ROUTE_ID { get; set; }
        public Nullable<decimal> STATION_ID { get; set; }
        public System.DateTime DEPARTURE_TIME { get; set; }
        public System.DateTime ARRIVAL_TIME { get; set; }
        public string DAY_OF_JOURNEY { get; set; }
    
        public virtual ROUTE ROUTE { get; set; }
        public virtual STATION STATION { get; set; }
    }
}