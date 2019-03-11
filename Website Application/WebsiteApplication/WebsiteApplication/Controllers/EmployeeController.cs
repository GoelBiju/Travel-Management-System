using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Threading.Tasks;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using Newtonsoft.Json;
using WebsiteApplication.Models;

namespace WebsiteApplication.Controllers
{
    public class EmployeeController
    {

        public List<employee> GetEmployees()
        {
            HttpClient client = new HttpClient();
            Task response = await RunAsync(client);
        }
            
            
        
            

        public static async Task<List<employee>> RunAsync(HttpClient client)
        {
            client.BaseAddress = new Uri("http://web.socem.plymouth.ac.uk/IntProj/PRCS252E/api/");
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));


        }

        private static async Task<HttpStatusCode> GetAllEmployees(HttpClient client, List<employee> returnData)
        {
            HttpResponseMessage response = await client.GetAsync("employees");
            if (response.IsSuccessStatusCode)
            {
                var JsonString = await response.Content.ReadAsStringAsync();
                returnData = JsonConvert.DeserializeObject<List<employee>>(JsonString);
            }

            return response.StatusCode;
        }
    }
}