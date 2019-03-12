using Newtonsoft.Json;


namespace api.Models.DTO
{
    public class CustomerLoginDetails
    {
        [JsonRequired]
        [JsonProperty("emailAddress")]
        public string emailAddress { get; set; }
        
        [JsonRequired]
        [JsonProperty("password")]
        public string password { get; set; }
    }
}