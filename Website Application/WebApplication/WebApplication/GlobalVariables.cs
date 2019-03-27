﻿using System;
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
        
        static GlobalVariables()
        {
            WebApiClient.BaseAddress = new Uri("http://web.socem.plymouth.ac.uk/IntProj/PRCS252E/api/");
            WebApiClient.DefaultRequestHeaders.Clear();
            WebApiClient.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
        }
    }
}