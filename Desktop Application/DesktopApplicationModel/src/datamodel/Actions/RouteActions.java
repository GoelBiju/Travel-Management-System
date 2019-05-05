/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.Actions;

import com.fasterxml.jackson.core.type.TypeReference;
import datamodel.BindingModels.LoginBindingModel;
import utilities.APIConnection;
import datamodel.RouteStops;
import datamodel.Route;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Goel
 */
public class RouteActions {
    
    private APIConnection apiConnection;
    
    public RouteActions(){
        this.apiConnection = APIConnection.getInstance();
    }
    
    public Route getRoutes(Integer routeId){
        Route route = (Route) apiConnection.getData("routes", Route.class, routeId.toString());
    
        return route;
    }
    
    public ArrayList<RouteStops> getRouteStops (Integer routeId){
        ArrayList<RouteStops> currentRoute = (ArrayList<RouteStops>) apiConnection.getListData("routestops/" + routeId, new TypeReference<List<RouteStops>>(){});

        return currentRoute;
    }
    
    
    
//    public static void main(String[] args){
//        RouteActions actions = new RouteActions();
//        
//        LoginBindingModel loginModel = new LoginBindingModel();
//        loginModel.setEmployeeID("D1006");
//        loginModel.setPassword("testpassword123");
//        loginModel.setLoginType("employee");
//        
//        boolean response = actions.apiConnection.login(loginModel);
//        System.out.println(response); 
//        
//        RouteStops route = actions.getRouteStops(4).get(0);
//        System.out.println(route.getStop());
//               
//    }
    
}
