/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

/**
 *
 * @author vcastellani
 */
public class Coach {
    private int coachId;
    private String coachMake;
    private String coachModel;
    private String registrationPlate;
    private int coachCapacity;
    private boolean isActive;

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public String getCoachMake() {
        return coachMake;
    }

    public void setCoachMake(String coachMake) {
        this.coachMake = coachMake;
    }

    public String getCoachModel() {
        return coachModel;
    }

    public void setCoachModel(String coachModel) {
        this.coachModel = coachModel;
    }

    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(String regPlate) {
        this.registrationPlate = regPlate;
    }

    public int getCoachCapacity() {
        return coachCapacity;
    }

    public void setCoachCapacity(int coachCapacity) {
        this.coachCapacity = coachCapacity;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
