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
    
    public partial class STOP
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public STOP()
        {
            this.BOOKINGS = new HashSet<BOOKING>();
            this.BOOKINGS1 = new HashSet<BOOKING>();
            this.ROUTES = new HashSet<ROUTE>();
            this.ROUTES1 = new HashSet<ROUTE>();
            this.ROUTE_STOPS = new HashSet<ROUTE_STOPS>();
        }
    
        public decimal STOP_ID { get; set; }
        public string STOP_NAME { get; set; }
        public bool IS_STATION { get; set; }
        public string STOP_POSTCODE { get; set; }
        public Nullable<decimal> STOP_LATITUDE { get; set; }
        public Nullable<decimal> STOP_LONGITUDE { get; set; }
    
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<BOOKING> BOOKINGS { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<BOOKING> BOOKINGS1 { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<ROUTE> ROUTES { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<ROUTE> ROUTES1 { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<ROUTE_STOPS> ROUTE_STOPS { get; set; }
    }
}
