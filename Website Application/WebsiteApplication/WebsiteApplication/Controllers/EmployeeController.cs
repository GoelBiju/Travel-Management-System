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

        public async Task<List<employee>> GetEmployeesAsync()
        {
            HttpClient client = new HttpClient();
            List<employee> employees = new List<employee>();
            await RunAsync(client);
            employees = await GetAllEmployees(client);
            return employees;
        }





        public static async Task RunAsync(HttpClient client)
        {
            client.BaseAddress = new Uri("http://web.socem.plymouth.ac.uk/IntProj/PRCS252E/api/");
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));


        }

        private static async Task<List<employee>> GetAllEmployees(HttpClient client)
        {
            HttpResponseMessage response = await client.GetAsync("employees");
            if (response.IsSuccessStatusCode)
            {
                var JsonString = await response.Content.ReadAsStringAsync();
                List<employee>returnData = JsonConvert.DeserializeObject<List<employee>>(JsonString);
                return returnData;
            }
            return null;

           
        }
    }
}