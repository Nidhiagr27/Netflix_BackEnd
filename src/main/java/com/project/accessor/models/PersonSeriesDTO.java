package com.project.accessor.models;

import lombok.Data;

@Data
public class PersonSeriesDTO {
    private String personID;
    private String seriesID;
    private PersonRole role; //director,singer,musician,producer,assistant director,author
    private double weightage;

    public PersonSeriesDTO(String personID, String seriesID, PersonRole role, double weightage) {
        this.personID = personID;
        this.seriesID = seriesID;
        this.role = role;
        this.weightage = weightage;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getSeriesID() {
        return seriesID;
    }

    public void setSeriesID(String seriesID) {
        this.seriesID = seriesID;
    }

    public PersonRole getRole() {
        return role;
    }

    public void setRole(PersonRole role) {
        this.role = role;
    }

    public double getWeightage() {
        return weightage;
    }

    public void setWeightage(double weightage) {
        this.weightage = weightage;
    }
}
