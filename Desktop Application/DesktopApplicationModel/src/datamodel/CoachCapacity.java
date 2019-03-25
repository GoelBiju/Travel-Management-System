/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import utilities.APIConnection;

/**
 *
 * @author adbellas
 */
public class CoachCapacity {
    private APIConnection apiConnection;
    
    public CoachCapacity(APIConnection apiConnection){
        this.apiConnection = apiConnection;
    }
    
    public int getCoachCapacity(){
        // TODO: ADD IN API GET CONNECTION LOGIC FOR RETURNING COACH CAPACITY
        
        return 0;
    }
}
