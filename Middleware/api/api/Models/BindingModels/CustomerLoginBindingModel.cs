using Newtonsoft.Json;
using System.ComponentModel.DataAnnotations;

namespace api.Models.BindingModels
{
    public class CustomerLoginBindingModel
    {
        [JsonProperty("emailAddress")]
        [JsonRequired]
        public string emailAddress { get; set; }
        
        [JsonProperty("password")]
        [JsonRequired]
        public string password { get; set; }
    }
}