using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WebApplication.ViewModels
{
    public class ShiftViewModel
    {
        [Display(Name = "Shift No.")]
        [JsonProperty("shiftId")]
        public int ShiftId { get; set; }

        [Display(Name = "Employee No.")]
        [JsonProperty("employeeId")]
        public string EmployeeId { get; set; }
    
        [Display(Name = "Start of Shift")]
        [JsonProperty("startDateTime")]
        [DataType(DataType.DateTime)]
        public DateTime StartDateTime { get; set; }

        [Display(Name = "End of Shift")]
        [JsonProperty("endDateTime")]
        [DataType(DataType.DateTime)]
        public DateTime EndDateTime { get; set; }

    }
}