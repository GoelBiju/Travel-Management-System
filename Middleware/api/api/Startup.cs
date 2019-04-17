using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;

using Owin;

namespace api
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}