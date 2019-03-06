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
    using System.Runtime.Serialization;

    [DataContract]
    public partial class EMPLOYEE
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public EMPLOYEE()
        {
            this.SHIFTS = new HashSet<SHIFT>();
        }
    
        [DataMember(Name = "employeeId")]
        public string EMPLOYEE_ID { get; set; }

        [DataMember(Name = "firstName")]
        public string FIRST_NAME { get; set; }

        [DataMember(Name = "lastName")]
        public string LAST_NAME { get; set; }

        [DataMember(Name = "jobRole")]
        public string JOB_ROLE { get; set; }

        [IgnoreDataMember]
        public string PASSWORD { get; set; }
    
        [DataMember(Name = "shifts")]
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<SHIFT> SHIFTS { get; set; }
    }
}
