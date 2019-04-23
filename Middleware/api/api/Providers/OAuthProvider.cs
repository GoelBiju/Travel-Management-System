using System;
using System.Threading.Tasks;
using System.Collections.Generic;
using System.Security.Claims;

using api.Services;
using Microsoft.Owin.Security;
using Microsoft.Owin.Security.OAuth;

namespace api.Providers
{
    public class OAuthProvider : OAuthAuthorizationServerProvider
    {
        CustomerService customerService = new CustomerService();
        EmployeeService employeeService = new EmployeeService();

        #region[GrantResourceOwnerCredentials]
        public override Task GrantResourceOwnerCredentials(OAuthGrantResourceOwnerCredentialsContext context)
        {
            //return base.GrantResourceOwnerCredentials(context);

            return Task.Factory.StartNew(() =>
            {
                var form = context.Request.ReadFormAsync().Result;
                var loginType = form["login_type"];

                // Ensure that a login is provided.
                if ((loginType != null) && (loginType.Equals("customer") || loginType.Equals("employee")))
                {
                    var userName = form["username"];
                    var password = form["password"];

                    // Handle the Customer login.
                    if (loginType.Equals("customer"))
                    {
                        //var customerService = new CustomerService();

                        var customer = customerService.ValidateCustomer(userName, password);
                        if (customer != null)
                        {
                            var claims = new List<Claim>()
                            {
                                new Claim(ClaimTypes.Sid, Convert.ToString(customer.CUSTOMER_ID)),
                                new Claim(ClaimTypes.Email, customer.EMAIL_ADDRESS),
                                new Claim(ClaimTypes.Role, "Customer")
                            };

                            ClaimsIdentity oAuthIdentity = new ClaimsIdentity(claims, Startup.OAuthOptions.AuthenticationType);

                            var properties = CreateProperties(customer.CUSTOMER_ID.ToString());
                            var ticket = new AuthenticationTicket(oAuthIdentity, properties);
                            context.Validated(ticket);
                        }
                        else
                        {
                            context.SetError("invalid_grant", "The user name or password is incorrect.");
                        }
                    }
                    else if (loginType.Equals("employee"))
                    {
                        var employee = employeeService.ValidateEmployee(userName, password);
                        if (employee != null)
                        {
                            var claims = new List<Claim>()
                            {
                                new Claim(ClaimTypes.Sid, Convert.ToString(employee.EMPLOYEE_ID)),
                                new Claim(ClaimTypes.Role, "Employee")
                            };

                            ClaimsIdentity oAuthIdentity = new ClaimsIdentity(claims, Startup.OAuthOptions.AuthenticationType);

                            var properties = CreateProperties(employee.EMPLOYEE_ID);
                            var ticket = new AuthenticationTicket(oAuthIdentity, properties);
                            context.Validated(ticket);
                        }
                        else
                        {
                            context.SetError("invalid_grant", "The user name or password is incorrect.");
                        }
                    }
                }
                else
                {
                    context.SetError("login_type_error", "The login type was not specified or there was an error recognising the type.");
                }
            });
        }
        #endregion

        #region[ValidateClientAuthentication]
        public override Task ValidateClientAuthentication(OAuthValidateClientAuthenticationContext context)
        {
            //return base.ValidateClientAuthentication(context);
            if (context.ClientId == null)
            {
                context.Validated();
            }

            return Task.FromResult<object>(null);
        }
        #endregion

        #region[TokenEndpoint]
        public override Task TokenEndpoint(OAuthTokenEndpointContext context)
        {
            //return base.TokenEndpoint(context);

            foreach (KeyValuePair<string, string> property in context.Properties.Dictionary)
            {
                context.AdditionalResponseParameters.Add(property.Key, property.Value);
            }

            return Task.FromResult<object>(null);
        }
        #endregion

        #region[CreateProperties]
        public static AuthenticationProperties CreateProperties(string identification)
        {
            IDictionary<string, string> data = new Dictionary<string, string>
            {
                {"identification", identification }
            };
            return new AuthenticationProperties(data);
        }
        #endregion
    }
}