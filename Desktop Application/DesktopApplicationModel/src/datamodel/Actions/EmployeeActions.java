/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.Actions;

import datamodel.BindingModels.LoginBindingModel;
import datamodel.Employee;
import utilities.APIConnection;

/**
 *
 * @author Goel
 */
public class EmployeeActions {
    
    private static EmployeeActions actionsInstance;
    
    private APIConnection apiConnection;
    
    private EmployeeActions() {
        
        this.apiConnection = APIConnection.getInstance();
    }
    
    public static EmployeeActions getInstance(){
        if (actionsInstance == null) {
            actionsInstance = new EmployeeActions();
        }
        return actionsInstance;
        
    }
    
    /**
     * 
     * @param bindingModel
     * @return 
     */
    public boolean employeeLogin(LoginBindingModel bindingModel) {
        
        boolean validated = false;
        if (!bindingModel.getEmployeeID().isEmpty() && !bindingModel.getLoginType().isEmpty()
                && !bindingModel.getPassword().isEmpty()) {

            validated = this.apiConnection.login(bindingModel);
        }
        
        return validated;
    }
    
    /**
     * 
     * @return 
     */
    public void employeeSignOut() {
        this.apiConnection.clearConnection();
    }
    
    /**
     * 
     * @param employeeId
     * @return 
     */
    public Employee getEmployee(String employeeId) {
        
        Employee employee = (Employee) apiConnection.getData("employees", Employee.class, employeeId);
        
        return employee;
    }
    
    public static void main(String[] args) {
        
        EmployeeActions actions = new EmployeeActions();
        
        //
        LoginBindingModel loginModel = new LoginBindingModel();
        loginModel.setEmployeeID("D1006");
        loginModel.setPassword("testpassword123");
        loginModel.setLoginType("employee");
        
        boolean response = actions.employeeLogin(loginModel);
        System.out.println(response);
    }
}
