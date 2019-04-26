package com.example.customermobileapplication.Model;

import com.example.customermobileapplication.Utilities.API.Helpers;


public class JourneyListItem {

    private Journey journey;

    private String journeyTitle;

    private String journeyDeparture;

    private String journeyArrival;

    private String coachStatus;

    //private boolean isExpandable;

//    public JourneyListItem(String journeyTitle, Date journeyDeparture, Date journeyArrival,
//                           String coachStatus){
//        this.journeyTitle = journeyTitle;
//
//        this.journeyDeparture = Helpers.formatAPIDateTime(journeyDeparture.toString());
//        this.journeyArrival = Helpers.formatAPIDateTime(journeyArrival.toString());
//
//        this.coachStatus = coachStatus;
//        //this.isExpandable = isExpandable;
//    }

    public JourneyListItem(Journey journey) {
        this.journey = journey;
    }

    public String getJourneyTitle() {
        return journeyTitle;
    }

    public void setJourneyTitle(String journeyTitle) {
        this.journeyTitle = journeyTitle;
    }

    public String getJourneyDeparture() {
        return journeyDeparture;
    }

    public void setJourneyDeparture(String journeyDeparture) {
        this.journeyDeparture = Helpers.formatAPIDateTime(journeyDeparture);
    }

    public String getJourneyArrival() {
        return journeyArrival;
    }

    public void setJourneyArrival(String journeyArrival) {
        this.journeyArrival = Helpers.formatAPIDateTime(journeyArrival);
    }

    public String getCoachStatus() {
        return coachStatus;
    }

    public void setCoachStatus(String coachStatus) {
        this.coachStatus = coachStatus;
    }
}
