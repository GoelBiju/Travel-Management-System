using Newtonsoft.Json;
using System.ComponentModel.DataAnnotations;

namespace api.Models.DTO
{
    public class CustomerLoginDetailsDTO
    {
        [JsonProperty("emailAddress")]
        [Required]
        public string emailAddress { get; set; }
        
        [JsonProperty("password")]
        [Required]
        public string password { get; set; }
    }
}