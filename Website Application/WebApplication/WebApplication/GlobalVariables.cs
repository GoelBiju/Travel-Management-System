using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Web;

namespace WebApplication
{
    public static class GlobalVariables
    {
        public static HttpClient WebApiClient = new HttpClient();

        private static string BASE_URL = "http://web.socem.plymouth.ac.uk/IntProj/PRCS252E/";

        private static string TEST_URL = "http://localhost:60019/";

        static GlobalVariables()
        {
            WebApiClient.BaseAddress = new Uri(TEST_URL + "api/");
            ResetHeaders();
        }

        public static bool WebLogin(string username, string password)
        {
            HttpClient tempClient = new HttpClient();
            tempClient.BaseAddress = new Uri(TEST_URL);

            var request = new HttpRequestMessage(HttpMethod.Post, "token");

            var keyValues = new List<KeyValuePair<string, string>>();
            keyValues.Add(new KeyValuePair<string, string>("username", username));
            keyValues.Add(new KeyValuePair<string, string>("password", password));
            keyValues.Add(new KeyValuePair<string, string>("grant_type", "password"));
            keyValues.Add(new KeyValuePair<string, string>("login_type", "employee"));

            request.Content = new FormUrlEncodedContent(keyValues);

            var response = tempClient.SendAsync(request).Result;

            if (response.IsSuccessStatusCode)
            {
                var jsonString = response.Content.ReadAsStringAsync().Result;
                JObject jsonResponse = JObject.Parse(jsonString);
                string accessToken = (string)jsonResponse["access_token"];

                // Store the bearer token in the global http client.
                WebApiClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", accessToken);

                return true;
            }

            return false;
        }

        public static void ResetHeaders()
        {
            WebApiClient.DefaultRequestHeaders.Clear();
            WebApiClient.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
        }
    }
}