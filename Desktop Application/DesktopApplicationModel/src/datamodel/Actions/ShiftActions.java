/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.Actions;

import com.fasterxml.jackson.core.type.TypeReference;
import datamodel.Shift;
import java.util.ArrayList;
import java.util.List;
import utilities.APIConnection;

/**
 *
 * @author Goel
 */
public class ShiftActions {
    
    private static ShiftActions actionsInstance;
    
    private APIConnection apiConnection;
    
    private ShiftActions() {
        apiConnection = APIConnection.getInstance();
    }
    
    public static ShiftActions getInstance() {
        if (actionsInstance == null) {
            actionsInstance = new ShiftActions();
        }
        return actionsInstance;
    }
    
    /**
     * 
     * @param shiftId
     * @return 
     */
    public Shift getShift(String shiftId) {
        
        Shift shift = (Shift) apiConnection.getData("shifts", Shift.class, shiftId);
        return shift;
    }
    
    
    /**
     * 
     * @param employeeId
     * @return 
     */
    public ArrayList<Shift> getDriverShifts(String employeeId) {
        
        ArrayList<Shift> driverShifts = (ArrayList<Shift>) apiConnection.getListData("shifts/employee/" + employeeId, new TypeReference<List<Shift>>() {});
        
        return driverShifts;
    }
    
    
}
