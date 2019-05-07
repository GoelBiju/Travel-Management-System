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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Goel
 */
public class RouteActions {
    
    private static RouteActions actionsInstance;
    
    private APIConnection apiConnection;
    
    public RouteActions(){
        
        this.apiConnection = APIConnection.getInstance();
    }
    
    public static RouteActions getInstance() {
        if (actionsInstance == null) {
            actionsInstance = new RouteActions();
        }
        return actionsInstance;
    }
    
    public Route getRoutes(Integer routeId){
        Route route = (Route) apiConnection.getData("routes", Route.class, routeId.toString());
    
        return route;
    }
    
    public ArrayList<RouteStops> getRouteStops (Integer routeId){
        ArrayList<RouteStops> currentRoute = (ArrayList<RouteStops>) apiConnection.getListData("routestops/" + routeId, new TypeReference<List<RouteStops>>(){});

        return currentRoute;
    }
    
    public static void main(String[] args){
        RouteActions actions = new RouteActions();
        
        LoginBindingModel loginModel = new LoginBindingModel();
        loginModel.setEmployeeID("D1006");
        loginModel.setPassword("testpassword123");
        loginModel.setLoginType("employee");
        
        boolean response = actions.apiConnection.login(loginModel);
        System.out.println(response); 
        
        ArrayList<RouteStops> route = actions.getRouteStops(18);
        System.out.println(route.get(0).getStop());
        
        // 
        Collections.sort(route, (rs1, rs2) -> rs1.getPositionInRoute() - rs2.getPositionInRoute());
        
        for (RouteStops routeStop : route) {
            System.out.println("Stop pos: " + routeStop.getPositionInRoute());
        }
    }
}
