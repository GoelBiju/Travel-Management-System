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
    
    public partial class BOOKING
    {
        public decimal BOOKING_REFERENCE { get; set; }
        public decimal CUSTOMER_ID { get; set; }
        public decimal JOURNEY_ID { get; set; }
        public decimal DEPARTING_STOP { get; set; }
        public decimal ARRIVAL_STOP { get; set; }
        public System.DateTime BOOKED_DATETIME { get; set; }
        public decimal PASSENGERS_SENIOR { get; set; }
        public decimal PASSENGERS_ADULT { get; set; }
        public decimal PASSENGERS_TEENAGER { get; set; }
        public decimal PASSENGERS_INFANT { get; set; }
        public decimal TICKET_PRICE { get; set; }
        public string STATUS { get; set; }
    
        public virtual STOP STOP { get; set; }
        public virtual CUSTOMER CUSTOMER { get; set; }
        public virtual STOP STOP1 { get; set; }
        public virtual JOURNEY JOURNEY { get; set; }
    }
}
