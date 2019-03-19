using Newtonsoft.Json;
using System.ComponentModel.DataAnnotations;

namespace api.Models.BindingModels
{
    public class CustomerLoginBindingModel
    {
        [JsonProperty("emailAddress")]
        [Required]
        public string emailAddress { get; set; }
        
        [JsonProperty("password")]
        [Required]
        public string password { get; set; }
    }
}