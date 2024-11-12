package org.hbrs.se1.ws24.exercises.uebung4;

import org.hbrs.se1.ws24.exercises.uebung2.Member;

public class UserStory implements Member {
    private int id;
    private String title;
    private String acceptanceCriteria;
    private int relAddedVal;
    private int relPenalty;
    private int relRisk;
    private int relEffort;
    private String project;
    private double priorityValue;

    public UserStory(int id, String title, String acceptanceCriteria, int relAddedVal, int relPenalty, int relRisk,
            int relEffort, String project){
        this.id = id;
        this.title = title;
        this.acceptanceCriteria = acceptanceCriteria;
        this.relAddedVal = relAddedVal;
        this.relPenalty = relPenalty;
        this.relRisk = relRisk;
        this.relEffort = relEffort;
        this.project = project;
        this.priorityValue = calculatePriority();
    }

    private double calculatePriority(){
        return (relAddedVal + relPenalty)/(relEffort + relRisk);
    }

    @Override
    public Integer getID() {
        return this.id;
    }
}
