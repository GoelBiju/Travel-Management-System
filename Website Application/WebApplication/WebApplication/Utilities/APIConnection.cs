using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Threading.Tasks;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using Newtonsoft.Json;
using WebApplication.Models;

namespace WebApplication.Utilities
{
    public class APIConnection
    {
        public static async Task RunAsync(HttpClient client)
        {
            client.BaseAddress = new Uri("http://web.socem.plymouth.ac.uk/IntProj/PRCS252E/api/");
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
        }
        static async Task GetAllEmployees(HttpClient client)
        {
            HttpResponseMessage response = await client.GetAsync("employees");
            if (response.IsSuccessStatusCode)
            {
                var JsonString = await response.Content.ReadAsStringAsync();
                Console.WriteLine(JsonString);
                var _Data = JsonConvert.DeserializeObject<List<Employee>>(JsonString);
            }
        }
    }
}